package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.entities.Heart;
import com.entities.HeartId;

@Repository
public interface HeartRepository extends JpaRepository<Heart, HeartId>{
	@Query(value = "SELECT COUNT(1) FROM Heart h WHERE h.employee.employee_id = ?1 AND h.status = 1")
	Long countByEmployeeId(Integer employee_id);
	
}
