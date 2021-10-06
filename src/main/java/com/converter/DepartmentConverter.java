package com.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dto.DepartmentDTO;
import com.entities.Department;

@Component
public class DepartmentConverter {
	@Autowired
	ModelMapper mapper;
	
	public Department toEntity(DepartmentDTO dto) {
		Department department = mapper.map(dto, Department.class);
		return department;
	}
	
	public DepartmentDTO toDTO(Department department) {
		department.setEmployees(null);
		DepartmentDTO dto = mapper.map(department, DepartmentDTO.class);
		return dto;
	}
}
