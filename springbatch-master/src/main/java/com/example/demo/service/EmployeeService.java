package com.example.demo.service;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.model.Employee;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface EmployeeService {
    Employee findEmployee(String id);

    List<EmployeeDTO> saveAllEmployees(List<EmployeeDTO> employees);

    CompletableFuture<List<Employee>> saveEmployees(MultipartFile files) throws Exception;


}
