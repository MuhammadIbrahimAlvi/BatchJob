package com.example.demo.util;

import com.example.demo.exception.WrongDataException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class ParseFileUtil {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ObjectMapperUtil objectMapperUtil;

    public List<Employee> parseCSVFile(final MultipartFile file) throws Exception {
        final List<Employee> employees = new ArrayList<>();
        Employee employee;
        try {
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                String line;
                br.readLine();
                while ((line = br.readLine()) != null) {
                    final String[] data = line.split(",");
                    if (!employeeRepository.findById(data[0]).isPresent())
                        if (data[0] != null && !data[0].isEmpty() &&
                                data[1] != null && !data[1].isEmpty() &&
                                data[2] != null && !data[2].isEmpty() &&
                                data[3] != null && !data[3].isEmpty() &&
                                data[4] != null && !data[4].isEmpty() &&
                                data[5] != null && !data[5].isEmpty()
                        ) {
                           employee = objectMapperUtil.toEmployee(data);
                           employees.add(employee);
                        } else {
                            throw new WrongDataException();
                        }
                    return employees;
                }
            }
        } catch (final IOException e) {
            throw new Exception("Failed to parse CSV file {}", e);
        }
        return employees;
    }
}
