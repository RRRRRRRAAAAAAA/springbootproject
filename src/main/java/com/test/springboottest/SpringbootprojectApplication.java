package com.test.springboottest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
@SpringBootApplication
public class SpringbootprojectApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(SpringbootprojectApplication.class, args);
		
		Configuration cfg = new Configuration();
		cfg.configure("com/test/springboottest/hibernate.cfg.xml");

		SessionFactory sf = cfg.buildSessionFactory();

		String excelFilePath = ".\\src\\dataFiles\\studentData.xlsx";

		FileInputStream inputStream = new FileInputStream(excelFilePath);

		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

		XSSFSheet sheet = workbook.getSheetAt(0);

		// Using for loop
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(1).getLastCellNum();

		Session session = sf.openSession();

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
			Student st = new Student();
			st.setId(id);
			st.setName(name);
			st.setCity(city);

			Transaction tx = session.beginTransaction();
			session.save(st);
			tx.commit();
		}
		session.close();
		
	}

}
