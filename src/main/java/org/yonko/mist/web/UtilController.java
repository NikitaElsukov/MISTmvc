package org.yonko.mist.web;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.jets3t.service.S3Service;
import org.jets3t.service.acl.AccessControlList;
import org.jets3t.service.acl.GroupGrantee;
import org.jets3t.service.acl.Permission;
import org.jets3t.service.impl.rest.httpclient.RestS3Service;
import org.jets3t.service.model.S3Bucket;
import org.jets3t.service.model.S3Object;
import org.jets3t.service.security.AWSCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
import org.springframework.web.multipart.MultipartFile;
import org.yonko.mist.domain.Employee;
import org.yonko.mist.exceptions.ImageUploadException;
import org.yonko.mist.service.EmailService;
import org.yonko.mist.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class UtilController {
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Value("C:/Users/1/Desktop/mist")
	private String webRootPath;		//Корневой путь на сервере для хранения файлов
	
/*	//http://localhost:8080/MISTmvc/employees/employee?employeeID=5    По этому запросу замаппит
	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public String getEmployeeById(@RequestParam("employeeID") Integer id, Map<String, Object> model) {
		Employee employee = employeeService.getEmployeeById(id);
		model.put("employee", employee);
		return "employees/empl";
	}*/
	
	/**БЛОК ФОРМЫ РЕДАКТИРОВАНИЯ СОТРУДНИКА (GET/POST)  ------------------------НАЧАЛО
	 * */
	@RequestMapping(value = "employee/{employeeID}/update", method = RequestMethod.GET)
	public String updateForm(@PathVariable("employeeID") Integer employeeID, Model model) {
		model.addAttribute("employee", employeeService.getEmployeeById(employeeID));
		return "employees/update";
	}
	
	@RequestMapping(value = "employee/{employeeID}/update", method = RequestMethod.POST)
	public String updateEmployee(@Valid Employee employee, BindingResult result,
			@RequestParam(value = "image", required = false) @Valid MultipartFile image){
		if (result.hasErrors()) {
			return "employees/update";
		}
		employeeService.updateDescription(employee);
		
		try {
			if (!image.isEmpty() && image.getSize() < 500000) {
				validateImage(image);
				saveImage(employee.getEmployeeID() + ".jpg", image);
			}
		} catch (ImageUploadException e) {
			result.reject(e.getMessage());
			return "employees/update";
		}
		return "redirect:/employees/employee/" + employee.getEmployeeID();
	}
	
	//1-й вариант сэйва файла в файловой системе на веб сервере
	private void saveImage(String filename, MultipartFile image) {
		
		try {
			File file = new File(webRootPath + "/resources/" + filename);
			FileUtils.writeByteArrayToFile(file, image.getBytes());
		} catch (IOException e) {
			throw new ImageUploadException("Unable to save image", e);
		}
	}
	
	//2-й вариант сэйва файла, на этот на серверах Amazon S3
	private void saveImageS3(String filename, MultipartFile image) {
		
		try {
		AWSCredentials awsCredentials = new AWSCredentials("", "");//awsAccessKey
																   //awsSecretAccessKey
		S3Service s3 = new RestS3Service(awsCredentials);	// Настройка S3
		
		// Создать объекты, представляющие хранилище и изображение
		S3Bucket imageBucket = s3.getBucket("employeeImages");
		S3Object imageObject = new S3Object(filename);
		
		// Скопировать данные изображения в объект
		imageObject.setDataInputStream(new ByteArrayInputStream(image.getBytes()));
		imageObject.setContentLength(image.getBytes().length);
		imageObject.setContentType("image/jpeg");
		
		// Определить разрешения
		AccessControlList acl = new AccessControlList();
		acl.setOwner(imageBucket.getOwner());
		acl.grantPermission(GroupGrantee.ALL_USERS, Permission.PERMISSION_READ);
		imageObject.setAcl(acl);
		
		// Сохранить изображение
		s3.putObject(imageBucket, imageObject);
		}catch (Exception e) {
			throw new ImageUploadException("Unable to save image", e);
		}
	}

	private void validateImage(MultipartFile image) throws ImageUploadException {
		if (!image.getContentType().equals("image/jpeg")) {
			throw new ImageUploadException("Only JPG images accepted");
		}
	}
	
	/***БЛОК ФОРМЫ РЕДАКТИРОВАНИЯ СОТРУДНИКА (GET/POST)  ------------------------КОНЕЦ*/
	
	//Форматирует дату, введенную в форме пользователем, в заданном формате
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
	 SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
	 dateFormat.setLenient(false);
	 webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	 }
	
	/**ДОБАВЛЕНИЕ СОТРУДНИКА -----------------------------------------------------НАЧАЛО*/
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addEmployeeGetForm(Model model) {
		model.addAttribute("newEmployee", new Employee());
		return "employees/new_employee";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	//@PreAuthorize("(hasRole('ROLE_ADMIN') and #image != null)")
	public String addEmployeePostForm(@ModelAttribute("newEmployee") @Valid Employee employee, 
			BindingResult result, Model model, @RequestParam(value = "image", required = false) MultipartFile image) {
		if (result.hasErrors()) {
			return "employees/new_employee";
		}
		employeeService.addEmployee(employee);		
		try {
			if (!image.isEmpty()) {
				validateImage(image);
				saveImage(employee.getEmployeeID() + ".jpg", image);
			//	model.addAttribute("employeeImage", IOUtils.toByteArray(image.getInputStream()));
			}
		} catch (ImageUploadException e) {
			result.reject(e.getMessage());
			return "employees/update";
		}
		try {
			emailService.sendAttachmentEmployeeEmail("nikita.elsukov.sa@mail.ru", employee);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		return "redirect:/employees/employee/" + employee.getEmployeeID();
	}	
	
	/**ДОБАВЛЕНИЕ СОТРУДНИКА -----------------------------------------------------КОНЕЦ*/
	
	
/*	*//**RESTful URL обработка запросов --------------------------------------------НАЧАЛО*//*
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Employee createEmployee(@Valid Employee employee, 
			BindingResult result, HttpServletResponse response) throws BindException {
		if (result.hasErrors()) {
			throw new BindException(result);
		}
		employeeService.addEmployee(employee);
		
		//Указать расположение ресурса
		response.setHeader("Location", "/employees/employee/" + employee.getEmployeeID());
		return employee;
	}*/
	
	//http://localhost:8080/MISTmvc/employees/employee/{employeeID}    По этому запросу замаппит
	@RequestMapping(value = "/employee/{employeeID}", method = RequestMethod.GET)
	public String getEmployeeById(@PathVariable("employeeID") Integer id, Model model) {
		model.addAttribute("employee",employeeService.getEmployeeById(id));
		return "employees/empl";
	}
	
	/**RESTful URL обработка запросов ---------------------------------------------КОНЕЦ*/
}
