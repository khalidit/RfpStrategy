package com.teamtrade.rfp.dao;

import java.util.Set;

import com.teamtrade.rfp.model.Company;

public interface CompanyDao {
	Set<Company> findAllCompanies();
	Company findById(int id);
	void save(Long actorId, Company company);
	void deleteById(int id);
	void update(Company person);
}
