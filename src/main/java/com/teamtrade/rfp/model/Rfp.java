package com.teamtrade.rfp.model;

import static com.teamtrade.rfp.constants.Constants.DEFAULT_CATALOG;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Defines the properties of the RFP, his status, his client owner, all of his actors and the relations between them
 * @author Khalid.ALIANNE
 *
 */
@Entity
@Table(name = "Rfp", catalog = DEFAULT_CATALOG)
public class Rfp implements java.io.Serializable, Comparable<Rfp> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer rfpId;
	private Client client; // The owner of RFP
	private RfpStatus rfpStatus;
	private String name;
	private Date startdate;
	private Date enddate;
	private Set<Actor> actors = new HashSet<Actor>(0);
	private Set<Relation> relations = new HashSet<Relation>(0);

	public Rfp() {
	}

	public Rfp(Client client, RfpStatus rfpStatus, String name, Date startdate, Date enddate) {
		this.client = client;
		this.rfpStatus = rfpStatus;
		this.name = name;
		this.startdate = startdate;
		this.enddate = enddate;
	}

	public Rfp(Client client, RfpStatus rfpStatus, String name, Date startdate, Date enddate, Set<Actor> actors,
			Set<Relation> relations) {
		this.client = client;
		this.rfpStatus = rfpStatus;
		this.name = name;
		this.startdate = startdate;
		this.enddate = enddate;
		this.actors = actors;
		this.relations = relations;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "rfp_id", unique = true, nullable = false)
	public Integer getRfpId() {
		return this.rfpId;
	}

	public void setRfpId(Integer rfpId) {
		this.rfpId = rfpId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "client_id", nullable = false)
	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "rfp_status_id", nullable = false)
	public RfpStatus getRfpStatus() {
		return this.rfpStatus;
	}

	public void setRfpStatus(RfpStatus rfpStatus) {
		this.rfpStatus = rfpStatus;
	}

	@Column(name = "name", nullable = false, length = 200)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "startdate", nullable = false, length = 19)
	public Date getStartdate() {
		return this.startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "enddate", nullable = false, length = 19)
	public Date getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="RfpActors",  
    joinColumns={@JoinColumn(name="rfp_id", nullable=false, updatable=false)},  
    inverseJoinColumns={@JoinColumn(name="actor_id", nullable=false, updatable=false)})  
	public Set<Actor> getActors() {
		return this.actors;
	}

	public void setActors(Set<Actor> actors) {
		this.actors = actors;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="RfpRelations",  
    joinColumns={@JoinColumn(name="rfp_id", nullable=false, updatable=false)},  
    inverseJoinColumns={@JoinColumn(name="relation_id", nullable=false, updatable=false)})  
	public Set<Relation> getRelations() {
		return this.relations;
	}

	public void setRelations(Set<Relation> relations) {
		this.relations = relations;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((rfpId == null) ? 0 : rfpId.hashCode());
		result = prime * result + ((rfpStatus == null) ? 0 : rfpStatus.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rfp other = (Rfp) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (rfpId == null) {
			if (other.rfpId != null)
				return false;
		} else if (!rfpId.equals(other.rfpId))
			return false;
		if (rfpStatus == null) {
			if (other.rfpStatus != null)
				return false;
		} else if (!rfpStatus.equals(other.rfpStatus))
			return false;
		return true;
	}

	@Override
	public int compareTo(Rfp o) {
		return this.getName().compareTo(o.getName());
	}

}
