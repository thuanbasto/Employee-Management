package com.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.converter.EmployeeConverter;
import com.dto.EmployeeDTO;
import com.dto.EmployeeHeartDTO;
import com.entities.Employee;
import com.exception.exceptionclass.ExistDepartmentChirldren;
import com.exception.exceptionclass.NoExistEmployee;
import com.repository.EmployeeRepository;
import com.service.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	EmployeeConverter employeeConverter;

	@Override
	public List<EmployeeDTO> findAll() {
		List<EmployeeDTO> employeeDTOs = new ArrayList<EmployeeDTO>();
		List<Employee> employees = employeeRepository.findAll();
		employees.forEach(employee -> employeeDTOs.add(employeeConverter.toDTO(employee)));
		return employeeDTOs;
	}

	@Override
	public EmployeeDTO save(EmployeeDTO employeeDTO) {
//		employeeDTO.setImageUrl(fileService.saveFile(employeeDTO.getFile()));
		
		Employee employee = employeeConverter.toEntity(employeeDTO);
		employeeRepository.save(employee);
		if (employee.getEmployee_id() != null) {
			employeeDTO.setEmployee_id(employee.getEmployee_id());
			return employeeDTO;
		}
		return null;
	}

	@Override
	public int delete(Integer id) {
		return employeeRepository.deleteByIdWithCount(id);
	}

	@Override
	public List<EmployeeDTO> pagingBySearch(String search, Integer page, Integer size, String sortBy, String sortDirection) {
		if (page == null || page < 0) {
			page = 0;
		} else {
			page -= 1;
		}
		
		Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(sortDirection), sortBy);
		List<EmployeeDTO> employeeDTOs = new ArrayList<EmployeeDTO>();
		List<Employee> employees = employeeRepository.pagingBySearch("%"+search.toLowerCase()+"%", pageable);
		employees.forEach(employee -> employeeDTOs.add(employeeConverter.toDTO(employee)));
		return employeeDTOs;
	}

	@Override
	public Long numberTotalBySearch(String search) {
		return employeeRepository.numberTotalBySearch("%"+search.toLowerCase()+"%");
	}

	@Override
	public EmployeeDTO findById(Integer id) {
		Optional<Employee> optional = employeeRepository.findByEmployeeIdAndDeletedFlagFalse(id);
		if (optional.isPresent()) {
			return employeeConverter.toDTO(optional.get());
		}
		return null;
	}

	@Override
	public int delete(Integer[] ids) {
		int result = employeeRepository.deleteByIdsWithCount(Arrays.asList(ids));
		if (ids.length > result) {
			throw new NoExistEmployee();
		}
		return result;
	}

	@Override
	public boolean existEmployeeInDepartment(List<Integer> ids) {
		List<Employee> employees = employeeRepository.existsByDepartmentId(ids);
		if (!employees.isEmpty()) { // true = has employee
			String departmentName = employees.get(0).getDepartment().getDepartmentName();
			throw new ExistDepartmentChirldren(departmentName);
		}
		return false;
	}

	@Override
	public List<EmployeeHeartDTO> findEmployeeForTopHeart() {
		List<Object[]> listObj = employeeRepository.findEmployeeForTopHeart();
		List<EmployeeHeartDTO> list = new ArrayList<>();
		listObj.forEach(obj -> list.add(employeeConverter.toDTO(obj)));
		return list;
	}
	
}
