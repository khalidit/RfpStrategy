package com.teamtrade.rfp.model;

import static com.teamtrade.rfp.constants.Constants.DEFAULT_CATALOG;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author Khalid.ALIANNE
 *
 */
@Entity
@Table(name = "Department", catalog = DEFAULT_CATALOG)
public class Department implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer departmentId;
	private Company company;
	private String name;

	public Department() {
	}

	public Department(Company company, String name) {
		this.company = company;
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "department_id", unique = true, nullable = false)
	public Integer getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id", nullable = false)
	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Column(name = "name", nullable = false, length = 200)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
