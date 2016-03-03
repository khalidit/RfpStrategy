package com.teamtrade.rfp.dao;

import java.util.Set;

import com.teamtrade.rfp.model.Person;

public interface PersonDao {
	Set<Person> findAllPersons();
}
