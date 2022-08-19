package com.test.springboottest.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {

	public final String UPLOAD_DIR="D:\\Demo Projects\\springbootproject\\springbootproject\\src\\main\\resources\\static\\Excel";

public boolean uploadFile(MultipartFile multipartFile) {
	
	boolean flag = false;
	
	try {
		
		Files.copy(multipartFile.getInputStream(),Paths.get(UPLOAD_DIR+File.separator+multipartFile.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
		flag = true;
		
	}catch(Exception ex) {
		
		ex.printStackTrace();
	}
	return flag;
}
}
