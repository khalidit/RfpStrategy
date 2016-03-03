package com.teamtrade.rfp.service;

import java.util.List;
import java.util.Map;

import com.teamtrade.rfp.model.Rfp;

public interface RfpService {	 
	
	Rfp findById(Integer id);		
	List<Rfp> findAllRfp();
	void saveRfp(Rfp r) ;
	void updateRfp(Rfp r);
	void deleteRfpById(int id);
	Map<String, Integer> getIndicators();
 }
