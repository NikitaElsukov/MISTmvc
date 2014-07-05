package org.yonko.mist.web;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.yonko.mist.domain.Passport;
import org.yonko.mist.service.EmployeeService;

@Controller
@RequestMapping("/passports")
@ManagedResource(objectName = "employee:name=PassportController")
public class PassportController {
	
	private int jmxTestProperty;
	
	@ManagedAttribute //экспортирует "jmxTestProperty" свойство как управляемый аттрибут
	public int getJmxTestProperty() {
		return jmxTestProperty;
	}
	
	@ManagedAttribute //экспортирует "jmxTestProperty" свойство как управляемый аттрибут
	public void setJmxTestProperty(int jmxTestProperty) {
		this.jmxTestProperty = jmxTestProperty;
	}
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(value = "/passport/{id}", method = RequestMethod.GET)
	public String getPassport(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("passport", employeeService.getPassportById(id));
		return "passport/passport";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET,
			headers = {"Accept=text/xml, application/json, text/html"})
	public @ResponseBody ResponseEntity<Passport> getPassport(@PathVariable("id") Integer id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setLastModified(new Date().getTime());
		return new ResponseEntity<Passport>(employeeService.getPassportById(id), headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, 
			headers = "Content-Type=application/json")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void putPassport(@PathVariable("id") Integer id, @RequestBody Passport passport) {
		employeeService.updatePassport(passport);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePassport(@PathVariable("id") Integer id) {
		employeeService.removePassport(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Passport addPassport(@RequestBody @Valid Passport passport, 
			HttpServletResponse response) throws BindException {	
		
		employeeService.addPassport(passport);
		
		//Указать расположение ссозданного ресурса
		response.setHeader("Location", "/passports/" + passport.getId());
		return passport;
	}
}
