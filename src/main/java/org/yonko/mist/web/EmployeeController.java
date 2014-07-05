package org.yonko.mist.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.yonko.mist.domain.Employee;
import org.yonko.mist.service.EmailService;
import org.yonko.mist.service.EmployeeService;

@Controller
@ManagedResource(objectName = "employee:name=EmployeeController")
public class EmployeeController {
	
	private static final int DEFAULT_EMPLOYEES_PER_PAGE = 5;
	
	private int employeesPerPage = DEFAULT_EMPLOYEES_PER_PAGE;
		
	@Autowired
	private EmployeeService employeeService;
	
	@ManagedAttribute
	public int getEmployeesPerPage() {
		return employeesPerPage;
	}
	
	@ManagedAttribute
	public void setEmployeesPerPage(int employeesPerPage) {
		this.employeesPerPage = employeesPerPage;
	}
	
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@RequestMapping("/index")
	public String listEmployees(Map<String, Object> model){
		model.put("employee", new Employee());
		model.put("employeeList", employeeService.getAllEmployees());
		
		return "employee";
	}
	
	@RequestMapping("/")
	public String home(){
		return "redirect:/index";
	}
	
/*	@RequestMapping(value = "add",method=RequestMethod.POST)
	public String addEmployee(@ModelAttribute("employee") @Valid Employee employee, BindingResult result) {
		
		if (result.hasErrors()) {
			return "redirect:/index";
		}
		employeeService.addEmployee(employee);
		
		return "redirect:/employees/employee/" + employee.getEmployeeID();
	}
	*/
	@RequestMapping(value = "/delete/{employeeID}", method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable("employeeID") Integer employeeID) {
		employeeService.removeEmployee(employeeID);
		
		return "redirect:/index";
	}
	
	@RequestMapping("/by")
	public String logout(Map<String, Object> model) {
		model.put("serverTime", DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
				DateFormat.MEDIUM, Locale.getDefault()).format(new Date()));
		return "home";
	}
	
	/**ВЫГРУЗКА В EXCEL ----------------------------------------------------------НАЧАЛО*/
	@RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
	public ModelAndView downoloadExcel(){
		List<Employee> employees = employeeService.getAllEmployees();
		
		return new ModelAndView("excelView", "employees", employees);
	}
	/**ВЫГРУЗКА В EXCEL ----------------------------------------------------------КОНЕЦ*/
	
	/**ВЫГРУЗКА В PDF ----------------------------------------------------------НАЧАЛО*/
	@RequestMapping(value = "/downloadPdf", method = RequestMethod.GET)
	public ModelAndView downloadPdf() {
		List<Employee> employees = employeeService.getAllEmployees();
		
		return new ModelAndView("pdfView", "employees", employees);
	}
	/**ВЫГРУЗКА В PDF ----------------------------------------------------------КОНЕЦ*/
	
	/**Поиск сотрудника по фамилии (Прямо с главной формы - там разместил поле ввода с именем fio)
	 * в заголовке элемента form:form поставил параметры method=POST, action=search*/
	@RequestMapping(method = RequestMethod.POST, value = "search")
	public String searchForm(@RequestParam("fio") String fio, Model model) {
		
		if (!fio.isEmpty()) {
			List<Employee> employees = employeeService.searchEmployee(fio);
			if (employees != null) {
				model.addAttribute("employeeList", employeeService.searchEmployee(fio));
			}
		}
		return "search_employee";
	}
	
	/**Устанавливает формат ввода и отображения даты в форме*/
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		dateFormat.setLenient(false);
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	 }
}