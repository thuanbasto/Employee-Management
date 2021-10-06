package com.service;

import com.dto.HeartDTO;
import com.entities.HeartId;

public interface HeartService {
	HeartDTO findById(HeartId heartId);
	void save(HeartDTO heartDTO);
	Long countByEmployeeId(Integer employee_id);
}
