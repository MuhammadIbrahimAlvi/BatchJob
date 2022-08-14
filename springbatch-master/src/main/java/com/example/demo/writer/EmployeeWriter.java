package com.example.demo.writer;

import com.example.demo.exception.FieldNotFound;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.PrintWriter;
import java.util.List;

public class EmployeeWriter implements ItemWriter<Employee> {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @Override
    public void write(List<? extends Employee> list) throws Exception {
        PrintWriter writer = new PrintWriter("/Users/mialvi/Documents/Gap/batchapplication/src/main/resources/Employee.csv");
        writer.print("");
        writer.close();
        if (list.isEmpty()) {
            throw new FieldNotFound(404, "Employees are empty", "Empty List cann't be persisted.");
        }
        employeeRepository.saveAll(list);
    }
}
