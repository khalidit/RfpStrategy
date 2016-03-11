package com.teamtrade.rfp.dao;

import java.util.Set;

import com.teamtrade.rfp.model.Person;

public interface PersonDao {
	Set<Person> findAllPersons();
	Person findById(int id);
	void save(Long actorId, Person person);
	void deleteById(int id);
	void update(Person person);
}
