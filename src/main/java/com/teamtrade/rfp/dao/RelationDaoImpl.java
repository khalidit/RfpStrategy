package com.teamtrade.rfp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.teamtrade.rfp.model.Relation;

@Repository("relationDao")
public class RelationDaoImpl extends AbstractDao<Integer, Relation> implements RelationDao{

	public Relation findById(int id) {		
		Relation r =  getByKey(id);		
		return r;
	}

	public int saveRelation(Relation relation) {
		return (int) getSession().save(relation);			
	}

	
	@SuppressWarnings("unchecked")
	public List<Relation> findAllRelation() {
		Criteria crit = createEntityCriteria();
	    crit.addOrder(Order.asc("name"));
		return  (List<Relation>)crit.list();
	}

	public void deleteRelationById(int id) {
		delete(getByKey(id));
		
	}

	@Override
	public void updateRelation(Relation r) {
		getSession().saveOrUpdate(r);		
	}


}
