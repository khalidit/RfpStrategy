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
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "client_id", unique = true, nullable = false)
	private Integer clientId;
	
	@Column(name = "company", unique = true, nullable = false)
	private String company;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "client")
	private Set<Rfp> rfps = new HashSet<Rfp>(0);

	public Client() {
	}

	public Client(int id) {
		this.clientId = id;
	}
	
	public Client(String company) {
		this.company = company;
	}

	public Client(String company, Set<Rfp> rfps) {
		this.company = company;
		this.rfps = rfps;
	}
	
	public Integer getClientId() {
		return this.clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Set<Rfp> getRfps() {
		return this.rfps;
	}

	public void setRfps(Set<Rfp> rfps) {
		this.rfps = rfps;
	}

	@Override
	public int compareTo(Client o) {
		return this.company.compareTo(o.company);
	}
}
