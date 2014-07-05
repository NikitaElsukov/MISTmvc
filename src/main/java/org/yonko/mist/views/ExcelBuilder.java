package org.yonko.mist.views;

import java.text.DateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.web.servlet.view.document.AbstractExcelView;
import org.yonko.mist.domain.Employee;

public class ExcelBuilder extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<Employee> employees = (List<Employee>) model.get("employees");
		
		 // create a new Excel sheet
		HSSFSheet sheet = workbook.createSheet("Список сотрудников M.I.S.T.");
		sheet.setDefaultColumnWidth(30);
		
		// create style for header cells
		CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);

        // create header row
        HSSFRow header = sheet.createRow(0);
        
        header.createCell(0).setCellValue("Ф.И.О.");
        header.getCell(0).setCellStyle(style);
         
        header.createCell(1).setCellValue("Возраст");
        header.getCell(1).setCellStyle(style);
         
        header.createCell(2).setCellValue("Дата рождения");
        header.getCell(2).setCellStyle(style);
         
        header.createCell(3).setCellValue("Профессия");
        header.getCell(3).setCellStyle(style);
         
        header.createCell(4).setCellValue("Биография");
        header.getCell(4).setCellStyle(style);
        
        // create data rows
        int rowCount = 1;
        
        for (Employee employee : employees) {
        	HSSFRow aRow = sheet.createRow(rowCount++);
        	aRow.createCell(0).setCellValue(employee.getEmployeeFIO());
            aRow.createCell(1).setCellValue(employee.getEmployeeAge());
            aRow.createCell(2).setCellValue(DateFormat.getDateInstance(
            		DateFormat.MEDIUM).format(employee.getBirthDate()));
            aRow.createCell(3).setCellValue(employee.getProfession());
            aRow.createCell(4).setCellValue(employee.getDescription());
        }
	}

}
