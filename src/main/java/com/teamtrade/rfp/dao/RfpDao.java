package com.teamtrade.rfp.dao;

import java.util.List;

import com.teamtrade.rfp.model.Rfp;

public interface RfpDao {	
	Rfp findRfpById(int id);
	List<Rfp> findAllRfp();
    void saveRfp(Rfp r);
	void updateRfp(Rfp r);
	void deleteRfpById(int id);
}
