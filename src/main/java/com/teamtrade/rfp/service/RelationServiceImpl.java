package com.teamtrade.rfp.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamtrade.rfp.dao.RelationDao;
import com.teamtrade.rfp.dao.RelationQualityDao;
import com.teamtrade.rfp.dao.RelationTypeDao;
import com.teamtrade.rfp.model.Relation;
import com.teamtrade.rfp.model.RelationQuality;
import com.teamtrade.rfp.model.RelationType;

@Service("relationService")
@Transactional
public class RelationServiceImpl implements RelationService {

	@Autowired
	RelationDao relationDao;
	
	@Autowired
	RelationTypeDao relationTypeDao;
	
	@Autowired
	RelationQualityDao relationQualityDao;
	
	
	@Override
	public Relation findById(int id) {	
		return relationDao.findById(id);
	}


	@Override
	public int save(Relation r) {
		return relationDao.saveRelation(r);
	}


	@Override
	public void update(Relation r) {
		 relationDao.updateRelation(r);		
	}


	@Override
	public void deleteById(int id) {
		relationDao.deleteRelationById(id);
		
	}


	@Override
	public Set<RelationType> getAllRelationTypes() {
		return relationTypeDao.getAllRelationTypes();
	}


	@Override
	public Set<RelationQuality> getAllRelationQualities() {
		return relationQualityDao.getAllRelationQualities();
	}


	
}
