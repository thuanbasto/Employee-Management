package com.converter;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dto.EmployeeDTO;
import com.dto.EmployeeHeartDTO;
import com.entities.Employee;

@Component
public class EmployeeConverter {
	
	@Autowired
	ModelMapper mapper;
	
	public Employee toEntity(EmployeeDTO dto) {
		Employee employee = mapper.map(dto, Employee.class);
		return employee;
	}
	
	public EmployeeDTO toDTO(Employee employee) {
		EmployeeDTO dto = mapper.map(employee, EmployeeDTO.class);
		return dto;
	}
	
//	e.employee_id, e.address, e.birthday, e.description, e.image_url, 
//	"e.married, e.name, e.phone, e.place_of_birth, e.status, e.department_id, e.deleted_flag, 
//	"d.department_name, COUNT(h.employee_id) 'numberOfHearts'
	public EmployeeHeartDTO toDTO(Object[] obj) {
		EmployeeHeartDTO dto = new EmployeeHeartDTO();
		dto.setEmployee_id(Integer.valueOf(obj[0].toString()));
		dto.setAddress(obj[1].toString());
		dto.setBirthday((Date)obj[2]);
		dto.setDescription(obj[3].toString());
		dto.setImageUrl(obj[4].toString());
		dto.setMarried(Boolean.valueOf(obj[5].toString()));
		dto.setName(obj[6].toString());
		dto.setPhone(obj[7].toString());
		dto.setPlaceOfBirth(obj[8].toString());
		dto.setStatus((Byte) obj[9]);
//		dto.setDepartmentName(obj[10].toString());
		dto.setDeletedFlag(Boolean.valueOf(obj[11].toString()));
		dto.setDepartmentName(obj[12].toString());
		dto.setNumberOfHearts(Long.valueOf(obj[13].toString()));
		return dto;
	}
}
