package com.teamtrade.rfp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.teamtrade.rfp.model.Rfp;

@Repository("rfpDao")
public class RfpDaoImpl extends AbstractDao<Integer, Rfp> implements RfpDao {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Rfp findRfpById(int id) {		
		Rfp rfp = getByKey(id);
//		if(rfp!=null) {
//			
//			Query queryActors = getSession().createSQLQuery("select * from RfpActors ra where ra.rfp_id = :rfp_id").setParameter("rfp_id", id);
//			rfp.setActors(new HashSet(queryActors.list()));
//			
//			Query queryRelations = getSession().createSQLQuery("select * from RfpRelations rr where rr.rfp_id = :rfp_id").setParameter("rfp_id", id);
//			rfp.setActors(new HashSet(queryRelations.list()));
//			Hibernate.initialize(rfp.getActors());
//			Hibernate.initialize(rfp.getRelations());
//		}
//		return getByKey(id);
		
		return rfp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rfp> findAllRfp() {
		Criteria criteria = createEntityCriteria();
		criteria.addOrder(Order.asc("name"));
		return (List<Rfp>) criteria.list();
	}
	
	@Override
	public void saveRfp(Rfp r) {
		getSession().saveOrUpdate(r);
		
	}

	@Override
	public void updateRfp(Rfp r) {		
		getSession().saveOrUpdate(r);
		
	}

	@Override
	public void deleteRfpById(int id) {
		if(findRfpById(id) != null)	delete(findRfpById(id));		
	}

}
