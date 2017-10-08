<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<?xml version="1.0" encoding="UTF-8" ?>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Добавление книги</title>
</head>

<body>
<h1>ДОБАВЛЕНИЕ КНИГИ</h1>
<p>Для добавления новой книги заполните поля ниже</p>

<!--Форма для заполнения-->
<form:form method="POST" commandName="book" action="${pageContext.request.contextPath}/add.html">
<table>
<tbody>
	<tr>
		<td>Наименование:</td>
		<td><form:input path="title" maxlength="100" size ="40" required="yes" placeholder="Не более 100 символов" /></td>
		<td><form:errors path="title" /></td>
	</tr>
	<tr>
		<td>Аннотация:</td>
		<td><form:input path="description" maxlength="255" size ="40" required="yes" placeholder="Не более 255 символов" /></td>
		<td><form:errors path="description" /></td>
	</tr>
	<tr>
		<td>Автор:</td>
		<td><form:input path="author" maxlength="100" size ="40" required="yes" placeholder="Не более 100 символов"  /></td>
		<td><form:errors path="author" /></td>
	</tr>
	<tr>
		<td>ISBN:</td>
		<td><form:input path="isbn" maxlength="20" size ="40" required="yes" placeholder="Не более 20 символов"  /></td>
		<td><form:errors path="isbn" /></td>
	</tr>
	<tr>
		<td>Год:</td>
		<td><form:input  path="printYear" type="number" min="1900" max="2200" required="yes" placeholder="С 1900 г. до 2200 г."/></td>
		<td><form:errors path="printYear" /></td>
	</tr>
	<tr>
		<td>Прочтено:</td>
		<td><form:checkbox path="readAlready" /></td>
	</tr>

	<hr>
	<tr>
		<td><input type="submit" value="Добавить    " /></td>
		<td></td>
	</tr>
</tbody>
</table>
</form:form>

<!--Кнопка к списку книг-->
<form method="POST" action="${pageContext.request.contextPath}/list.html">
	<input type="submit" value="К списку книг"/>
</form>

</body>
</html>