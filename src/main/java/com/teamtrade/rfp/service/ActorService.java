package com.teamtrade.rfp.service;

import java.util.Set;

import com.teamtrade.rfp.model.Actor;
import com.teamtrade.rfp.model.ActorRole;
import com.teamtrade.rfp.model.ActorType;
import com.teamtrade.rfp.model.Civility;
import com.teamtrade.rfp.model.Company;
import com.teamtrade.rfp.model.Department;
import com.teamtrade.rfp.model.Person;
import com.teamtrade.rfp.model.Appreciation;

public interface ActorService {
	
	Actor findById(int id);
	int saveActor(Actor a);
	void updateActor(Actor a);
	void deleteActorById(int id);
	
	int saveCivility(Civility civ);
	
	Set<ActorType> getAllActorTypes();
	Set<ActorRole> getAllActorRoles();
	
	Set<Civility> getCivilities();
	
	Set<Person> getAllPersons();
	
	Set<Company> getAllCompanies();
	
	Set<Department> getAllDepartments();
	
	Set<Appreciation> getAllAppreciations();
	

}
