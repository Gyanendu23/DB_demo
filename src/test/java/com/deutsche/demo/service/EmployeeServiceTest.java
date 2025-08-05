//package com.deutsche.demo.service;
//
//import com.deutsche.demo.exception.EmployeeNotFoundException;
//import com.deutsche.demo.model.Employee;
//import com.deutsche.demo.repository.EmployeeRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.*;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//
//class EmployeeServiceTest {
//
//    @InjectMocks
//    private EmployeeService employeeService;
//
//    @Mock
//    private EmployeeRepository employeeRepository;
//
//    @Test
//    void testGetAllEmployees() {
//        List<Employee> mockList = Arrays.asList(new Employee(1, "Gyanendu", 50000.0));
//        when(employeeRepository.findAll()).thenReturn(mockList);
//
//        List<Employee> result = employeeService.getAllEmployees();
//
//        assertEquals(1, result.size());
//        assertEquals("Gyanendu", result.get(0).getName());
//    }
//
//    @Test
//    void testGetEmployeeById_found() {
//        Employee emp = new Employee(1, "Chetan", 40000.0);
//        when(employeeRepository.findById(1)).thenReturn(Optional.of(emp));
//
//        Employee result = employeeService.getEmployeeById(1);
//
//        assertEquals("Chetan", result.getName());
//    }
//
//    @Test
//    void testGetEmployeeById_notFound() {
//        when(employeeRepository.findById(1)).thenReturn(Optional.empty());
//
//        assertThrows(EmployeeNotFoundException.class, () -> employeeService.getEmployeeById(1));
//    }
//
//    @Test
//    void testAddEmployee_success() {
//        Employee emp = new Employee(null, "Ram", 60000.0);
//        when(employeeRepository.save(emp)).thenReturn(new Employee(1, "Ram", 60000.0));
//
//        Employee saved = employeeService.addEmployee(emp);
//
//        assertEquals("Ram", saved.getName());
//    }
//
//    @Test
//    void testUpdateEmployee_nonExistent() {
//        Employee emp = new Employee(999, "Ishaan", 9000.0);
//        when(employeeRepository.existsById(emp.getId())).thenReturn(false);
//
//        assertThrows(EmployeeNotFoundException.class, () -> employeeService.updateEmployee(emp));
//    }
//
//    @Test
//    void testDeleteEmployee_notFound() {
//        when(employeeRepository.existsById(5)).thenReturn(false);
//
//        assertThrows(EmployeeNotFoundException.class, () -> employeeService.deleteEmployee(5));
//    }
//}
