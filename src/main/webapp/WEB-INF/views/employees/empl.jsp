<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Карточка сотрудника</title>
<div>
	<h2>
		<spring:message code="employee.title" />
		${employee.employeeFIO}
	</h2>

	<table cellspacing="15">
			<tr>
				<td>
					<strong>Кодовый номер: </strong><c:out value="${employee.employeeID}" /> <br />
					<strong>Ф.И.О.: </strong><c:out value="${employee.employeeFIO}" /><br />
					<strong>Возраст: </strong><c:out value="${employee.employeeAge}" /><br />
					<strong>Дата рождения: </strong><c:out value="${employee.birthDate}" /><br />
					<strong>Специальность: </strong><c:out value="${employee.profession}" /> <br />
					<strong>Биография: </strong><c:out value="${employee.description}" /> <br />
					<strong>Фотография: </strong><div><img src="${employeeImage}"  width="100" height="80"></div>
				</td>
				
			</tr>
	</table>
	
	<h4><a href="/MISTmvc/employees/employee/${employee.employeeID}/update">Редактировать</a></h4>
	
	<h3> <a href="/MISTmvc/index">Список сотрудников</a></h3>
</div>
