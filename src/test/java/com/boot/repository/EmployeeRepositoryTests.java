package com.boot.repository;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.boot.entity.EmployeeEntity;



@DataJpaTest
public class EmployeeRepositoryTests 
{
    @Autowired	
	private EmployeeRepository employeeRepository;
    
    private EmployeeEntity employee;
    
   Logger log =  LoggerFactory.getLogger(EmployeeRepositoryTests.class);
    
    //Junit test for save employee
    
    public void given_when_then()
    {
    	//given :- precondition or tets
    	
    	// when :- action or behavior that is going to be test
    	
    	// then :- verify the output
    }
    
    @BeforeEach
    public void setup()
    {
    	 employee = EmployeeEntity.builder()
                .firstName("ram")
                .lastName("kumar")
                .email("ram@gmail.com")
                .build();
    }
    @Test
    @DisplayName("Junit test for save employee")
    public void givenEmployeeObject_whenSave_thenReturnedSavedEmployee()
    {
    	//given employee object
    	log.info("Junit test for save operation in employee log");
    	// when :- action or behavior that is going to be test
        EmployeeEntity savedEmployee = employeeRepository.save(employee);
     
         // then :- verify the output
	     Assertions.assertThat(savedEmployee).isNotNull();
	     Assertions.assertThat(savedEmployee.getEmployeeId()).isGreaterThan(0);
    	
    	
    }
    
    @Test
    @DisplayName("Junit test for get all employees")
    public void givenAllEmployeeObject_whenSaved_thenReturnedAllSavedEmployee()
    {
    	//given employee object


    	
    	EmployeeEntity employee2 = EmployeeEntity.builder()
                .firstName("mohan")
                .lastName("verma")
                .email("mohan@gmail.com")
                .build();
    	
    	 employeeRepository.save(employee);
    	 employeeRepository.save(employee2);
    	
    	// when :- action or behavior that is going to be test
          List<EmployeeEntity> employeeList = employeeRepository.findAll();
     
         // then :- verify the output
	     Assertions.assertThat(employeeList).isNotNull();
	     Assertions.assertThat(employeeList.size()).isEqualTo(2);
    	
    	
    }
    
    @Test
    @DisplayName("Junit test for find employee By Id")
    public void givenEmployeeObject_whenFindByEmployeeId_thenReturnedEmployeeObj()
    {
    	//given employee object
//    	EmployeeEntity employee = EmployeeEntity.builder()
//    			                  .firstName("ram")
//    			                  .lastName("kumar")
//    			                  .email("ram@gmail.com")
//    			                  .build();
    	
    	
    	 employeeRepository.save(employee);
    	
    	
    	// when :- action or behavior that is going to be test
          EmployeeEntity employeeObj = employeeRepository.findById(employee.getEmployeeId()).get();
     
         // then :- verify the output
	     Assertions.assertThat(employeeObj).isNotNull();
	     Assertions.assertThat(employeeObj.getEmployeeId()).isGreaterThan(0);
    	
    	
    }
    
    
    @Test
    @DisplayName("Junit test for find employee By Email")
    public void givenEmployeeObject_whenFindByEmail_thenReturnedEmployeeObj()
    {
    	//given employee object
//    	EmployeeEntity employee = EmployeeEntity.builder()
//    			                  .firstName("ram")
//    			                  .lastName("kumar")
//    			                  .email("ram@gmail.com")
//    			                  .build();
    	
    	
    	 employeeRepository.save(employee);
    	
    	
    	// when :- action or behavior that is going to be test
          EmployeeEntity employeeObj = employeeRepository.findByEmail(employee.getEmail()).get();
     
         // then :- verify the output
	     Assertions.assertThat(employeeObj).isNotNull();
	     Assertions.assertThat(employeeObj.getEmail()).isEqualTo("ram@gmail.com");
    	
    	
    }
    
    
    @Test
    @DisplayName("Junit test for update employee object")
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnedUpdatedEmployeeObj()
    {
    	//given employee object
//    	EmployeeEntity employee = EmployeeEntity.builder()
//    			                  .firstName("ram")
//    			                  .lastName("kumar")
//    			                  .email("ram@gmail.com")
//    			                  .build();
    	
    	
    	 employeeRepository.save(employee);
    	
    	
    	// when :- action or behavior that is going to be test
    	 EmployeeEntity employeeEntity = employeeRepository.findById(employee.getEmployeeId()).get();
    	 employeeEntity.setEmail("abc@gmail.com");
          EmployeeEntity updatedEmployee = employeeRepository.save(employeeEntity);
     
         // then :- verify the output
	     Assertions.assertThat(updatedEmployee).isNotNull();
	     Assertions.assertThat(updatedEmployee.getEmail()).isEqualTo("abc@gmail.com");
    	
    	
    }
    
    
    @Test
    @DisplayName("Junit test for delete employee object")
    public void givenEmployeeObject_whenDeleteEmployee_thenReturnedRemoveEmployeeObj()
    {
    	//given employee object
//    	EmployeeEntity employee = EmployeeEntity.builder()
//    			                  .firstName("ram")
//    			                  .lastName("kumar")
//    			                  .email("ram@gmail.com")
//    			                  .build();
//    	
    	
    	 employeeRepository.save(employee);
    	
    	
    	// when :- action or behavior that is going to be test
    	 employeeRepository.delete(employee);
    	 Optional<EmployeeEntity> employeeObj = employeeRepository.findById(employee.getEmployeeId());
    	
     
         // then :- verify the output
	     Assertions.assertThat(employeeObj).isEmpty();
	    
    	
    	
    }

    @Test
    @DisplayName("Junit test for custom JPQL query with index parameter")
    public void givenEmployeeFirstNameAndLastName_whenFindByJPQL_thenReturningEmployeeObject()
    {
    	//given :- precondition or test
    	//given employee object
//    	EmployeeEntity employee = EmployeeEntity.builder()
//    			                  .firstName("ram")
//    			                  .lastName("kumar")
//    			                  .email("ram@gmail.com")
//    			                  .build();
    	
    	 employeeRepository.save(employee);
    	 
    	// when :- action or behavior that is going to be test
    	EmployeeEntity employeeObj = employeeRepository.findByJPQL("ram", "kumar");
    	
    	// then :- verify the output
    	Assertions.assertThat(employeeObj).isNotNull();
    	Assertions.assertThat(employeeObj.getLastName()).isEqualTo("kumar");
    	
    	
    }
    

    @Test
    @DisplayName("Junit test for custom JPQL query with named parameter")
    public void givenEmployeeFirstNameAndLastName_whenFindByJPQLNamedParams_thenReturningEmployeeObject()
    {
    	//given :- precondition or test
    	//given employee object
//    	EmployeeEntity employee = EmployeeEntity.builder()
//    			                  .firstName("ram")
//    			                  .lastName("kumar")
//    			                  .email("ram@gmail.com")
//    			                  .build();
    	
    	 employeeRepository.save(employee);
    	 
    	// when :- action or behavior that is going to be test
    	EmployeeEntity employeeObj = employeeRepository.findByNamedParams("ram", "kumar");
    	
    	// then :- verify the output
    	Assertions.assertThat(employeeObj).isNotNull();
    	Assertions.assertThat(employeeObj.getLastName()).isEqualTo("kumar");
    	
    	
    }
    @Test
    @DisplayName("Junit test for custom Native query with named parameter")
    public void givenEmployeeFirstNameAndLastName_whenFindByNativeNamedParams_thenReturningEmployeeObject()
    {
    	//given :- precondition or test
    	//given employee object
//    	EmployeeEntity employee = EmployeeEntity.builder()
//    			                  .firstName("ram")
//    			                  .lastName("kumar")
//    			                  .email("ram@gmail.com")
//    			                  .build();
    	
    	 employeeRepository.save(employee);
    	 
    	// when :- action or behavior that is going to be test
    	EmployeeEntity employeeObj = employeeRepository.findByNativeNamedParams("ram", "kumar");
    	
    	// then :- verify the output
    	Assertions.assertThat(employeeObj).isNotNull();
    	Assertions.assertThat(employeeObj.getLastName()).isEqualTo("kumar");
    	
    	
    }
    
    


    
    


    
    








}
