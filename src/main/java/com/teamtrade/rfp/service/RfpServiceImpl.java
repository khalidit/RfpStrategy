package com.teamtrade.rfp.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamtrade.rfp.dao.RfpDao;
import com.teamtrade.rfp.model.Actor;
import com.teamtrade.rfp.model.ActorRole;
import com.teamtrade.rfp.model.Rfp;

/**
 * @author Khalid.ALIANNE
 *
 */
@Service("rfpService")
@Transactional
public class RfpServiceImpl implements RfpService {

	@Autowired
	private RfpDao dao;
	
	@Override
	public Rfp findById(Integer id) {
		return dao.findRfpById(id);
	}

	@Override
	public Set<Rfp> findAllRfp() {
		return dao.findAllRfp();
	}
	
	@Override
	public void addActorToRfp(Rfp r, Actor actor, ActorRole actorRole) {
		dao.addActorToRfp(r, actor, actorRole);
	}

	@Override
	public void updateRfp(Rfp r) {
		dao.updateRfp(r);
		
	}

	@Override
	public void deleteRfpById(int id) {
	}
	
	public Map<String, Integer> getIndicators(){
		Map<String, Integer> indicators = new HashMap<>();
		Set<Rfp> rfps = findAllRfp();
		
		for(Rfp rfp : rfps){
			String status = rfp.getRfpStatus().getName();
			if(!indicators.containsKey(status)){
				indicators.put(status, 1);
			}else{
				indicators.put(status, indicators.get(status)+1);
			}
		}
		return indicators;
	}

}
