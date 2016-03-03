package com.teamtrade.rfp.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamtrade.rfp.dao.ActorDao;
import com.teamtrade.rfp.dao.ActorRoleDao;
import com.teamtrade.rfp.dao.ActorTypeDao;
import com.teamtrade.rfp.dao.CivilityDao;
import com.teamtrade.rfp.dao.PersonDao;
import com.teamtrade.rfp.dao.AppreciationDao;
import com.teamtrade.rfp.model.Actor;
import com.teamtrade.rfp.model.ActorRole;
import com.teamtrade.rfp.model.ActorType;
import com.teamtrade.rfp.model.Civility;
import com.teamtrade.rfp.model.Person;
import com.teamtrade.rfp.model.Appreciation;

@Service("actorService")
@Transactional
public class ActorServiceImpl implements ActorService{
	
	@Autowired
	ActorDao actorDao;
	
	@Autowired
	ActorTypeDao actorTypeDao;
	
	@Autowired
	ActorRoleDao actorRoleDao;
	
	@Autowired
	AppreciationDao appreciationDao;
	
	@Autowired
	CivilityDao civilityDao;
	
	@Autowired
	PersonDao personDao;
	
	@Override
	public Actor findById(int id) {
		return actorDao.findById(id);
	}
	
	@Override
	public int saveActor(Actor a) {
		return actorDao.saveActor(a);
		
	}
	
	@Override
	public void deleteActorById(int id) {
		actorDao.deleteActorById(id);
		
	}
	
	@Override
	public void updateActor(Actor a) {
		actorDao.update(a);
		
	}
	
	@Override
	public Set<ActorType> getAllActorTypes() {
		return actorTypeDao.getAllActorTypes();
	}
	
	@Override
	public Set<ActorRole> getAllActorRoles() {
		return actorRoleDao.getAllActorRoles();
	}
	
	@Override
	public Set<Appreciation> getAllAppreciations() {
		return appreciationDao.getAllAppreciations();
	}

	@Override
	public Set<Civility> getCivilities() {
		return civilityDao.getCivilities();
	}

	@Override
	public Set<Person> getAllPersons() {
		return personDao.findAllPersons();
	}
}
