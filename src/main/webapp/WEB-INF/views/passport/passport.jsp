<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<title>Паспорт сотрудника</title>
<div>
	<h2>
		<spring:message code="passport.title" />
	</h2>

	<table cellspacing="15">
			<tr>
				<td>
					<strong>Номер паспорта: </strong><c:out value="${passport.serialNumber}" /><br />
					<strong>Выдан: </strong><c:out value="${passport.issued}" /><br />
					<strong>Дата выдачи пасспорта: </strong><c:out value="${passport.issuedDate}" /> <br />
				</td>
				
			</tr>
	</table>
	
	<%-- <h4><a href="${employee.employeeID}?update">Редактировать</a></h4> --%>
	
	<h3> <a href="/MISTmvc/index">Список сотрудников</a></h3>
</div>
