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
 * The status of RFP : In Progress, Lost, Short Listed, Won
 * @author Khalid.ALIANNE
 *
 */
@Entity
@Table(name = "RfpStatus", catalog = DEFAULT_CATALOG, uniqueConstraints = @UniqueConstraint(columnNames = "name") )
public class RfpStatus implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer rfpStatusId;
	private String name;
	private Set<Rfp> rfps = new HashSet<Rfp>(0); // All of RFP having this status

	public RfpStatus() {
	}

	public RfpStatus(String name) {
		this.name = name;
	}

	public RfpStatus(String name, Set<Rfp> rfps) {
		this.name = name;
		this.rfps = rfps;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "rfp_status_id", unique = true, nullable = false)
	public Integer getRfpStatusId() {
		return this.rfpStatusId;
	}

	public void setRfpStatusId(Integer rfpStatusId) {
		this.rfpStatusId = rfpStatusId;
	}

	@Column(name = "name", unique = true, nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rfpStatus")
	public Set<Rfp> getRfps() {
		return this.rfps;
	}

	public void setRfps(Set<Rfp> rfps) {
		this.rfps = rfps;
	}

}
