package com.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dto.HeartDTO;
import com.entities.HeartId;
import com.service.HeartService;

@RestController
@RequestMapping("/api/hearts")
@CrossOrigin(origins = "http://localhost:4200")
public class HeartRestController {
	
	@Autowired
	HeartService heartService;
	
	@GetMapping(value = "")
	public ResponseEntity<HeartDTO> getHeart(
			@RequestParam("employee_id") Integer employee_id,
			@RequestParam("user_id") Integer user_id
		) {
		HeartDTO heartDTO = heartService.findById(new HeartId(user_id, employee_id));
		return new ResponseEntity<HeartDTO>(heartDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/count/{id}")
	public ResponseEntity<Long> countHeart(@PathVariable("id") Integer employee_id) {
		Long count = heartService.countByEmployeeId(employee_id);
		return new ResponseEntity<Long>(count, HttpStatus.OK);
	}
	
	@PostMapping(value = "")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<HeartDTO> addHeart(@RequestBody @Valid HeartDTO heartDTO) {
		heartService.save(heartDTO);
		return new ResponseEntity<HeartDTO>(heartDTO, HttpStatus.OK);
	}
	
}
