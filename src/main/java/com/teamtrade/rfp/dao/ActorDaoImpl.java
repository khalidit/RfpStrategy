package com.teamtrade.rfp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.teamtrade.rfp.model.Actor;

@Repository("actorDao")
public class ActorDaoImpl extends AbstractDao<Integer, Actor> implements ActorDao {

	public Actor findById(int id) {
		Actor actor = getByKey(id);
		return actor;
	}

	public int saveActor(Actor actor) {
		return (int) getSession().save(actor);		
	}
	
	public List<Actor> findAllActors() {
		 Criteria criteria = createEntityCriteria().addOrder(Order.asc("name"));
	     criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
	     @SuppressWarnings("unchecked")
		List<Actor> actors = (List<Actor>) criteria.list();
	     return actors;
	}

	@Override
	public void deleteActorById(int id) {
		Actor actor = getByKey(id);
		delete(actor);
		
	}

	@Override
	public void update(Actor a) {
		getSession().saveOrUpdate(a);
	}
	
}
