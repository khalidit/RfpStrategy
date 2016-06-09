package com.teamtrade.rfp.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.teamtrade.rfp.model.Company;

@Repository("companyDao")
public class CompanyDaoImpl extends AbstractDao<Integer, Company> implements CompanyDao {

	@SuppressWarnings("unchecked")
	public Set<Company> findAllCompanies() {
		 Criteria criteria = createEntityCriteria();
	     return new HashSet<>((List<Company>) criteria.list());
	}

	@Override
	public Company findById(int id) {
		return getByKey(id);
	}

	@Override
	public void save(Long actorId, Company company) {
		SQLQuery query = getSession().createSQLQuery("INSERT INTO Company (`id`, `siren_number`, `logo`) VALUES (?, ?, ?)");
		query.setParameter(0, actorId);
		query.setParameter(1, company.getSirenNumber());
		query.setParameter(2, company.getLogo());
		query.executeUpdate();		
	}

	@Override
	public void deleteById(int id) {
		Company p = new Company();
		p.setActorId(id);
		delete(p);
	}
}
