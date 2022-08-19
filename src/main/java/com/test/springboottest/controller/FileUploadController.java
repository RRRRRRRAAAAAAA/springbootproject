package com.test.springboottest.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.test.springboottest.helper.FileUploadHelper;

@RestController
public class FileUploadController {

	@Autowired
	private FileUploadHelper fileUploadHelper;
	
	@PostMapping("/upload-file")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){
		
		try {
		//validation 1
		if(file.isEmpty()) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain file");
		}
		//validation 2
		if(!file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only excel file allowed");
		}
		
		// upload file code
		boolean f = fileUploadHelper.uploadFile(file);
		
		if(f) {
			return ResponseEntity.ok("File is succesfully uploaded");
		}
		}catch(Exception ex) {
			ex.printStackTrace();			
		}		
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong ! try again");
	}
}
