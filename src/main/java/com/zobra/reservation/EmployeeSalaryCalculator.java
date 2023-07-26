package com.zobra.reservation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class EmployeeSalaryCalculator {
    public static void main(String[] args) {
        String InputFilePath = "src/main/resources/Book1.xlsx";
        String outputFilePath = "src/main/resources/Book3.xlsx";

        try {
            FileInputStream fileInputStream = new FileInputStream(InputFilePath);
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);

            // Calculate and update the salary for each employee
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    // Skip the header row
                    continue;
                }

                Cell experienceCell = row.getCell(4);
                int experience = (int) experienceCell.getNumericCellValue();
                double salary = calculateSalary(experience);

                Cell salaryCell = row.createCell(5, CellType.NUMERIC);
                salaryCell.setCellValue(salary);
            }

            // Write the updated data back to the excel file
            FileOutputStream fileOutputStream = new FileOutputStream(outputFilePath);
            workbook.write(fileOutputStream);

            // Close streams and release resources
            fileInputStream.close();
            fileOutputStream.close();
            workbook.close();

            System.out.println("Salary calculation and updating the file completed successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static double calculateSalary(int experience) {
        if (experience < 5) {
            return 1000 * 5;
        } else if (experience >= 5 && experience < 10) {
            return 2500 * 5;
        } else if (experience >= 10 && experience < 20) {
            return 5000 * 5;
        } else {
            return 8000 * 5;
        }
    }
}
