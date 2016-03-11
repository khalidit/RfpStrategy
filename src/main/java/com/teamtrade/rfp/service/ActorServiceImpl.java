package com.teamtrade.rfp.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamtrade.rfp.dao.ActorDao;
import com.teamtrade.rfp.dao.ActorRoleDao;
import com.teamtrade.rfp.dao.ActorTypeDao;
import com.teamtrade.rfp.dao.CivilityDao;
import com.teamtrade.rfp.dao.CompanyDao;
import com.teamtrade.rfp.dao.DepartmentDao;
import com.teamtrade.rfp.dao.PersonDao;
import com.teamtrade.rfp.dao.AppreciationDao;
import com.teamtrade.rfp.model.Actor;
import com.teamtrade.rfp.model.ActorRole;
import com.teamtrade.rfp.model.ActorType;
import com.teamtrade.rfp.model.Civility;
import com.teamtrade.rfp.model.Company;
import com.teamtrade.rfp.model.Department;
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
	
	@Autowired
	CompanyDao companyDao;
	
	@Autowired
	DepartmentDao departmentDao;
	
	@Override
	public Actor findById(int id) {
		return actorDao.findById(id);
	}
	
	@Override
	public int saveActor(Actor actor) {
		Object[] res = actorDao.saveActor(actor);
		if(!(Boolean)res[1]){ // Si l'acteur n'existes pas, on l'ajoute dans la table des acteurs
			if(actor.getActorType().getActorTypeId() == 2){
				System.out.println("Adding new Person...");
				personDao.save((Long)res[0], (Person) actor);
			}else{
				System.out.println("Adding new Company...");
				companyDao.save((Long)res[0], (Company) actor);
			}
		}
		return ((Long)res[0]).intValue();
		
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
	
	@Override
	public Set<Company> getAllCompanies() {
		return companyDao.findAllCompanies();
	}
	
	@Override
	public Set<Department> getAllDepartments() {
		return departmentDao.findAllDepartments();
	}

	@Override
	public int saveCivility(Civility civ) {
		Civility c = civilityDao.getCivilityByName(civ.getName());
		if(c!= null){
			System.out.println("Civilitity "+civ.getName()+" already exists");
			return -1;
		}
		return civilityDao.save(civ);
	}
}
