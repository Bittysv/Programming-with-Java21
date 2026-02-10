package example.coding.practice.employeemgntapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import example.coding.practice.employeemgntapi.exception.ResourceNotFoundException;
import example.coding.practice.employeemgntapi.model.Employee;
import example.coding.practice.employeemgntapi.repository.EmployeeRepository;

//Business Logic

@Service
public class EmployeeService {

	private final EmployeeRepository repository;

	public EmployeeService(EmployeeRepository repository) {
		this.repository = repository;
	}

	public Employee save(Employee employee) {
		return repository.save(employee);
	}

	public List<Employee> findAll() {
		return repository.findAll();
	}

	public Employee findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not find"));
	}
}
