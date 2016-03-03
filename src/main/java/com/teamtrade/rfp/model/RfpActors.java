package com.teamtrade.rfp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import static com.teamtrade.rfp.constants.Constants.DEFAULT_CATALOG;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Defines an actor of an Rfp
 * @author Khalid.ALIANNE
 *
 */
@Entity
@Table(name = "RfpActors", catalog = DEFAULT_CATALOG)
public class RfpActors implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer rfpActorsId;
	private Actor actor;
	private ActorRole actorRole;
	private Rfp rfp;

	public RfpActors() {
	}

	public RfpActors(Actor actor, ActorRole actorRole, Rfp rfp) {
		this.actor = actor;
		this.actorRole = actorRole;
		this.rfp = rfp;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "rfp_actors_id", unique = true, nullable = false)
	public Integer getRfpActorsId() {
		return this.rfpActorsId;
	}

	public void setRfpActorsId(Integer rfpActorsId) {
		this.rfpActorsId = rfpActorsId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "actor_id", nullable = false)
	public Actor getActor() {
		return this.actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", nullable = false)
	public ActorRole getActorRole() {
		return this.actorRole;
	}

	public void setActorRole(ActorRole actorRole) {
		this.actorRole = actorRole;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rfp_id", nullable = false)
	public Rfp getRfp() {
		return this.rfp;
	}

	public void setRfp(Rfp rfp) {
		this.rfp = rfp;
	}

}
