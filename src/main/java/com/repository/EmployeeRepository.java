package com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query("UPDATE Employee i SET i.deletedFlag = 1 WHERE i.employee_id = ?1 AND i.deletedFlag = 0")
	int deleteByIdWithCount(Integer employee_id);
	
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query("UPDATE Employee i SET i.deletedFlag = 1 WHERE i.employee_id IN ?1 AND i.deletedFlag = 0")
	int deleteByIdsWithCount(List<Integer> ids);
	
	@Query("FROM Employee i WHERE i.employee_id = ?1 AND i.deletedFlag = 0")
	Optional<Employee> findByEmployeeIdAndDeletedFlagFalse(Integer id);
	
	@Query(value = "FROM Employee i "
			+ "WHERE (i.name LIKE ?1 "
			+ "OR i.address LIKE ?1 "
			+ "OR i.description LIKE ?1 "
			+ "OR i.phone LIKE ?1 "
			+ "OR i.status LIKE ?1 "
			+ "OR lower(i.department.departmentName) LIKE ?1) "
			+ "AND i.deletedFlag = 0")
	List<Employee> pagingBySearch(String str, Pageable pageable);
	
	@Query(value = "SELECT COUNT(1) FROM Employee i "
			+ "WHERE (i.name LIKE ?1 "
			+ "OR i.address LIKE ?1 "
			+ "OR i.description LIKE ?1 "
			+ "OR i.phone LIKE ?1 "
			+ "OR i.status LIKE ?1 "
			+ "OR lower(i.department.departmentName) LIKE ?1) "
			+ "AND i.deletedFlag = 0")
	Long numberTotalBySearch(String str);
	
	@Query("FROM Employee i WHERE i.department.department_id IN ?1 AND i.department.deletedFlag = 0")
	List<Employee> existsByDepartmentId(List<Integer> ids);
	
	@Query(value="SELECT e.employee_id, e.address, e.birthday, e.description, e.image_url, "
			+ "e.married, e.name, e.phone, e.place_of_birth, e.status, e.department_id, e.deleted_flag, "
			+ "d.department_name, COUNT(h.employee_id) 'numberOfHearts' FROM Employee e "
			+ "JOIN Department d ON d.department_id = e.department_id "
			+ "JOIN Heart h ON h.employee_id = e.employee_id "
			+ "WHERE h.status = true "
			+ "GROUP BY e.employee_id, e.address, e.birthday, e.description, e.image_url, e.married, "
			+ "e.name, e.phone, e.place_of_birth, e.status, e.department_id, e.deleted_flag, d.department_name "
			+ "ORDER BY numberOfHearts DESC\r\n"
			+ "LIMIT 10"
			,nativeQuery=true)
	List<Object[]> findEmployeeForTopHeart();
}
