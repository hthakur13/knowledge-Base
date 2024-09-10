package com.example.demo.knowledgeBaseAI.repository;

import com.example.demo.knowledgeBaseAI.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmpIdAndPassword(String empId, String password);

    Optional<Employee> findByEmpId(String empId);
}
