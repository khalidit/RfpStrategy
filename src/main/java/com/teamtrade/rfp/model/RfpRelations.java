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
 * Defines a relation of an Rfp
 * @author Khalid.ALIANNE
 *
 */
@Entity
@Table(name = "RfpRelations", catalog = DEFAULT_CATALOG)
public class RfpRelations implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer rfpRelationsId;
	private Relation relation;
	private Rfp rfp;

	public RfpRelations() {
	}

	public RfpRelations(Relation relation, Rfp rfp) {
		this.relation = relation;
		this.rfp = rfp;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "rfp_relations_id", unique = true, nullable = false)
	public Integer getRfpRelationsId() {
		return this.rfpRelationsId;
	}

	public void setRfpRelationsId(Integer rfpRelationsId) {
		this.rfpRelationsId = rfpRelationsId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "relation_id", nullable = false)
	public Relation getRelation() {
		return this.relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
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
