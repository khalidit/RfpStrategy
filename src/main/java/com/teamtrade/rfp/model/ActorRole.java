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
import javax.persistence.UniqueConstraint;


/**
 * 
 * @author Khalid.ALIANNE
 *
 */
@Entity
@Table(name = "ActorRole", catalog = DEFAULT_CATALOG, uniqueConstraints = @UniqueConstraint(columnNames = "name") )
public class ActorRole implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer roleId;
	private String name;
	private Set<RfpActors> rfpActors = new HashSet<RfpActors>(0);

	public ActorRole() {
	}

	public ActorRole(String name) {
		this.name = name;
	}

	public ActorRole(String name, Set<RfpActors> rfpActors) {
		this.name = name;
		this.rfpActors = rfpActors;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "role_id", unique = true, nullable = false)
	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Column(name = "name", unique = true, nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "actorRole")
	public Set<RfpActors> getRfpActors() {
		return this.rfpActors;
	}

	public void setRfpActors(Set<RfpActors> rfpActors) {
		this.rfpActors = rfpActors;
	}

}
