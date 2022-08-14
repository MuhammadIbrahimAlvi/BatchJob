package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    @Id
    private String id;
    private String name;
    private String email;
    private String phone;
    private String designation;
    private String accountNumber;
    private String accountHash;
}
