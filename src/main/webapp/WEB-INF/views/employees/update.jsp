<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Cp1251"/>
<title>Редактирование</title>
</head>
<body>
<form:form  method="put" modelAttribute="employee" enctype="multipart/form-data">
	<table>
		
		<tr>
			<td><form:label path="employeeFIO">
				<spring:message code="label.fio" />
			</form:label></td>
			<td><form:input path="employeeFIO" /> <br />
				<form:errors path="employeeFIO" cssClass="error"/>
			</td>
		</tr>
		<tr>
			<td><form:label path="employeeAge">
				<spring:message code="label.age" />
			</form:label></td>
			<td><form:input path="employeeAge"/> <br />
				<form:errors path="employeeAge" cssClass="error"/>
			</td>
		</tr>
		<tr>
			<td><form:label path="birthDate">
				<spring:message code="label.birthdate"  />
			</form:label></td>
			<td><form:input path="birthDate"/><small>Например: 10.06.1992</small>
			 </td>
		</tr>
		<tr>
			<td><form:label path="profession">
				<spring:message code="label.profession" />
			</form:label></td>
			<td><form:input path="profession" /> <br />
				<form:errors path="profession" cssClass="error">
					Профессия сотрудника должна иметь диапазон символов от 1 до 255</form:errors>
			</td> 
		</tr>
		<tr>
			<td><form:label path="description">
				<spring:message code="employee.description" />
			</form:label></td>
			<td><form:textarea path="description" cols="40" rows="8" />
			</td> 
		</tr>
		<tr>
			<td>
				<label for="image">Фотография</label>
			</td>
			<td><input name="image" type="file"> <small>Поддерживаемые форматы JPEG, размер не более 50 КБ</small></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit"
				value="<spring:message code="label.saveChanges"/>" /></td>
		</tr>
	</table>
</form:form>
</body>
</html>
