package com.teamtrade.rfp.model;

import static com.teamtrade.rfp.constants.Constants.DEFAULT_CATALOG;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
public class Person extends Actor implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name = "civility", nullable = false)
	private Civility civility;
	
	@Column(name = "_function", length = 200)
	private String _function;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name = "department", nullable = false)
	private Department department;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name = "manager", nullable = false)
	private Person manager;
	
	@Column(name = "avatar", length = 200)
	private String avatar = "NA";

	public Person() {
	}

	public Person(Civility civility, String function, Department department, Person manager) {
		this.civility = civility;
		this.department = department;
		this.manager = manager;
		this._function = function;
	}

	public Person(Civility civility, String function, Department department, Person manager, String avatar) {
		this(civility, function, department, manager);
		this.avatar = avatar;
	}

	public Civility getCivility() {
		return this.civility;
	}

	public void setCivility(Civility civility) {
		this.civility = civility;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Person getManager() {
		return this.manager;
	}

	public void setManager(Person manager) {
		this.manager = manager;
	}

	public String getFunction() {
		return this._function;
	}

	public void setFunction(String function) {
		this._function = function;
	}

	public String getAvatar() {
		return this.avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
}
