package com.teamtrade.rfp.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.teamtrade.rfp.model.ActorType;

@Repository("actorTypeDao")
public class ActorTypeDaoImpl extends AbstractDao<Integer, ActorType> implements ActorTypeDao {

	
	@SuppressWarnings("unchecked")
	@Override
	public Set<ActorType> getAllActorTypes() {
		Criteria criteria = createEntityCriteria();
		return new HashSet<>((List<ActorType>) criteria.list());
		
	}

}
