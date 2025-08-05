package com.deutsche.demo.repository;


import com.deutsche.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//reference -
// https://docs.spring.io/spring-data/jpa/docs/current-SNAPSHOT/reference/html/#jpa.query-methods.query-creation

//	For basic CRUD Operations - no need to write any methods
//	SELECT - findAll();
//	SELECT - findById();
//	INSERT - save();
//	UPDATE - save();
//	DELETE - deleteById();

//	Only for business specific queries, abstract methods need to be declared here.
//	syntax
//	public abstract List<Employee> findByFieldName(String fieldName);
//	example
//	public abstract List<Employee> findByName(String name);
//	public abstract List<Employee> findBySalaryGreaterThan(Double salary);
//    @Query("select u from User u where u.emailAddress = ?1")
//    User findByEmailAddress(String emailAddress);

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    public abstract List<Employee> findByNameIgnoreCase(String name);

}