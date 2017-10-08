<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<?xml version="1.0" encoding="UTF-8" ?>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Редактирование книгу</title>
</head>
<body>
<h1>Редактирование книгу</h1>
<p>Для редактирования книги измените поля ниже</p>
<p>${message}</p>
<form:form method="POST" commandName="book" action="${pageContext.request.contextPath}/edit/${book.id}.html">
<table>
<tbody>
	<tr>
		<td>Наименование:</td>
		<td><form:input path="title" maxlength="100" size ="50" required="yes" placeholder="Не более 100 символов" /></td>
		<td><form:errors path="title" /></td>
	</tr>
	<tr>
		<td>Аннотация:</td>
		<td><form:textarea path="description" maxlength="255" cols="50" rows="3" required="yes" placeholder="Не более 255 символов" /></td>
		<td><form:errors path="description" /></td>
	</tr>
	<tr>
		<td>Автор:</td>
		<td><form:input path="author" maxlength="100" size ="50" readonly="true"  /></td>
		<td><form:errors path="author" /></td>
	</tr>
	<tr>
		<td>ISBN:</td>
		<td><form:input path="isbn" maxlength="20" size ="50" required="yes" placeholder="Не более 20 символов"  /></td>
		<td><form:errors path="isbn" /></td>
	</tr>
	<tr>
		<td>Год:</td>
		<td><form:input  path="printYear" type="number" min="1900" max="2200" required="yes" placeholder="С 1900 г. до 2200 г."/></td>
		<td><form:errors path="printYear" /></td>
	</tr>
	<tr>
		<td>Прочтено:</td>
		<td><form:checkbox path="readAlready" disabled="true"/></td>
	</tr>
	<hr>
	<tr>
		<td><input type="submit" value="Отредактировать" /></td>
	<tr>
</tbody>
</table>
</form:form>

<form action="${pageContext.request.contextPath}/result.html">
	<input type="submit" value="К результатам последнего поиска"/>
</form>

<br>

<form method="POST" action="${pageContext.request.contextPath}/list.html">
	<input type="submit" value="К списку книг"/>
</form>

</body>
</html>