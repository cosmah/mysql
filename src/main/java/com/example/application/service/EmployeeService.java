package com.example.application.service;

import com.example.application.entity.Employee;
import com.example.application.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    @Autowired
    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> findAll() {
        return repository.findAll();
    }

    public Employee save(String name, String role) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setRole(role);
        return repository.save(employee);
    }

}
