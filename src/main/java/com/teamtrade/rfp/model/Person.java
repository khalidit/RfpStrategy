package com.teamtrade.rfp.model;

import static com.teamtrade.rfp.constants.Constants.DEFAULT_CATALOG;
import static com.teamtrade.rfp.constants.Constants.DISCRIMINATOR_PERSON;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * 
 * @author Khalid.ALIANNE
 *
 */
@Entity
@Table(name = "Person", catalog = DEFAULT_CATALOG)
@PrimaryKeyJoinColumn(name = "id")
@DiscriminatorValue(value=DISCRIMINATOR_PERSON)
public class Person extends Actor implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Civility civility;
	private String _function;
	private Department department;
	private Person manager;
	private String avatar;

	public Person() {
	}

	public Person(Civility civility, Department department, Person person) {
		this.civility = civility;
		this.department = department;
		this.manager = person;
	}

	public Person(Civility civility, Department department, Person manager, String function, String avatar) {
		this.civility = civility;
		this.department = department;
		this.manager = manager;
		this._function = function;
		this.avatar = avatar;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.PERSIST)
	@JoinColumn(name = "civility", nullable = false)
	public Civility getCivility() {
		return this.civility;
	}

	public void setCivility(Civility civility) {
		this.civility = civility;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.PERSIST)
	@JoinColumn(name = "department", nullable = false)
	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.PERSIST)
	@JoinColumn(name = "manager", nullable = false)
	public Person getPerson() {
		return this.manager;
	}

	public void setPerson(Person person) {
		this.manager = person;
	}

	@Column(name = "_function", length = 200)
	public String getFunction() {
		return this._function;
	}

	public void setFunction(String function) {
		this._function = function;
	}

	@Column(name = "avatar", length = 200)
	public String getAvatar() {
		return this.avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
}
