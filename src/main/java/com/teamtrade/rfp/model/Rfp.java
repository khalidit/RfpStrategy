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
public class Rfp implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "rfp_id", unique = true, nullable = false)
	private Integer rfpId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "client_id", nullable = false)
	private Client client; // The owner of RFP
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "rfp_status_id", nullable = false)
	private RfpStatus rfpStatus;
	
	@Column(name = "name", nullable = false, length = 200)
	private String name;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "startdate", nullable = false, length = 19)
	private Date startdate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "enddate", nullable = false, length = 19)
	private Date enddate;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "RfpActors", 
	    inverseJoinColumns = {@JoinColumn(name = "actor_id", referencedColumnName = "id")}, // RfpActors -> Actor
		joinColumns = {@JoinColumn(name="rfp_id", referencedColumnName = "rfp_id")} // RfpActors -> Rfp
	)
	private Set<Actor> actors = new HashSet<Actor>(0);
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="RfpRelations",
		inverseJoinColumns = {@JoinColumn(name = "relation_id", referencedColumnName="relation_id")}, // RfpRelations -> Relation
		joinColumns = {@JoinColumn(name = "rfp_id", referencedColumnName = "rfp_id")} // RfpRelations -> Rfp
	)
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
		this(client, rfpStatus, name, startdate, enddate);
		this.actors = actors;
		this.relations = relations;
	}

	public Integer getRfpId() {
		return this.rfpId;
	}

	public void setRfpId(Integer rfpId) {
		this.rfpId = rfpId;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public RfpStatus getRfpStatus() {
		return this.rfpStatus;
	}

	public void setRfpStatus(RfpStatus rfpStatus) {
		this.rfpStatus = rfpStatus;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartdate() {
		return this.startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public Set<Actor> getActors() {
		return this.actors;
	}

	public void setActors(Set<Actor> actors) {
		this.actors = actors;
	}

	public Set<Relation> getRelations() {
		return this.relations;
	}

	public void setRelations(Set<Relation> relations) {
		this.relations = relations;
	}
}
