package com.teamtrade.rfp.model;


import static com.teamtrade.rfp.constants.Constants.DEFAULT_CATALOG;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 
 * @author Khalid.ALIANNE
 *
 */
@Entity
@Table(name = "Civility", catalog = DEFAULT_CATALOG, uniqueConstraints = @UniqueConstraint(columnNames = "name") )
public class Civility implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "civility_id", unique = true, nullable = false)
	private Integer civilityId;
	
	@Column(name = "name", unique = true, nullable = false, length = 12)
	private String name;
	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "civility")
//	private Set<Person> persons = new HashSet<Person>(0);

	public Civility() {
	}

	public Civility(String name) {
		this.name = name;
	}

//	public Civility(String name, Set<Person> persons) {
//		this.name = name;
//		this.persons = persons;
//	}

	public Integer getCivilityId() {
		return this.civilityId;
	}

	public void setCivilityId(Integer civilityId) {
		this.civilityId = civilityId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public Set<Person> getPersons() {
//		return this.persons;
//	}
//
//	public void setPersons(Set<Person> persons) {
//		this.persons = persons;
//	}

}
