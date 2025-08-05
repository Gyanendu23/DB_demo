package com.deutsche.demo.service;

import com.deutsche.demo.exception.EmployeeNotFoundException;
import com.deutsche.demo.model.Employee;
import com.deutsche.demo.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Get all employees from the database
     */
    public List<Employee> getAllEmployees() {
        LOG.info("Fetching all employees...");
        return employeeRepository.findAll();
    }

    /**
     * Get an employee by ID. Throws exception if not found.
     */
    public Employee getEmployeeById(Integer id) {
        LOG.info("Fetching employee with id: {}", id);
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    /**
     * Get employees by name (case insensitive)
     */
    public List<Employee> getEmployeesByName(String name) {
        LOG.info("Fetching employees with name: {}", name);
        return employeeRepository.findByNameIgnoreCase(name);
    }

    /**
     * Add new employee. You can add logic to prevent duplicates.
     */
    public Employee addEmployee(Employee employee) {
        LOG.info("Adding new employee: {}", employee);

        // Example logic to prevent duplicate ID (if needed)
        if (employee.getId() != null && employeeRepository.existsById(employee.getId())) {
            throw new IllegalArgumentException("Employee with ID " + employee.getId() + " already exists.");
        }

        return employeeRepository.save(employee);
    }

    /**
     * Update employee if exists
     */
    public Employee updateEmployee(Employee employee) {
        LOG.info("Updating employee with id: {}", employee.getId());

        if (!employeeRepository.existsById(employee.getId())) {
            throw new EmployeeNotFoundException( employee.getId());
        }

        return employeeRepository.save(employee);
    }

    /**
     * Delete employee by ID and return deleted object
     */
    public Employee deleteEmployee(Integer id) {
        LOG.info("Deleting employee with id: {}", id);

        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if (!employeeOptional.isPresent()) {
            throw new EmployeeNotFoundException(id);
        }

        employeeRepository.deleteById(id);
        return employeeOptional.get();
    }
}
