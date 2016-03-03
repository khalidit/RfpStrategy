package com.teamtrade.rfp.model;

import static com.teamtrade.rfp.constants.Constants.DEFAULT_CATALOG;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author Khalid.ALIANNE
 *
 */
@Entity
@Table(name = "Actor", catalog = DEFAULT_CATALOG)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="id", discriminatorType=DiscriminatorType.INTEGER)
public class Actor implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id  
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name = "id", unique = true, nullable = false)
	private Integer actorId;
	
	@Column(name = "name", unique = true, nullable = false)
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.PERSIST)
	@JoinColumn(name = "appreciation", nullable = false)
	private Appreciation appreciation;
	
	public Actor() {}

	public Actor(ActorType actorType, Appreciation appreciation) {
		this.appreciation = appreciation;
	}

	public Actor(Appreciation appreciation) {
		this.appreciation = appreciation;
	}
	
	public Integer getActorId() {
		return this.actorId;
	}

	public void setActorId(Integer actorId) {
		this.actorId = actorId;
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Appreciation getAppreciation() {
		return this.appreciation;
	}

	public void setAppreciation(Appreciation appreciation) {
		this.appreciation = appreciation;
	}
}
