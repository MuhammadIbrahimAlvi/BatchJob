package com.example.demo.service.Impl;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.exception.FieldNotFound;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import com.example.demo.util.ObjectMapperUtil;
import com.example.demo.util.ParseFileUtil;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;


@Service
@AllArgsConstructor
class EmployeeServiceImpl implements EmployeeService {

    Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ObjectMapperUtil objectMapperUtil;

    private final ParseFileUtil parseFile;



    @Override
    public Employee findEmployee(String id) {
        return employeeRepository.findById(id).orElseThrow(()-> new FieldNotFound(404, "Employee not Found","The id id doesn't exist in db"));
    }
    @Override
    public List<EmployeeDTO> saveAllEmployees(List<EmployeeDTO> employees) {
        List<Employee> newList = objectMapperUtil.map(employees, Employee.class);
        List<Employee> savedEmployeeList = employeeRepository.saveAll(newList);
        return objectMapperUtil.map(savedEmployeeList, EmployeeDTO.class);
    }

    @Async
    public CompletableFuture<List<Employee>> saveEmployees(MultipartFile file) throws Exception {
        long start = System.currentTimeMillis();
        List<Employee> users = parseFile.parseCSVFile(file);
        logger.info("saving list of users of size {}", users.size(), "" + Thread.currentThread().getName());
        users = employeeRepository.saveAll(users);
        long end = System.currentTimeMillis();
        logger.info("Total time {}", (end - start));
        return CompletableFuture.completedFuture(users);
    }

    @Autowired
    public EmployeeServiceImpl(ParseFileUtil parseFile) {
        this.parseFile = parseFile;
    }
}
