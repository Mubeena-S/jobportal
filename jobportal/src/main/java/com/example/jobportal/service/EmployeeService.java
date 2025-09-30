package com.example.jobportal.service;

import com.example.jobportal.model.Employee;
import com.example.jobportal.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    // Create / Save
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Get all
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    
    // Get employees by recruiter
    public List<Employee> getEmployeesByRecruiter(Long recruiterId) {
        return employeeRepository.findAll()
                .stream()
                .filter(emp -> emp.getRecruiter() != null && emp.getRecruiter().getId().equals(recruiterId))
                .collect(Collectors.toList());
    }

    // Get by id
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    // Update
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setFirstName(updatedEmployee.getFirstName());
                    employee.setLastName(updatedEmployee.getLastName());
                    employee.setEmail(updatedEmployee.getEmail());
                    employee.setDepartment(updatedEmployee.getDepartment());
                    employee.setSalary(updatedEmployee.getSalary());
                    employee.setPhoneNumber(updatedEmployee.getPhoneNumber());
                    employee.setAddress(updatedEmployee.getAddress());
                    return employeeRepository.save(employee);
                })
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }

    // Delete
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
