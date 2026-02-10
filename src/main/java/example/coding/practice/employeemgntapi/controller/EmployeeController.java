package example.coding.practice.employeemgntapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import example.coding.practice.employeemgntapi.model.Employee;
import example.coding.practice.employeemgntapi.service.EmployeeService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	private final EmployeeService service;

	public EmployeeController(EmployeeService service) {
		this.service = service;
	}

	@PostMapping
	public Employee create(@Valid @RequestBody Employee employee) {

		return service.save(employee);
	}

	@GetMapping
	public List<Employee> getAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public Employee getById(@PathVariable Long id) {
		return service.findById(id);
	}
}
