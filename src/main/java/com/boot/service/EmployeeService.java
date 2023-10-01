package com.boot.service;


import java.util.List;
import java.util.Optional;


import com.boot.entity.EmployeeEntity;

public interface EmployeeService 
{
	EmployeeEntity saveEmployee(EmployeeEntity employeeEntity);

	List<EmployeeEntity> getAllEmployees();
	
	Optional<EmployeeEntity>  getEmployeeById(Long employeeId);
	
	EmployeeEntity updateEmployee(EmployeeEntity updateEmployee);
	public void deleteEmployee(Long employeeId);



}
