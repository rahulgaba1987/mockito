package com.boot.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.boot.entity.EmployeeEntity;
import com.boot.exception.ResourceNotFoundException;
import com.boot.repository.EmployeeRepository;
import com.boot.serviceimplementation.EmployeeServiceImplementation;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests 
{
	@Mock
	private EmployeeRepository employeeRepository;
	
	@InjectMocks
	private EmployeeServiceImplementation employeeService;
	
	private EmployeeEntity employee;
	
	@BeforeEach
	public void setup()
	{
		//employeeRepository= Mockito.mock(EmployeeRepository.class);
		
		//employeeService= new EmployeeServiceImplementation(employeeRepository);
		 employee =EmployeeEntity.builder()
                .employeeId(1L)
                .firstName("rahul")
                .lastName("gaba")
                .email("rahul@gmail.com").build();

	}
	
	@Test
	@DisplayName("Junit test for save employee")
	public void givenEmployeeObject_whenSavedemployee_thenReturnedEmployeeObject()
	
    {
    	//given :- precondition or test
//		EmployeeEntity employee =EmployeeEntity.builder()
//				                 .employeeId(1L)
//				                 .firstName("rahul")
//				                 .lastName("gaba")
//				                 .email("rahul@gmail.com").build();
		
		BDDMockito.given(employeeRepository.findByEmail(employee.getEmail()))
		          .willReturn(Optional.empty());
		
		BDDMockito.given(employeeRepository.save(employee)).willReturn(employee);
		
    	
    	// when :- action or behavior that is going to be test
		EmployeeEntity savedEmployee = employeeService.saveEmployee(employee);
    	
    	// then :- verify the output
		
		Assertions.assertThat(savedEmployee).isNotNull();
    }
	@Test
	@DisplayName("Junit test for save employee method which throws exception")
	public void givenExistngEmail_whenSavedemployee_thenThrowException()
	
    {

		
		BDDMockito.given(employeeRepository.findByEmail(employee.getEmail()))
		          .willReturn(Optional.of(employee));

		
    	
    	// when :- action or behavior that is going to be test
		org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class,()-> employeeService.saveEmployee(employee));
    	
    	// then :- verify the output

		Mockito.verify(employeeRepository,Mockito.never()).save(Mockito.any(EmployeeEntity.class));
    }
	
	@Test
	@DisplayName("Junit test for get all employees")
	public void givenEmployeeList_whenFindAllEmployees_thenReturnAllEmployees()
    {
		EmployeeEntity employee2 =EmployeeEntity.builder()
	                .employeeId(2L)
	                .firstName("karanrahul")
	                .lastName("gaba")
	                .email("karan@gmail.com").build();
		
		BDDMockito.given(employeeRepository.findAll())
		          .willReturn(List.of(employee,employee2));

		
   	
    	// when :- action or behavior that is going to be test
	    List<EmployeeEntity> allEmployeesList = employeeService.getAllEmployees();
    	
    	// then :- verify the output
        Assertions.assertThat(allEmployeesList).isNotNull();
        Assertions.assertThat(allEmployeesList.size()).isEqualTo(2);
	
    }
	
	@Test
	@DisplayName("Junit test for get all employees (Negative Scenario)")
	public void givenEmptyEmployeeList_whenFindAllEmployees_thenReturnEmptyEmployeesList()
    {
		
		
		BDDMockito.given(employeeRepository.findAll())
		          .willReturn(Collections.emptyList());

		
   	
    	// when :- action or behavior that is going to be test
	    List<EmployeeEntity> allEmployeesList = employeeService.getAllEmployees();
    	
    	// then :- verify the output
        Assertions.assertThat(allEmployeesList).isEmpty();;
        Assertions.assertThat(allEmployeesList.size()).isEqualTo(0);
	
    }
	
	@Test
	@DisplayName("Junit test for get employee by Id")
	public void givenEmployeeId_whenFindEmployeeById_thenReturnEmployees()
    {
		
		
		BDDMockito.given(employeeRepository.findById(employee.getEmployeeId()))
		          .willReturn(Optional.of(employee));

		
   	
    	// when :- action or behavior that is going to be test
	    EmployeeEntity allEmployeesList = employeeService.getEmployeeById(employee.getEmployeeId()).get();
    	
    	// then :- verify the output
        Assertions.assertThat(allEmployeesList).isNotNull();;
 
	
    }
	
	@Test
	@DisplayName("Junit test for update employee")
	public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployees()
    {
		
		
		BDDMockito.given(employeeRepository.findById(employee.getEmployeeId()))
		          .willReturn(Optional.of(employee));
		
		BDDMockito.given(employeeRepository.save(employee))
        .willReturn(employee);

		employee.setFirstName("Mohan");
		employee.setLastName("Verma");
   	
    	// when :- action or behavior that is going to be test
	    EmployeeEntity employeeObj = employeeService.updateEmployee(employee);
    	
    	// then :- verify the output
        Assertions.assertThat(employeeObj).isNotNull();
        Assertions.assertThat(employeeObj.getFirstName()).isEqualTo("Mohan");
 
	
    }
	
	@Test
	@DisplayName("Junit test for delete employee")
	public void givenEmployeeId_whenDeleteEmployee_thenEmployeesObjDeleted()
    {
		
		
		BDDMockito.given(employeeRepository.findById(employee.getEmployeeId()))
		          .willReturn(Optional.of(employee));
		
		BDDMockito.willDoNothing().given(employeeRepository).deleteById(employee.getEmployeeId());
       

		
   	
    	// when :- action or behavior that is going to be test
	    employeeService.deleteEmployee(employee.getEmployeeId());
    	
    	// then :- verify the output
       Mockito.verify(employeeRepository,Mockito.times(1)).deleteById(employee.getEmployeeId());
        
 
	
    }










}
