package com.teamtrade.rfp.model;


import static com.teamtrade.rfp.constants.Constants.DEFAULT_CATALOG;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Khalid.ALIANNE
 *
 */
@Entity
@Table(name = "ActorType", catalog = DEFAULT_CATALOG )
public class ActorType implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "actor_type_id", unique = true, nullable = false)
	private Integer actorTypeId;
	
	@Column(name = "name", unique = true, nullable = false, length = 50)
	private String name;
	
//	@OneToMany(fetch = FetchType.EAGER, mappedBy = "actorType")
//	@Cascade({ CascadeType.ALL })
//	private Set<Actor> actors = new HashSet<Actor>(0);
//	

	public ActorType() {
	}
	
	public ActorType(Integer actorTypeId) {
		this.actorTypeId =actorTypeId;
	}

	public ActorType(String name) {
		this.name = name;
	}

	public Integer getActorTypeId() {
		return this.actorTypeId;
	}

	public void setActorTypeId(Integer actorTypeId) {
		this.actorTypeId = actorTypeId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
//	public Set<Actor> getActors() {
//		return this.actors;
//	}
//
//	public void setActors(Set<Actor> actors) {
//		this.actors = actors;
//	}

	@Override
	public String toString() {
		return "ActorType [actorTypeId=" + actorTypeId + ", name=" + name + "]";
	}

}
