package com.teamtrade.rfp.service;

import java.util.Set;

import com.teamtrade.rfp.model.Relation;
import com.teamtrade.rfp.model.RelationQuality;
import com.teamtrade.rfp.model.RelationType;

public interface RelationService {
	Relation findById(int id);
	int save(Relation r);
	void update(Relation r);
	void deleteById(int id);
	
	Set<RelationType> getAllRelationTypes();
	Set<RelationQuality> getAllRelationQualities();
}
