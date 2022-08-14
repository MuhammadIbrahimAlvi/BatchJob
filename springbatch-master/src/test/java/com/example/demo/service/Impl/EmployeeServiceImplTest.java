package com.example.demo.service.Impl;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.util.ObjectMapperUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private ObjectMapperUtil objectMapperUtil;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    List<Employee> employeeList = new ArrayList<>();

    List<EmployeeDTO> employeeDTOSList = new ArrayList<>();

    Employee employee0 = new Employee();

    EmployeeDTO employeeDTO = new EmployeeDTO();


    List<EmployeeDTO> employeeDTOList = new ArrayList<>();
// The method run before every test
    @BeforeEach
    public void initializeList(){
        // set first employee data
        employee0.setId("1");
        employee0.setName("Ibrahim");
        employee0.setEmail("ebrahimalvi2@gmail.com");
        employee0.setPhone("033333333333");
        employee0.setAccountNumber("A0111B678");
        employee0.setDesignation("Engineer");

        // set first employee data
        employeeDTO.setId("1");
        employeeDTO.setName("Ibrahim");
        employeeDTO.setEmail("ebrahimalvi2@gmail.com");
        employeeDTO.setPhone("033333333333");
        employeeDTO.setAccountNumber("A0111B678");
        employeeDTO.setDesignation("Engineer");
        employeeList = Collections.singletonList(employee0);
        employeeDTOSList = Collections.singletonList(employeeDTO);
    }

    @Test
    void saveEmployeesFailed() {
        Mockito.when(objectMapperUtil.map(anyList(), any(Class.class))).thenReturn(employeeList);
        Mockito.when(employeeRepository.saveAll(employeeList)).thenThrow(new NullPointerException());
        assertThrows(NullPointerException.class, () -> employeeService.saveAllEmployees(employeeDTOSList));
    }

    @Test
    void saveEmployees() {
        Mockito.when(objectMapperUtil.map(anyList(), any(Class.class))).thenReturn(employeeList);
        Mockito.when(employeeRepository.saveAll(employeeList)).thenReturn(employeeList);
        assertEquals(employeeDTOList, employeeService.saveAllEmployees(employeeDTOSList));
    }

    @Test
    void checkIfEmployeeExist(){
        Mockito.when(employeeRepository.findById(anyString())).thenReturn(Optional.of(employee0));
        Employee employee = employeeService.findEmployee(employee0.getId());
        assertNotNull(employee);
        assertEquals("1", employee.getId());
    }

}