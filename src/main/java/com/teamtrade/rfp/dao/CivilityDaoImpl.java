package com.teamtrade.rfp.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.teamtrade.rfp.model.Civility;

@Repository("civilityDao")
public class CivilityDaoImpl extends AbstractDao<Integer, Civility> implements CivilityDao {

	@Override
	public Civility getCivilityByName(String name) {
		Query query =  getSession().createQuery("from Civility where name=:pname").setParameter("pname", name);
		return (Civility) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Set<Civility> getCivilities() {
		Criteria criteria = createEntityCriteria();
		return new HashSet<>((List<Civility>) criteria.list());
		
	}

	@Override
	public int save(Civility civ) {
		return (int) getSession().save(civ);
	}

}
