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
 * The appreciation of the actor for TeamTrade, like :
 * Favorable, Very favorable, Neutral, Unfavorable, Very Unfavorable
 * @author Khalid.ALIANNE
 *
 */
@Entity
@Table(name = "Appreciation", catalog = DEFAULT_CATALOG, uniqueConstraints = @UniqueConstraint(columnNames = "name") )
public class Appreciation implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer appreciationId;
	private String name;
	private Integer rating;
//	private Set<Actor> actors = new HashSet<Actor>(0); // the set of actors concerned by this appreciation

	public Appreciation() {
	}

	public Appreciation(String name) {
		this.name = name;
	}

	public Appreciation(String name, Integer rating) {
		this.name = name;
		this.rating = rating;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "appreciation_id", unique = true, nullable = false)
	public Integer getAppreciationId() {
		return this.appreciationId;
	}

	public void setAppreciationId(Integer appreciationId) {
		this.appreciationId = appreciationId;
	}

	@Column(name = "name", unique = true, nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "rating")
	public Integer getRating() {
		return this.rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "appreciation")
//	public Set<Actor> getActors() {
//		return this.actors;
//	}
//
//	public void setActors(Set<Actor> actors) {
//		this.actors = actors;
//	}

	@Override
	public String toString() {
		return "Appreciation [appreciationId=" + appreciationId + ", name=" + name + ", rating=" + rating + "]";
	}

}
