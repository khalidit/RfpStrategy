package com.teamtrade.rfp.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.teamtrade.rfp.model.Department;

@Repository("departmetDao")
public class DepartmentDaoImpl extends AbstractDao<Integer, Department> implements DepartmentDao {

	@SuppressWarnings("unchecked")
	public Set<Department> findAllDepartments() {
		 Criteria criteria = createEntityCriteria();
	     return new HashSet<>((List<Department>) criteria.list());
	}

	@Override
	public Department findById(int id) {
		return getByKey(id);
	}

	@Override
	public void save(Long actorId, Department dep) {
//		SQLQuery query = getSession().createSQLQuery("INSERT INTO Company (`id`, `siren_number`, `logo`) VALUES (?, ?, ?)");
//		query.setParameter(0, actorId);
//		query.setParameter(1, company.getSirenNumber());
//		query.setParameter(2, company.getLogo());
//		query.executeUpdate();		
	}

	@Override
	public void deleteById(int departmentId) {
		Department p = new Department();
		p.setDepartmentId(departmentId);
		delete(p);
	}
}
