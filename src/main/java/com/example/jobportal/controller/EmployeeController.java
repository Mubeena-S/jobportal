package com.example.jobportal.controller;

import com.example.jobportal.dto.EmployeeResponseDTO;
import com.example.jobportal.model.Employee;
import com.example.jobportal.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

	private final EmployeeService employeeService;

	// Create Employee
	@PostMapping
	public ResponseEntity<EmployeeResponseDTO> createEmployee(@RequestBody Employee employee) {
		Employee saved = employeeService.saveEmployee(employee);
		return ResponseEntity.ok(mapToDTO(saved));
	}

	// Get All Employees
	@GetMapping
	public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {
		List<EmployeeResponseDTO> dtos = employeeService.getAllEmployees().stream().map(this::mapToDTO)
				.collect(Collectors.toList());
		return ResponseEntity.ok(dtos);
	}

	// Get Employee by ID
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable Long id) {
		return employeeService.getEmployeeById(id).map(emp -> ResponseEntity.ok(mapToDTO(emp)))
				.orElse(ResponseEntity.notFound().build());
	}

	// Get Employees by Recruiter
	@GetMapping("/recruiter/{recruiterId}")
	public ResponseEntity<List<EmployeeResponseDTO>> getEmployeesByRecruiter(@PathVariable Long recruiterId) {
		List<EmployeeResponseDTO> dtos = employeeService.getEmployeesByRecruiter(recruiterId).stream()
				.map(this::mapToDTO).collect(Collectors.toList());
		return ResponseEntity.ok(dtos);
	}

	// Update Employee
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeResponseDTO> updateEmployee(@PathVariable Long id,
			@RequestBody Employee updatedEmployee) {
		Employee updated = employeeService.updateEmployee(id, updatedEmployee);
		return ResponseEntity.ok(mapToDTO(updated));
	}

	// Delete Employee
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployee(id);
		return ResponseEntity.ok("Employee deleted successfully!");
	}

	// Mapper: Employee -> EmployeeResponseDTO
	private EmployeeResponseDTO mapToDTO(Employee emp) {
		return EmployeeResponseDTO.builder().id(emp.getId()).firstName(emp.getFirstName()).lastName(emp.getLastName())
				.email(emp.getEmail()).department(emp.getDepartment()).salary(emp.getSalary())
				.phoneNumber(emp.getPhoneNumber()).address(emp.getAddress())
				.recruiterId(emp.getRecruiter() != null ? emp.getRecruiter().getId() : null).build();
	}
}
