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

import com.dto.DepartmentDTO;
import com.service.DepartmentService;

@RestController
@RequestMapping("/api/departments")
@CrossOrigin(origins = "http://localhost:4200")
public class DepartmentRestController {
	
	@Autowired
	DepartmentService departmentService;
	
	@GetMapping("/paging")
	public ResponseEntity<List<DepartmentDTO>> pagingDepartments(
			@RequestParam(name = "search", defaultValue = "") String search,
			@RequestParam(name = "sortBy", defaultValue = "department_id") String sortBy,
			@RequestParam(name = "sortDirection", defaultValue = "DESC") String sortDirection,
			@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "size", defaultValue = "10") Integer size) {
		
		List<DepartmentDTO> departmentDTOs = departmentService.pagingBySearch(search, page, size, sortBy, sortDirection);
		
		if(departmentDTOs.isEmpty()) {
			return ResponseEntity.ok(new ArrayList<DepartmentDTO>());
		}else {
			return ResponseEntity.ok(departmentDTOs);
		}
	}
	
	@GetMapping("/count")
	public ResponseEntity<Long> numberTotalBySearch(@RequestParam(name = "search", required = false) String search) {
		if (search == null) {
			search = "";
		}
		Long number = departmentService.numberTotalBySearch(search);
		return ResponseEntity.ok(number);
	}
	
	@GetMapping("")
	public ResponseEntity<List<DepartmentDTO>> getDepartments() {
		
		List<DepartmentDTO> departmentDTOs = departmentService.findAll();
		
		if(departmentDTOs.isEmpty()) {
			return ResponseEntity.ok(new ArrayList<DepartmentDTO>());
		}else {
			return ResponseEntity.ok(departmentDTOs);
		}
	}
	
	@GetMapping("/{departmentName}")
	public ResponseEntity<DepartmentDTO> getDepartmentByName(@PathVariable("departmentName") String departmentName) {
		
		DepartmentDTO departmentDTO = departmentService.findByDepartmentName(departmentName);
		
		if(departmentDTO == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok(departmentDTO);
	}
	
	
	@PostMapping("")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<DepartmentDTO> addDepartment(@RequestBody @Valid DepartmentDTO departmentDTO) {
		departmentService.save(departmentDTO);
		
		if (departmentDTO.getDepartment_id() != null) {
			return new ResponseEntity<DepartmentDTO>(departmentDTO, HttpStatus.CREATED);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<DepartmentDTO> updateDepartment(@RequestBody @Valid DepartmentDTO departmentDTO, @PathVariable Integer id) {
		departmentDTO.setDepartment_id(id);
		departmentDTO = departmentService.save(departmentDTO);

		if (departmentDTO != null) {
			return ResponseEntity.ok(departmentDTO);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Boolean> deleteDepartment(@PathVariable Integer id) {
		int result = departmentService.delete(id);
		
		if (result > 0) {
			return ResponseEntity.ok(true);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Boolean> deleteDepartments(@RequestParam Integer[] ids) {
		
		int result = departmentService.delete(ids);
		System.out.println(result);
		if (result > 0) {
			return ResponseEntity.ok(true);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
