package com.teamtrade.rfp.model;

import static com.teamtrade.rfp.constants.Constants.DEFAULT_CATALOG;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @author Khalid.ALIANNE
 *
 */
@Entity
@Table(name = "Client", catalog = DEFAULT_CATALOG )
public class Client implements java.io.Serializable, Comparable<Client> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer clientId;
	private Company company;
	private Set<Rfp> rfps = new HashSet<Rfp>(0);

	public Client() {
	}

	public Client(int id) {
		this.clientId = id;
	}
	
	public Client(Company company) {
		this.company = company;
	}

	public Client(Company company, Set<Rfp> rfps) {
		this.company = company;
		this.rfps = rfps;
	}
	

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "client_id", unique = true, nullable = false)
	public Integer getClientId() {
		return this.clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "company", unique = true, nullable = false)
	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "client")
	public Set<Rfp> getRfps() {
		return this.rfps;
	}

	public void setRfps(Set<Rfp> rfps) {
		this.rfps = rfps;
	}

	@Override
	public int compareTo(Client o) {
		return this.getCompany().compareTo(o.getCompany());
	}
	
}
