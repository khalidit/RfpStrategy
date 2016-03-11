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
@Table(name = "Relation", catalog = DEFAULT_CATALOG)
public class Relation implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "relation_id", unique = true, nullable = false)
	private Integer relationId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "actor_from", nullable = false)
	private Actor actorSource;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "actor_to", nullable = false)
	private Actor actorTaget;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "relation_quality_id", nullable = false)
	private RelationQuality relationQuality;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "relation_type_id", nullable = false)
	private RelationType relationType;
	
	@Column(name = "relation_title", length = 200)
	private String relationTitle;
	
	@Column(name = "relation_comment", length = 500)
	private String relationComment;
	
//	@ManyToMany(mappedBy="relations")
//	private Set<Rfp> rfps = new HashSet<Rfp>(0);

	public Relation() {
	}

	public Relation(Actor actorTarget, Actor actorSource, RelationQuality relationQuality,
			RelationType relationType) {
		this.actorTaget = actorTarget;
		this.actorSource = actorSource;
		this.relationQuality = relationQuality;
		this.relationType = relationType;
	}

	public Relation(Actor actorTarget, Actor actorSource, RelationQuality relationQuality,
			RelationType relationType, String relationTitle, String relationComment) {
		this.actorTaget = actorTarget;
		this.actorSource = actorSource;
		this.relationQuality = relationQuality;
		this.relationType = relationType;
		this.relationTitle = relationTitle;
		this.relationComment = relationComment;
//		this.rfps = rfps;
	}

	public Integer getRelationId() {
		return this.relationId;
	}

	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}

	public Actor getActorTarget() {
		return this.actorTaget;
	}

	public void setActorTarget(Actor actorTaget) {
		this.actorTaget = actorTaget;
	}

	public Actor getActorSource() {
		return this.actorSource;
	}

	public void setActorSource(Actor actorSource) {
		this.actorSource = actorSource;
	}

	public RelationQuality getRelationQuality() {
		return this.relationQuality;
	}

	public void setRelationQuality(RelationQuality relationQuality) {
		this.relationQuality = relationQuality;
	}

	public RelationType getRelationType() {
		return this.relationType;
	}

	public void setRelationType(RelationType relationType) {
		this.relationType = relationType;
	}

	public String getRelationTitle() {
		return this.relationTitle;
	}

	public void setRelationTitle(String relationTitle) {
		this.relationTitle = relationTitle;
	}

	public String getRelationComment() {
		return this.relationComment;
	}

	public void setRelationComment(String relationComment) {
		this.relationComment = relationComment;
	}
//
//	public Set<Rfp> getRfps() {
//		return this.rfps;
//	}
//
//	public void setRfps(Set<Rfp> rfps) {
//		this.rfps = rfps;
//	}

}
