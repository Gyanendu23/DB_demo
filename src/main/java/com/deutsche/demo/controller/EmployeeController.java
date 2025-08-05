package com.deutsche.demo.controller;

import com.deutsche.demo.model.Employee;
import com.deutsche.demo.service.EmployeeService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
@RestController
@RequestMapping("employees")
public class EmployeeController {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EmployeeService employeeService;

    // GET all employees
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        LOG.info("Fetching all employees");
        return ResponseEntity
                .ok()
                .header("message", "All employees fetched successfully.")
                .body(employeeService.getAllEmployees());
    }

    // GET employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
        LOG.info("Fetching employee with id {}", id);
        return ResponseEntity
                .ok()
                .header("message", "Employee with id " + id + " fetched successfully.")
                .body(employeeService.getEmployeeById(id));
    }

    // POST - Add employee
    @PostMapping
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee employee) {
        LOG.info("Adding employee: {}", employee);
        Employee created = employeeService.addEmployee(employee);
        return ResponseEntity
                .status(201)
                .header("message", "Employee added successfully.")
                .body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id, @Valid @RequestBody Employee employee) {
        LOG.info("Updating employee with id {}", id);
        employee.setId(id); // ensure correct ID is used
        Employee updated = employeeService.updateEmployee(employee);
        return ResponseEntity
                .ok()
                .header("message", "Employee updated successfully.")
                .body(updated);
    }


    // DELETE - Remove employee by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable Integer id) {
        LOG.info("Deleting employee with id {}", id);
        Employee deleted = employeeService.deleteEmployee(id);
        return ResponseEntity
                .ok()
                .header("message", "Employee with id " + id + " deleted successfully.")
                .body(deleted);
    }

    // Optionally: only return recently added employees
    // To support "Add Employee" page view only
    // You must implement a "filter" logic in service layer or frontend
    @GetMapping("/search")
    public ResponseEntity<List<Employee>> getEmployeesByName(@RequestParam String name) {
        LOG.info("Searching for employees by name: {}", name);
        List<Employee> employees = employeeService.getEmployeesByName(name);
        return ResponseEntity
                .ok()
                .header("message", "Employees with name '" + name + "' fetched successfully.")
                .body(employees);
    }
}
