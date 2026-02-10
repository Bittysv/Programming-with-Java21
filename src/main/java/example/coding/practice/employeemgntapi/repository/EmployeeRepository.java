package example.coding.practice.employeemgntapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import example.coding.practice.employeemgntapi.model.Employee;

//Repository Spring Data JPA

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
