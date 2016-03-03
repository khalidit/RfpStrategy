package com.teamtrade.rfp.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.teamtrade.rfp.model.RelationQuality;

@Repository("relationQualityDao")
public class RelationQualityDaoImpl extends AbstractDao<Integer, RelationQuality> implements RelationQualityDao {

	
	@SuppressWarnings("unchecked")
	@Override
	public Set<RelationQuality> getAllRelationQualities() {
		Criteria criteria = createEntityCriteria();
		return new HashSet<>((List<RelationQuality>) criteria.list());
	}

}
