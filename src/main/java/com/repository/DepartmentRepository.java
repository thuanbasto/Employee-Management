package com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.entities.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>{
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query("UPDATE Department d SET d.deletedFlag = 1 WHERE d.department_id = ?1 AND d.deletedFlag = 0")
	int deleteByIdWithCount(Integer department_id);
	
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query("UPDATE Department d SET d.deletedFlag = 1 WHERE d.department_id IN ?1 AND d.deletedFlag = 0")
	int deleteByIdsWithCount(List<Integer> ids);
	
	@Query(value = "FROM Department d "
			+ "WHERE lower(d.departmentName) LIKE ?1 AND d.deletedFlag = 0")
	List<Department> pagingBySearch(String str, Pageable pageable);
	
	@Query(value = "SELECT COUNT(1) FROM Department d "
			+ "WHERE lower(d.departmentName) LIKE ?1 AND d.deletedFlag = 0")
	Long numberTotalBySearch(String str);
	
	@Query(value = "FROM Department d WHERE d.department_id = ?1 AND d.deletedFlag = 0")
	Optional<Department> findByDepartmentIdAndDeletedFlagFalse(Integer id);
	
	Department findByDepartmentNameIgnoreCaseAndDeletedFlagFalse(String departmentName);
	
	List<Department> findByDeletedFlagFalse();

}
