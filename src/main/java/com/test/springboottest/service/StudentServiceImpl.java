package com.test.springboottest.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.test.springboottest.Student;
import com.test.springboottest.dao.StudentDao;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;
	@Override
	public Student updateStudent(Student student) {
		// TODO Auto-generated method stub
		return studentDao.save(student);
	}
	@Override
	public boolean insertUploadFileData(MultipartFile multipartFile) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
		//String excelFilePath = UPLOAD_DIR+File.separator+multipartFile.getOriginalFilename();

		//FileInputStream inputStream = new FileInputStream(excelFilePath);
		InputStream inputStream = multipartFile.getInputStream();
		
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

		XSSFSheet sheet = workbook.getSheetAt(0);

		// Using for loop
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(1).getLastCellNum();

		for (int r = 1; r <= rows; r++) {

			XSSFRow row = sheet.getRow(r);

			int id = 0;
			String name = "", city = "";
			for (int c = 0; c < cols; c++) {

				XSSFCell cell = row.getCell(c);

				XSSFRow rowField = sheet.getRow(0);
				XSSFCell cellField = rowField.getCell(c);

				if (cellField.getStringCellValue().equalsIgnoreCase("Id")) {
					id = (int) cell.getNumericCellValue();
				} else if (cellField.getStringCellValue().equalsIgnoreCase("Name")) {
					name = cell.getStringCellValue();
				} else if (cellField.getStringCellValue().equalsIgnoreCase("City")) {
					city = cell.getStringCellValue();
				}
			}
			System.out.println("id=> "+id+"  name=> "+name+" city=> "+city);
			Student st = new Student();
			st.setId(id);
			st.setName(name);
			st.setCity(city);
			
			studentDao.save(st);
		}		
		flag = true;	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	
	return flag;
	}

}
