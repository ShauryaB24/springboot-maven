package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;
    private final DataFormatter dataFormatter = new DataFormatter();

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public void saveStudents(MultipartFile file) throws Exception {
        List<Student> students = getStudentsFromExcel(file.getInputStream());
        repository.saveAll(students);
    }

    private List<Student> getStudentsFromExcel(InputStream inputStream) throws Exception {
        List<Student> list = new ArrayList<>();

        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);  // First sheet

        // Start from row index 1 (skip header row at index 0)
        for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            if (row == null) continue;

            String firstName = getCellValue(row.getCell(0));
            String lastName = getCellValue(row.getCell(1));
            String rollNumber = getCellValue(row.getCell(2));
            String className = getCellValue(row.getCell(3));

            // Skip empty rows
            if (isBlank(firstName) && isBlank(lastName) && isBlank(rollNumber) && isBlank(className)) {
                continue;
            }

            Student student = new Student();
            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setRollNumber(rollNumber);
            student.setClassName(className);

            list.add(student);
        }

        workbook.close();
        return list;
    }

    private String getCellValue(Cell cell) {
        if (cell == null) return "";
        return dataFormatter.formatCellValue(cell).trim();
    }

    private boolean isBlank(String val) {
        return val == null || val.trim().isEmpty();
    }
}
