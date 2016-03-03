package com.teamtrade.rfp.dao;

import java.util.List;

import com.teamtrade.rfp.model.Relation;

public interface RelationDao {
	Relation findById(int id);
	  
    int saveRelation(Relation relation);
    
    void  updateRelation(Relation r);
    
    void deleteRelationById(int id);
     
    List<Relation> findAllRelation();
 
  
}
