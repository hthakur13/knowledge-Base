package com.example.demo.knowledgeBaseAI.service;

import com.example.demo.knowledgeBaseAI.entity.Employee;
import com.example.demo.knowledgeBaseAI.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public boolean authenticate(String empId, String password) {
        return employeeRepository.findByEmpIdAndPassword(empId, password).isPresent();
    }

    public Optional<Employee> getEmployeeById(String empId) {
        return employeeRepository.findByEmpId(empId);
    }

    //to save the new user login details
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }
}
