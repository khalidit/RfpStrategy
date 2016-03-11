package com.teamtrade.rfp.dao;

import static com.teamtrade.rfp.constants.Constants.LAST_INSERTED_ID;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.teamtrade.rfp.model.Actor;

@Repository("actorDao")
public class ActorDaoImpl extends AbstractDao<Integer, Actor> implements ActorDao {

	public Actor findById(int id) {
		Actor actor = getByKey(id);
		return actor;
	}

	/**
	 * return -1 if already exists
	 */
	public Object[] saveActor(Actor actor) {
		Object[] res = new Object[2];
		Query aQuery = getSession().createQuery("from Actor a where upper(a.name) = :name");
		aQuery.setParameter("name", actor.getName().toUpperCase());
		Actor a = (Actor) aQuery.uniqueResult();
		if(a != null){
			res[0] = a.getActorId().longValue();
			res[1] = Boolean.TRUE;
			return res;
		}
		SQLQuery query = getSession().createSQLQuery("INSERT INTO Actor (`name`, `appreciation`, `actor_type`) VALUES (:name, :appreciation, :type)");
		query.setParameter("name", actor.getName());
		query.setParameter("appreciation", actor.getAppreciation().getAppreciationId());
		query.setParameter("type", actor.getActorType().getActorTypeId());
		query.executeUpdate();	
		
		res[0] = ((BigInteger) getSession().createSQLQuery(LAST_INSERTED_ID).uniqueResult()).longValue();
		res[1] = Boolean.FALSE;
		return res;
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
