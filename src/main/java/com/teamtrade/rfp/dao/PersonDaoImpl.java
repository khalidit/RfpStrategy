package com.teamtrade.rfp.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.teamtrade.rfp.model.Person;

@Repository("personDao")
public class PersonDaoImpl extends AbstractDao<Integer, Person> implements PersonDao {

	@SuppressWarnings("unchecked")
	public Set<Person> findAllPersons() {
		 Criteria criteria = createEntityCriteria();
	     return new HashSet<>((List<Person>) criteria.list());
	}

	@Override
	public Person findById(int id) {
		return getByKey(id);
	}

	@Override
	public void save(Long actorId, Person person) {
		SQLQuery query = getSession().createSQLQuery("INSERT INTO Person (`id`, `civility`, `_function`, `department`, `manager`, `avatar`, `company`) VALUES (?, ?, ?, ?, ?, ?, ?)");
		query.setParameter(0, actorId);
		query.setParameter(1, person.getCivility().getCivilityId());
		query.setParameter(2, person.getFunction());
		query.setParameter(3, person.getDepartment().getDepartmentId());
		query.setParameter(4, person.getManager().getActorId());
		query.setParameter(5, person.getAvatar());
		query.setParameter(6, person.getCompany().getActorId());
		query.executeUpdate();		
	}

	@Override
	public void deleteById(int id) {
		Person p = new Person();
		p.setActorId(id);
		delete(p);
	}
}
