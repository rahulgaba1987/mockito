package com.boot.serviceimplementation;

import java.util.List;
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

	private EmployeeRepository employeeRepository;
	
	public EmployeeServiceImplementation(EmployeeRepository employeeRepository ) 
	{
		this.employeeRepository=employeeRepository;
	}
	
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

	@Override
	public List<EmployeeEntity> getAllEmployees() 
	{
			return employeeRepository.findAll();
	}

	@Override
	public Optional<EmployeeEntity> getEmployeeById(Long employeeId) 
	{
		Optional<EmployeeEntity> employeeObj = employeeRepository.findById(employeeId);
		
		if(employeeObj.isPresent())
		{
			return employeeObj;
		}
		else
		{
			throw new ResourceNotFoundException("Employee obj does not exist with employee ID  "+employeeId);
		}
		
	}

	@Override
	public EmployeeEntity updateEmployee(EmployeeEntity updateEmployee) 
	{
		
        Optional<EmployeeEntity> employeeObj = employeeRepository.findById(updateEmployee.getEmployeeId());
		
		if(employeeObj.isPresent())
		{
			return employeeRepository.save(updateEmployee);
		}
		else
		{
			throw new ResourceNotFoundException("Employee obj does not exist with employee ID  "+updateEmployee.getEmployeeId());
		}
	}

	@Override
	public void deleteEmployee(Long employeeId)
	{
		 Optional<EmployeeEntity> employeeObj = employeeRepository.findById(employeeId);
			
		 if(employeeObj.isPresent())
		 {
			  employeeRepository.deleteById(employeeId);
		 }
		 else
		 {
			 throw new ResourceNotFoundException("Employee obj does not exist with employee ID  "+employeeId);
		 }
		
	}

}
