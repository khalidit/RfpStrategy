package com.teamtrade.rfp.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.teamtrade.rfp.model.ActorRole;

@Repository("actorRoleDao")
public class ActorRoleDaoImpl extends AbstractDao<Integer, ActorRole> implements ActorRoleDao {

	
	@SuppressWarnings("unchecked")
	@Override
	public Set<ActorRole> getAllActorRoles() {
		Criteria criteria = createEntityCriteria();
		return new HashSet<>((List<ActorRole>) criteria.list());
		
	}

}
