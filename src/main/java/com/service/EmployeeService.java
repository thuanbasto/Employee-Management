package com.service;

import java.util.List;

import com.dto.EmployeeDTO;
import com.dto.EmployeeHeartDTO;

public interface EmployeeService {
	List<EmployeeDTO> findAll();
	EmployeeDTO findById(Integer id);
	EmployeeDTO save(EmployeeDTO employeeDTO);
	int delete(Integer id);
	int delete(Integer[] ids);
	List<EmployeeDTO> pagingBySearch(String search, Integer page, Integer size, String sortBy, String sortDirection);
	Long numberTotalBySearch(String search);
	boolean existEmployeeInDepartment(List<Integer> ids);
	List<EmployeeHeartDTO> findEmployeeForTopHeart();
}
