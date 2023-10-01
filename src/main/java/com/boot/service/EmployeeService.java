package com.boot.service;

<<<<<<< HEAD
import java.util.List;
import java.util.Optional;
=======
>>>>>>> 80cd25995981557a974305fb8a34ead9478ecf9d

import com.boot.entity.EmployeeEntity;

public interface EmployeeService 
{
	EmployeeEntity saveEmployee(EmployeeEntity employeeEntity);
<<<<<<< HEAD
	
	List<EmployeeEntity> getAllEmployees();
	
	Optional<EmployeeEntity>  getEmployeeById(Long employeeId);
	
	EmployeeEntity updateEmployee(EmployeeEntity updateEmployee);
	public void deleteEmployee(Long employeeId);
=======
public interface EmployeeService {

>>>>>>> 80cd25995981557a974305fb8a34ead9478ecf9d

}
