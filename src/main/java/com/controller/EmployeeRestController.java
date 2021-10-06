package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dto.EmployeeDTO;
import com.dto.EmployeeHeartDTO;
import com.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeRestController {
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/paging")
	public ResponseEntity<List<EmployeeDTO>> pagingEmployees(
			@RequestParam(name = "search", defaultValue = "") String search,
			@RequestParam(name = "sortBy", defaultValue = "employee_id") String sortBy,
			@RequestParam(name = "sortDirection", defaultValue = "DESC") String sortDirection,
			@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "size", defaultValue = "10") Integer size) {
		
		List<EmployeeDTO> employeeDTOs = employeeService.pagingBySearch(search, page, size, sortBy, sortDirection);
		
		if(employeeDTOs.isEmpty()) {
			return ResponseEntity.ok(new ArrayList<EmployeeDTO>());
		}else {
			return ResponseEntity.ok(employeeDTOs);
		}
	}
	
	@GetMapping("")
	public ResponseEntity<List<EmployeeDTO>> getEmployees() {
		
		List<EmployeeDTO> employeeDTOs = employeeService.findAll();
		
		if(employeeDTOs.isEmpty()) {
			return ResponseEntity.ok(new ArrayList<EmployeeDTO>());
		}else {
			return ResponseEntity.ok(employeeDTOs);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable Integer id) {
		
		EmployeeDTO employeeDTO = employeeService.findById(id);
		
		if(employeeDTO != null) {
			return ResponseEntity.ok(employeeDTO);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	@GetMapping("/count")
	public ResponseEntity<Long> numberOfEmployeeBySearch(@RequestParam(name = "search", required = false) String search) {
		if (search == null) {
			search = "";
		}
		Long number = employeeService.numberTotalBySearch(search);
		return ResponseEntity.ok(number);
	}
	
	
	@PostMapping(value = "")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {
		employeeDTO.setStatus((byte) 0);
		employeeDTO.setDeletedFlag(false);
		employeeService.save(employeeDTO);
		
		if (employeeDTO.getEmployee_id() != null) {
			return new ResponseEntity<EmployeeDTO>(employeeDTO, HttpStatus.CREATED);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody @Valid EmployeeDTO employeeDTO, @PathVariable Integer id) {
		
		EmployeeDTO _employeeDTO = employeeService.findById(id);
		
		if (_employeeDTO != null) {
			employeeDTO.setDeletedFlag(_employeeDTO.getDeletedFlag());
			employeeDTO.setStatus(_employeeDTO.getStatus());
			employeeDTO.setEmployee_id(_employeeDTO.getEmployee_id());
			employeeService.save(employeeDTO);
			return ResponseEntity.ok(employeeDTO);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Boolean> deleteEmployee(@PathVariable Integer id) {
		int result = employeeService.delete(id);
		
		if (result > 0) {
			return ResponseEntity.ok(true);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Boolean> deleteEmployees(@RequestParam Integer[] ids) {
		
		int result = employeeService.delete(ids);
		System.out.println(result);
		if (result > 0) {
			return ResponseEntity.ok(true);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/top")
	public ResponseEntity<List<EmployeeHeartDTO>> findEmployeeForTopHeart() {
		
		List<EmployeeHeartDTO> list = employeeService.findEmployeeForTopHeart();
		
		if(list.isEmpty()) {
			return ResponseEntity.ok(new ArrayList<EmployeeHeartDTO>());
		}else {
			return ResponseEntity.ok(list);
		}
	}
}
