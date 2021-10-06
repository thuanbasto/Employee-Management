package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.converter.HeartConverter;
import com.dto.HeartDTO;
import com.entities.Heart;
import com.entities.HeartId;
import com.exception.exceptionclass.NoExistHeart;
import com.repository.HeartRepository;
import com.service.HeartService;

@Service
public class HeartServiceImpl implements HeartService {
	
	@Autowired
	HeartRepository heartRepository;
	
	@Autowired
	HeartConverter heartConverter;
	
	@Override
	public void save(HeartDTO heartDTO) {
		Heart heart = heartConverter.toEntity(heartDTO);
		heartRepository.save(heart);
		heartDTO.setHeartId(heart.getHeartId());
		heartDTO.setCreatedDate(heart.getCreatedDate());
	}

	@Override
	public HeartDTO findById(HeartId heartId) {
		Heart heart = heartRepository.findById(heartId)
				.orElseThrow(() -> new NoExistHeart());
		return heartConverter.toDTO(heart);
	}

	@Override
	public Long countByEmployeeId(Integer employee_id) {
		return heartRepository.countByEmployeeId(employee_id);
	}
}
