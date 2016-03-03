package com.teamtrade.rfp.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.teamtrade.rfp.model.Person;

@Repository("personDao")
public class PersonDaoImpl extends AbstractDao<Integer, Person> implements PersonDao {

	@SuppressWarnings("unchecked")
	public Set<Person> findAllPersons() {
		 Criteria criteria = createEntityCriteria().addOrder(Order.asc("name"));
	     return new HashSet<>((List<Person>) criteria.list());
	}
}
