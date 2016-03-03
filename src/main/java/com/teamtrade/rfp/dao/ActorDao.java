package com.teamtrade.rfp.dao;

import java.util.List;

import com.teamtrade.rfp.model.Actor;

public interface ActorDao {
		
	Actor findById(int id);
	  
	int saveActor(Actor person);
	     
	void deleteActorById(int id);
	     
	List<Actor> findAllActors();

	void update(Actor a);
	 
	    
}
