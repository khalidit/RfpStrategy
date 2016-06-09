package com.teamtrade.rfp.dao;

import java.util.List;
import java.util.Set;

import com.teamtrade.rfp.model.Actor;
import com.teamtrade.rfp.model.ActorRole;
import com.teamtrade.rfp.model.Relation;
import com.teamtrade.rfp.model.Rfp;
import com.teamtrade.rfp.model.RfpActors;

public interface RfpDao {	
	Rfp findRfpById(int id);
	Set<Rfp> findAllRfp();
	void updateRfp(Rfp r);
	void deleteRfpById(int id);
	Actor findRfpActorById(int rfpId, int actorId);
	List<Object[]> getAllRfpActors(int rfpId);
	Set<Relation> getAllRfpRelations(int rfpId);
	void addActorToRfp(Rfp rfp, Actor actor, ActorRole actorRole);
}
