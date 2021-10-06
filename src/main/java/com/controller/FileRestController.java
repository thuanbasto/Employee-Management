package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dto.MessageResponse;
import com.service.FileService;

@RestController
@RequestMapping("/api/files")
@CrossOrigin(origins = "http://localhost:4200")
public class FileRestController {
	
	@Autowired
	FileService fileService;
	
	@PostMapping(value = "")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> addFile(@RequestParam("file") MultipartFile file) {
		String result = fileService.saveFile(file);
		
		if (result != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(result));
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
//	@PostMapping("/upload")
//	  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
//	    String message = "";
//	    try {
//	      storageService.store(file);
//
//	      message = "Uploaded the file successfully: " + file.getOriginalFilename();
//	      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
//	    } catch (Exception e) {
//	      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
//	      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
//	    }
//	  }
}
