package com.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.service.FileService;

@Service
public class FileServiceImpl implements FileService{

	private final Path root = Paths.get("src/main/resources/static/image");
	
	@Override
	public String saveFile(MultipartFile file) {
		String fileName = null;
		if (file != null) {
			try {
				fileName = new Date().getTime() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
				
				File newFile = new File(root.toString() + "/" + fileName);
				FileOutputStream fileOutputStream = new FileOutputStream(newFile);
				fileOutputStream.write(file.getBytes());
				fileOutputStream.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return fileName;
	}

}
