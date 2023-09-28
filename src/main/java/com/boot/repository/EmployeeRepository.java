package com.boot.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.boot.entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long>
{
	
       Optional<EmployeeEntity>  findByEmail(String email);
       
       //defined custom query using JPQL with index parameter
       @Query("select e from EmployeeEntity e where e.firstName=?1 and lastName=?2")
       EmployeeEntity  findByJPQL(String firstName,String lastName);
       
       //defined custom query using JPQL with named parameter
       @Query("select e from EmployeeEntity e where e.firstName=:firstName and lastName=:lastName")
       EmployeeEntity  findByNamedParams(@Param("firstName") String firstName,@Param("lastName") String lastName);
       
       //defined custom query using JPQL with index parameter
       @Query(value="select * from employee e where e.firstName=?1 and lastName=?2", nativeQuery=true)
       EmployeeEntity  findByNativeSQL(String firstName,String lastName);
       
       //defined custom query using Native with named parameter
       @Query(value="select * from employee e where e.FIRST_NAME=:firstName and e.LAST_NAME=:lastName",nativeQuery = true)
       EmployeeEntity  findByNativeNamedParams(@Param("firstName") String firstName,@Param("lastName") String lastName);

}
