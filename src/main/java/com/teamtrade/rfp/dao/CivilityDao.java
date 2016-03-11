package com.teamtrade.rfp.dao;

import java.util.Set;

import com.teamtrade.rfp.model.Civility;

/**
 * 
 * @author Khalid.ALIANNE
 *
 */
public interface CivilityDao {
	
	public Civility getCivilityByName(String name);
    public Set<Civility> getCivilities();
	public int save(Civility civ);
}
