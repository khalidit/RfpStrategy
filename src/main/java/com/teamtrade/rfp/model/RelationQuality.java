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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Defines a quality of the relations, like : Bad, Good, Very Bad, Very Good
 * @author Khalid.ALIANNE
 *
 */
@Entity
@Table(name = "RelationQuality", catalog = DEFAULT_CATALOG, uniqueConstraints = @UniqueConstraint(columnNames = "name") )
public class RelationQuality implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer qualityId;
	private String name;
	private Set<Relation> relations = new HashSet<Relation>(0); // All relations having this quality

	public RelationQuality() {
	}

	public RelationQuality(String name) {
		this.name = name;
	}

	public RelationQuality(String name, Set<Relation> relations) {
		this.name = name;
		this.relations = relations;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "quality_id", unique = true, nullable = false)
	public Integer getQualityId() {
		return this.qualityId;
	}

	public void setQualityId(Integer qualityId) {
		this.qualityId = qualityId;
	}

	@Column(name = "name", unique = true, nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "relationQuality")
	public Set<Relation> getRelations() {
		return this.relations;
	}

	public void setRelations(Set<Relation> relations) {
		this.relations = relations;
	}

}
