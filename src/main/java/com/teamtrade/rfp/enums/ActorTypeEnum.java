package com.teamtrade.rfp.enums;

import com.teamtrade.rfp.model.Company;
import com.teamtrade.rfp.model.Person;

/**
 * 
 * @author Khalid.ALIANNE
 *
 */
public enum ActorTypeEnum {
	
	COMPANY(1, Company.class),
	PERSON(2, Person.class);
	
	public int id;
	public Class<?> model;
	
	private ActorTypeEnum(int id, Class<?> model) {
		this.id = id;
		this.model = model;
	}

}
