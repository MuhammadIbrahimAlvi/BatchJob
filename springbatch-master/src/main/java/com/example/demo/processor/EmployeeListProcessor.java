package com.example.demo.processor;

import com.example.demo.client.HashClient;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class EmployeeListProcessor implements ItemProcessor<Employee, Employee> {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HashClient hashClient;

    @Autowired
    private EmployeeService employeeService;

    @Override
    public Employee process(Employee employee) {
        Employee response = employeeService.findEmployee(employee.getId());
        if (response.getAccountHash() == null) {
            String hash = hashClient.generateHash(employee.getAccountNumber());
            employee.setAccountHash(hash);
        }
        return employee;
    }

}