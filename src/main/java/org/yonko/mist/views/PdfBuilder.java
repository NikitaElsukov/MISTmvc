package org.yonko.mist.views;

import java.text.DateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;
import org.yonko.mist.domain.Employee;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

public class PdfBuilder extends AbstractPdfView {

	public PdfBuilder() {
		setContentType("application/pdf");
	}

	@Override
	protected boolean generatesDownloadContent() {
		return true;
	}

	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		BaseFont baseFont = BaseFont.createFont("C:/Windows/Fonts/Andale_Mono.ttf",
				BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		Font font = new Font(baseFont, 12);
		List<Employee> employees = (List<Employee>) model.get("employees");
		Table employeeTable = new Table(5);
		employeeTable.setWidth(90);
		employeeTable.setBorderWidth(1);
		employeeTable.setBorder(Rectangle.NO_BORDER);

		employeeTable.addCell(new Phrase("Ф.И.О.", font));
		employeeTable.addCell(new Phrase("Возраст", font));
		employeeTable.addCell(new Phrase("Дата рождения", font));
		employeeTable.addCell(new Phrase("Профессия", font));
		employeeTable.addCell(new Phrase("Биография", font));

		for (Employee employee : employees) {
			employeeTable.addCell(new Phrase(employee.getEmployeeFIO(), font));
			employeeTable.addCell(new Phrase("" + employee.getEmployeeAge(), font));
			employeeTable.addCell(new Phrase(DateFormat.getDateInstance(DateFormat.MEDIUM)
					.format(employee.getBirthDate()), font));
			employeeTable.addCell(new Phrase(employee.getProfession(), font));
			employeeTable.addCell(new Phrase(employee.getDescription(), font));
		}
		document.add(employeeTable);
	}

}
