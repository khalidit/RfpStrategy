package com.teamtrade.rfp.model;

import static com.teamtrade.rfp.constants.Constants.DEFAULT_CATALOG;
import static com.teamtrade.rfp.constants.Constants.DISCRIMINATOR_COMPAGNY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * 
 * @author Khalid.ALIANNE
 *
 */
@Entity
@Table(name = "Company", catalog = DEFAULT_CATALOG)
@PrimaryKeyJoinColumn(name = "id")
@DiscriminatorValue(value=DISCRIMINATOR_COMPAGNY)
public class Company extends Actor implements java.io.Serializable, Comparable<Company> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "siren_number", length = 200)
	private String sirenNumber;
	
	@Column(name = "logo", length = 200)
	private String logo;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
	private Set<Department> departments = new HashSet<Department>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
	private Set<Client> clients = new HashSet<Client>(0);

	public Company() {
	}

	public Company(String sirenNumber, String logo, Set<Department> departments, Set<Client> clients) {
		this.sirenNumber = sirenNumber;
		this.logo = logo;
		this.departments = departments;
		this.clients = clients;
	}

	public String getSirenNumber() {
		return this.sirenNumber;
	}

	public void setSirenNumber(String sirenNumber) {
		this.sirenNumber = sirenNumber;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Set<Department> getDepartments() {
		return this.departments;
	}

	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}

	public Set<Client> getClients() {
		return this.clients;
	}

	public void setClients(Set<Client> clients) {
		this.clients = clients;
	}
	
	@Override
	public int compareTo(Company c) {
		return this.getName().compareTo(c.getName());
	}
}
