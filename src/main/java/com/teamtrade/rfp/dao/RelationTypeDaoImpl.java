package com.teamtrade.rfp.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.teamtrade.rfp.model.RelationType;

@Repository("relationTypeDao")
public class RelationTypeDaoImpl extends AbstractDao<Integer, RelationType> implements RelationTypeDao {

	
	@SuppressWarnings("unchecked")
	@Override
	public Set<RelationType> getAllRelationTypes() {
		Criteria criteria = createEntityCriteria();
		return new HashSet<>((List<RelationType>) criteria.list());
	}

}
