package com.teamtrade.rfp.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import static com.teamtrade.rfp.constants.Constants.DEFAULT_CATALOG;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	private Integer relationId;
	private Actor actorByActorTo;
	private Actor actorByActorFrom;
	private RelationQuality relationQuality;
	private RelationType relationType;
	private String relationTitle;
	private String relationComment;
	private Set<RfpRelations> rfpRelations = new HashSet<RfpRelations>(0);

	public Relation() {
	}

	public Relation(Actor actorByActorTo, Actor actorByActorFrom, RelationQuality relationQuality,
			RelationType relationType) {
		this.actorByActorTo = actorByActorTo;
		this.actorByActorFrom = actorByActorFrom;
		this.relationQuality = relationQuality;
		this.relationType = relationType;
	}

	public Relation(Actor actorByActorTo, Actor actorByActorFrom, RelationQuality relationQuality,
			RelationType relationType, String relationTitle, String relationComment, Set<RfpRelations> rfpRelations) {
		this.actorByActorTo = actorByActorTo;
		this.actorByActorFrom = actorByActorFrom;
		this.relationQuality = relationQuality;
		this.relationType = relationType;
		this.relationTitle = relationTitle;
		this.relationComment = relationComment;
		this.rfpRelations = rfpRelations;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "relation_id", unique = true, nullable = false)
	public Integer getRelationId() {
		return this.relationId;
	}

	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "actor_to", nullable = false)
	public Actor getActorByActorTo() {
		return this.actorByActorTo;
	}

	public void setActorByActorTo(Actor actorByActorTo) {
		this.actorByActorTo = actorByActorTo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "actor_from", nullable = false)
	public Actor getActorByActorFrom() {
		return this.actorByActorFrom;
	}

	public void setActorByActorFrom(Actor actorByActorFrom) {
		this.actorByActorFrom = actorByActorFrom;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "relation_quality_id", nullable = false)
	public RelationQuality getRelationQuality() {
		return this.relationQuality;
	}

	public void setRelationQuality(RelationQuality relationQuality) {
		this.relationQuality = relationQuality;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "relation_type_id", nullable = false)
	public RelationType getRelationType() {
		return this.relationType;
	}

	public void setRelationType(RelationType relationType) {
		this.relationType = relationType;
	}

	@Column(name = "relation_title", length = 200)
	public String getRelationTitle() {
		return this.relationTitle;
	}

	public void setRelationTitle(String relationTitle) {
		this.relationTitle = relationTitle;
	}

	@Column(name = "relation_comment", length = 500)
	public String getRelationComment() {
		return this.relationComment;
	}

	public void setRelationComment(String relationComment) {
		this.relationComment = relationComment;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "relation")
	public Set<RfpRelations> getRfpRelationses() {
		return this.rfpRelations;
	}

	public void setRfpRelationses(Set<RfpRelations> rfpRelations) {
		this.rfpRelations = rfpRelations;
	}

}
