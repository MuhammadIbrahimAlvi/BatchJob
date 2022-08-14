package com.example.demo.reader;

import com.example.demo.exception.FieldNotFound;
import com.example.demo.model.Employee;
import com.example.demo.util.ObjectMapperUtil;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;

import java.io.FileWriter;
import java.io.IOException;

public class EmployeeReader implements FieldSetMapper<Employee> {
    @Autowired
    private ObjectMapperUtil objectMapperUtil;

    @Override
    public Employee mapFieldSet(FieldSet fieldSet) throws BindException {
        try {
            FileWriter writer = new FileWriter("/Users/mialvi/Documents/Gap/batchapplication/src/main/resources/EmployeeOutput.csv", true);

            Employee emp = objectMapperUtil.mapItem(fieldSet.getProperties(), Employee.class);

            if (emp.getAccountNumber().isEmpty() || emp.getName().isEmpty() || emp.getEmail().isEmpty() | emp.getDesignation().isEmpty() || emp.getId().isEmpty() || emp.getPhone().isEmpty()) {
                try {
                    FileWriter fw = new FileWriter("/Users/mialvi/Documents/Gap/batchapplication/src/main/resources/IncorrecrRecords.txt");
                    fw.write(String.valueOf(emp));
                    fw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                throw new FieldNotFound(404, "Field has incorrect value..!", "The one of the field value entered is corrupted or has incorrect type.");
            }
            writer.write(emp + "\n");
            writer.close();
            return emp;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
