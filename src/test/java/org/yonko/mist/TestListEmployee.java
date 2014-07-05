package org.yonko.mist;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.yonko.mist.domain.Employee;
import org.yonko.mist.service.EmployeeService;
import org.yonko.mist.web.EmployeeController;

public class TestListEmployee {
	@Test
	public void shouldDisplayEmployees() {
		List<Employee> employees = Arrays.asList((Employee)new Employee(), new Employee(), new Employee());
		
		EmployeeService service = Mockito.mock(EmployeeService.class);
		Mockito.when(service.getAllEmployees()).thenReturn(employees);
		
		EmployeeController controller = new EmployeeController();
		controller.setEmployeeService(service);
		HashMap<String, Object> model = new HashMap<String, Object>();
		String viewName = controller.listEmployees(model);
		
		Assert.assertEquals("employee", viewName);
		
		Assert.assertSame(employees, model.get("employeeList"));
		Mockito.verify(service).getAllEmployees();
		
		
	}
}
