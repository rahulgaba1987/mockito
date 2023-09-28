package com.boot.serviceimplementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.entity.EmployeeEntity;
import com.boot.exception.ResourceNotFoundException;
import com.boot.repository.EmployeeRepository;
import com.boot.service.EmployeeService;

@Service
public class EmployeeServiceImplementation implements EmployeeService
{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public EmployeeEntity saveEmployee(EmployeeEntity employeeEntity) 
	{
		Optional<EmployeeEntity> savedEmployee = employeeRepository.findByEmail(employeeEntity.getEmail());
	
		if(savedEmployee.isPresent())
		{
			throw new ResourceNotFoundException("Employee already exist with given email "+employeeEntity.getEmail());
		}
		else
		{
			return employeeRepository.save(employeeEntity);
			
		}
		
		
		
	}

}
