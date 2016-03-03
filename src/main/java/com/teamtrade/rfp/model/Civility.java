package com.teamtrade.rfp.model;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import static com.teamtrade.rfp.constants.Constants.DEFAULT_CATALOG;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	private Integer civilityId;
	private String name;
	private Set<Person> persons = new HashSet<Person>(0);

	public Civility() {
	}

	public Civility(String name) {
		this.name = name;
	}

	public Civility(String name, Set<Person> persons) {
		this.name = name;
		this.persons = persons;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "civility_id", unique = true, nullable = false)
	public Integer getCivilityId() {
		return this.civilityId;
	}

	public void setCivilityId(Integer civilityId) {
		this.civilityId = civilityId;
	}

	@Column(name = "name", unique = true, nullable = false, length = 12)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "civility")
	public Set<Person> getPersons() {
		return this.persons;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}

}
