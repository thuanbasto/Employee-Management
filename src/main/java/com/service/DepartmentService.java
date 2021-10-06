package com.service;

import java.util.List;

import com.dto.DepartmentDTO;

public interface DepartmentService {
	List<DepartmentDTO> findAll();
	DepartmentDTO save(DepartmentDTO departmentDTO);
	int delete(Integer id);
	int delete(Integer[] ids);
	List<DepartmentDTO> pagingBySearch(String search, Integer page, Integer size, String sortBy, String sortDirection);
	Long numberTotalBySearch(String search);
	DepartmentDTO findById(Integer id);
	DepartmentDTO findByDepartmentName(String departmentName);
}
