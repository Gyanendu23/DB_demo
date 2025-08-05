package com.deutsche.demo.controller;

import com.deutsche.demo.model.Employee;
import com.deutsche.demo.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//@CrossOrigin(origins=["http://localhost:5173/"])
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService empService;

    @Test
    void testGetAllEmployees() throws Exception {
        List<Employee> dummyList = List.of(
                new Employee(101, "Gyan", 50000.0),
                new Employee(102, "John", 60000.0)
        );

        when(empService.getAllEmployees()).thenReturn(dummyList);

        mockMvc.perform(get("/api/v1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(101))
                .andExpect(jsonPath("$[0].name").value("Gyan"))
                .andExpect(jsonPath("$[0].salary").value(50000.0))
                .andExpect(jsonPath("$[1].id").value(102))
                .andExpect(jsonPath("$[1].name").value("John"))
                .andExpect(jsonPath("$[1].salary").value(60000.0));

        verify(empService, times(1)).getAllEmployees();
    }
}
