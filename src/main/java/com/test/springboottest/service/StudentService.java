package com.test.springboottest.service;

import org.springframework.web.multipart.MultipartFile;

import com.test.springboottest.Student;

public interface StudentService {

	public Student updateStudent(Student student);
	
	public boolean insertUploadFileData(MultipartFile multipartFile);
}
