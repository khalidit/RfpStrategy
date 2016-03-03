package com.teamtrade.rfp.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.teamtrade.rfp.model.Appreciation;

@Repository("appreciationDao")
public class AppreciationDaoImpl extends AbstractDao<Integer, Appreciation> implements AppreciationDao {

	
	@SuppressWarnings("unchecked")
	@Override
	public Set<Appreciation> getAllAppreciations() {
		Criteria criteria = createEntityCriteria();
		return new HashSet<>((List<Appreciation>) criteria.list());
	}

}
