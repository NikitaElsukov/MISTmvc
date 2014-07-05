<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title><spring:message code="label.title" /></title>
<style>
body {
	color: #006400;
}

table {
	border: 4px double #333; /* Рамка вокруг таблицы */
	border-collapse: separate; /* Способ отображения границы */
	width: 75%; /* Ширина таблицы */
	border-spacing: 7px 11px; /* Расстояние между ячейками */
}

td {
	padding: 5px; /* Поля вокруг текста */
	border: 1px solid #a52a2a; /* Граница вокруг ячеек */
}
</style>
</head>
<body>
	<h2>Привет! <security:authentication property="principal.username"/></h2>
	
	<a href="<c:url value="/logout" />"> <spring:message
			code="label.logout" />
	</a>
	<h2>
		<spring:message code="label.title" />
	</h2>
	
	<h3><a href="/MISTmvc/index">На главную</a></h3>

	<form:form method="POST" action="search">
		<label for="fio">Введите Ф.И.О. сотрудника</label>
		<input name="fio" type="text"/>
		<input type="submit" value="Найти" />
	</form:form>
	<h3>
		<spring:message code="label.employees" />
	</h3>
	<c:choose>
	 <c:when test="${!empty employeeList}">
		<table class="data">
			<tr>
				<th><spring:message code="label.fio" /></th>
				<th><spring:message code="label.age" /></th>
				<th><spring:message code="label.birthdate" /></th> 
				<th><spring:message code="label.profession" /></th>
				<th>&nbsp;</th>
			</tr>
			<c:forEach items="${employeeList}" var="employee">
				<tr>
					<td>${employee.employeeFIO}</td>
					<td>${employee.employeeAge}</td>
					<td><fmt:formatDate value="${employee.birthDate}" pattern="dd.MM.yyyy"/></td>
					<td>${employee.profession}</td>
					<security:authorize access="hasRole('ROLE_ADMIN')">
					<td><a href="delete/${employee.employeeID}"><spring:message
								code="label.delete" /></a></td>
					<td><a
						href="employees/employee/${employee.employeeID}"> <spring:message
								code="employee.description" /></a></td>
					</security:authorize>
				</tr>
			</c:forEach>
		</table>
	</c:when>
	<c:otherwise> 
	<font size="24" class="Tahoma">По Вашему запросу данных не найдено.</font>
	</c:otherwise>
	</c:choose>
</body>
</html>