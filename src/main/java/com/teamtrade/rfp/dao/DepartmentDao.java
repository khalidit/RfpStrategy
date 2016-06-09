package com.teamtrade.rfp.dao;

import java.util.Set;

import com.teamtrade.rfp.model.Department;

public interface DepartmentDao {
	Set<Department> findAllDepartments();
	Department findById(int id);
	void save(Long actorId, Department dep);
	void deleteById(int id);
	void update(Department dep);
}
