package com.teamtrade.rfp.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.teamtrade.rfp.model.Actor;
import com.teamtrade.rfp.model.ActorRole;
import com.teamtrade.rfp.model.Relation;
import com.teamtrade.rfp.model.Rfp;

@Repository("rfpDao")
public class RfpDaoImpl extends AbstractDao<Integer, Rfp> implements RfpDao {

	@SuppressWarnings({ })
	public Rfp findRfpById(int rfpId) {		
		Rfp rfp = getByKey(rfpId);
		return rfp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Rfp> findAllRfp() {
		Criteria criteria = createEntityCriteria();
		criteria.addOrder(Order.asc("name"));
		return new HashSet((List<Rfp>) criteria.list());
	}
	
	@Override
	public void addActorToRfp(Rfp rfp, Actor actor, ActorRole actorRole) {
		if(findRfpActorById(rfp.getRfpId(), actor.getActorId()) == null){
			SQLQuery query = getSession().createSQLQuery("INSERT INTO RfpActors (rfp_id, actor_id, role_id) VALUES (?, ?, ?)");
			query.setParameter(0, rfp.getRfpId());
			query.setParameter(1, actor.getActorId());
			query.setParameter(2, actorRole.getRoleId());
			query.executeUpdate();
		}else{
			System.out.println("The actor "+actor.getName()+" is already in the Rfp "+rfp.getName());
		}
	}

	@Override
	public void updateRfp(Rfp r) {		
		getSession().saveOrUpdate(r);
		
	}

	@Override
	public void deleteRfpById(int id) {
		if(findRfpById(id) != null)	delete(findRfpById(id));		
	}

	@Override
	public Actor findRfpActorById(int rfpId, int actorId) {
		return (Actor) getSession().createSQLQuery("SELECT a.id, a.name, a.appreciation, a.actor_type FROM RfpActors ra INNER JOIN Actor a ON a.id=ra.actor_id WHERE ra.rfp_id=? AND ra.actor_id=?").setParameter(0, rfpId).setParameter(1, actorId).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getAllRfpActors(int rfpId) {
		SQLQuery query = getSession().createSQLQuery("SELECT ra.rfp_actors_id, ra.rfp_id, ra.actor_id, ra.role_id, a.name as actorName, a.appreciation, a.actor_type, arx.name as roleName FROM RfpActors as ra INNER JOIN Actor as a ON a.id = ra.actor_id INNER JOIN ActorRole as arx ON ra.role_id = arx.role_id where ra.rfp_id=?");
		query.setParameter(0, rfpId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Set<Relation> getAllRfpRelations(int rfpId) {
		SQLQuery query = getSession().createSQLQuery("SELECT r.relation_id, r.actor_from, r.actor_to, r.relation_type_id, r.relation_quality_id, r.relation_title, r.relation_comment FROM RfpRelations rr INNER JOIN Relation r ON r.relation_id=rr.relation_id WHERE rr.rfp_id=?");
		query.setParameter(0, rfpId);
		query.addEntity(Relation.class);
		return new HashSet<Relation>(query.list());
	}

}
