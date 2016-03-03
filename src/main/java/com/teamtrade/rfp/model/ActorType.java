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
@Table(name = "ActorType", catalog = DEFAULT_CATALOG, uniqueConstraints = @UniqueConstraint(columnNames = "name") )
public class ActorType implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer actorTypeId;
	private String name;

	public ActorType() {
	}

	public ActorType(String name) {
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "actor_type_id", unique = true, nullable = false)
	public Integer getActorTypeId() {
		return this.actorTypeId;
	}

	public void setActorTypeId(Integer actorTypeId) {
		this.actorTypeId = actorTypeId;
	}

	@Column(name = "name", unique = true, nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
