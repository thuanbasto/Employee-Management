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

import com.converter.DepartmentConverter;
import com.dto.DepartmentDTO;
import com.entities.Department;
import com.exception.exceptionclass.DuplicateDepartmentName;
import com.exception.exceptionclass.NoExistDepartment;
import com.repository.DepartmentRepository;
import com.service.DepartmentService;
import com.service.EmployeeService;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService{
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Autowired
	DepartmentConverter departmentConverter;
	
	@Autowired
	EmployeeService employeeService;

	@Override
	public List<DepartmentDTO> findAll() {
		List<DepartmentDTO> departmentDTOs = new ArrayList<DepartmentDTO>();
		List<Department> departments = departmentRepository.findByDeletedFlagFalse();
		departments.forEach(department -> departmentDTOs.add(departmentConverter.toDTO(department)));
		return departmentDTOs;
	}
	
	@Override
	public DepartmentDTO save(DepartmentDTO departmentDTO) {
		
		if (departmentDTO.getDepartment_id() != null) { // update
			// check exist id
			Optional<Department> department = departmentRepository.findByDepartmentIdAndDeletedFlagFalse(departmentDTO.getDepartment_id());
			if (department.isPresent()) {
				// check exist name
				Department departmentDuplicate = 
						departmentRepository.findByDepartmentNameIgnoreCaseAndDeletedFlagFalse(departmentDTO.getDepartmentName());
				boolean check = false;
				// duplicate name but equal id
				if (departmentDuplicate != null && departmentDuplicate.getDepartment_id() == departmentDTO.getDepartment_id()) {
					check = true;
				// dont duplicate name
				} else if (departmentDuplicate == null) {
					check = true;
				} else {
					throw new DuplicateDepartmentName();
				}
				if (check) {
					departmentDTO.setDeletedFlag(department.get().getDeletedFlag());
					departmentRepository.save(departmentConverter.toEntity(departmentDTO));
					return departmentDTO;
				}
			}
		} else { // add
			if (departmentRepository.findByDepartmentNameIgnoreCaseAndDeletedFlagFalse(departmentDTO.getDepartmentName()) == null) {
				departmentDTO.setDeletedFlag(false);
				Department department = departmentConverter.toEntity(departmentDTO);
				departmentRepository.save(department);
				if (department.getDepartment_id() != null) {
					departmentDTO.setDepartment_id(department.getDepartment_id());
					return departmentDTO;
				}
			} else {
				throw new DuplicateDepartmentName();
			}
		}
		
		return null;
	}

	@Override
	public int delete(Integer id) {
		employeeService.existEmployeeInDepartment(Arrays.asList(id));
		return departmentRepository.deleteByIdWithCount(id);
	}

	@Override
	public List<DepartmentDTO> pagingBySearch(String search, Integer page, Integer size, String sortBy, String sortDirection) {
		if (page == null || page < 0) {
			page = 0;
		} else {
			page -= 1;
		}
		
		Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(sortDirection), sortBy);
		List<DepartmentDTO> departmentDTOs = new ArrayList<DepartmentDTO>();
		List<Department> departments = departmentRepository.pagingBySearch("%"+search.toLowerCase()+"%", pageable);
		departments.forEach(department -> departmentDTOs.add(departmentConverter.toDTO(department)));
		return departmentDTOs;
	}

	@Override
	public Long numberTotalBySearch(String search) {
		return departmentRepository.numberTotalBySearch("%"+search.toLowerCase()+"%");
	}

	@Override
	public DepartmentDTO findById(Integer id) {
		Optional<Department> optional = departmentRepository.findById(id);
		if (optional.isPresent()) {
			return departmentConverter.toDTO(optional.get());
		}
		return null;
	}

	@Override
	public DepartmentDTO findByDepartmentName(String departmentName) {
		Department department = departmentRepository.findByDepartmentNameIgnoreCaseAndDeletedFlagFalse(departmentName);
		if (department == null) {
			return null;
		} 
		return departmentConverter.toDTO(department);
	}

	@Override
	public int delete(Integer[] ids) {
		employeeService.existEmployeeInDepartment(Arrays.asList(ids));
		int result = departmentRepository.deleteByIdsWithCount(Arrays.asList(ids));
		if (ids.length > result) {
			throw new NoExistDepartment();
		}
		return result;
	}
	
}
