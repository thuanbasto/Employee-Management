package com.converter;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dto.HeartDTO;
import com.entities.Employee;
import com.entities.Heart;
import com.entities.User;

@Component
public class HeartConverter {
	@Autowired
	ModelMapper mapper;
	
	public HeartDTO toDTO(Heart heart) {
		HeartDTO heartDTO = mapper.map(heart, HeartDTO.class);
		return heartDTO;
	}
	
	public Heart toEntity(HeartDTO heartDTO) {
		Heart heart = new Heart();
		
		heart.setHeartId(heartDTO.getHeartId());
		heart.setStatus(heartDTO.getStatus());
		
		heart.setCreatedDate(new Date());
		
		heart.setUser(new User(heartDTO.getHeartId().getUser_id()));
		heart.setEmployee(new Employee(heartDTO.getHeartId().getEmployee_id()));
		
		return heart;
	}
}
