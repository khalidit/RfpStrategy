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
 * Define the type of relation between two actors as : Friendship, Hierarchy, Influence, Previous collaboration
 * @author Khalid.ALIANNE
 *
 */
@Entity
@Table(name = "RelationType", catalog = DEFAULT_CATALOG, uniqueConstraints = @UniqueConstraint(columnNames = "name") )
public class RelationType implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer typeId;
	private String name;
	private Set<Relation> relations = new HashSet<Relation>(0);

	public RelationType() {
	}

	public RelationType(String name) {
		this.name = name;
	}

	public RelationType(String name, Set<Relation> relations) {
		this.name = name;
		this.relations = relations;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "type_id", unique = true, nullable = false)
	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	@Column(name = "name", unique = true, nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "relationType")
	public Set<Relation> getRelations() {
		return this.relations;
	}

	public void setRelations(Set<Relation> relations) {
		this.relations = relations;
	}

}
