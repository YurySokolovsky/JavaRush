<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<?xml version="1.0" encoding="UTF-8" ?>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <title>Search book page</title>
  <style>
    h4 {
      color: red;
    }
  </style>
</head>
<body>
<h1>ПОИСК</h1>
<p>Выберите поле по которому хотите искать и введите информацию</p>
<p>Можно одновременно использовать несколько полей</p>
<hr/>
<p><h4>${isSearch}</h4></p>
<form:form method="POST" commandName="searchBook" action="${pageContext.request.contextPath}/search-init.html">
  <table>
    <tbody>
    <tr>
      <td>Номер:</td>
      <td><form:input path="id" type="number" min="0" max="2147483647" /></td>
    </tr>
    <tr>
      <td>Наименование:</td>
      <td><form:input path="title" maxlength="100" size="50" placeholder="Не более 100 символов"  /></td>
      <td><form:errors path="title" /></td>
    </tr>
    <tr>
      <td>Аннотация:</td>
      <td><form:textarea path="description" maxlength="100" cols="50" rows="3" placeholder="Не более 255 символов"  /></td>
      <td><form:errors path="description" /></td>
    </tr>
    <tr>
      <td>Автор:</td>
      <td><form:input path="author" maxlength="100" size="50" placeholder="Не более 100 символов"  /></td>
      <td><form:errors path="author" /></td>
    </tr>
    <tr>
      <td>ISBN:</td>
      <td><form:input path="isbn" maxlength="20" size="50" placeholder="Не более 20 символов"  /></td>
      <td><form:errors path="isbn" /></td>
    </tr>
    <tr>
      <td>Год:</td>
      <td><form:input  path="printYear" type="number" max="2200" min="1900" placeholder="С 1900 г. до 2200 г."/></td>
      <td><form:errors path="printYear" /></td>
    </tr>
    <tr>
      <td>Искать прочитанные:</td>
      <td><form:checkbox path="readAlready" /></td>
    </tr>
    <tr>
      <td style="color:blue;">Будте внимательны: </td>
      <td style="color:blue;">По умолчанию ищутся непрочитанные книги!</td>
    </tr>
    <tr>
      <td><input type="submit" value="Искать книгу" /></td>
      <td></td>
    </tr>
    </tbody>
  </table>
</form:form>
<br>
<form method="GET" action="${pageContext.request.contextPath}/list.html">
  <input type="submit" value="К списку книг"/>
</form>

</body>
</html>