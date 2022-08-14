package com.example.demo.util;

import com.example.demo.client.HashClient;
import com.example.demo.model.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ObjectMapperUtil {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private HashClient hashClient;
    public <T,D> D mapItem(T item,Class<D> cl){
        return modelMapper.map(item,cl);
    }
    public <T,D> List<D> map(List<T> list, Class<D> cl){
        return list.stream()
                .map(item -> modelMapper.map(item, cl))
                .collect(Collectors.toList());
    }

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
