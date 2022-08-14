package com.example.demo.util;

import com.example.demo.client.HashClient;
import com.example.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class EmployeeMapper {
    @Autowired
    private HashClient hashClient;

    @Bean
    public Employee toEmployee(String[] data){
        Employee employee = Employee.builder()
                .id(data[0])
                .name(data[1])
                .email(data[2])
                .phone(data[3])
                .designation(data[4])
                .accountNumber(data[5])
                .build();
        String hashAccountNumber = hashClient.generateHash(data[5]);
        employee.setAccountHash(hashAccountNumber);
        return employee;
    }

}
