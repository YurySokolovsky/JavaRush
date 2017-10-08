<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Результат операции</title>

</head>
<body>
<h2>Чтение книги</h2>
<p>
<h3>${message}<br/></h3>
<hr/>
<p>Здесь должно находиться, без сомения, одно из увлекательнейших повествований,
   но в виду учебного назначения данного проекта вас приветствует Чеширский Кот!</p>
<br/>
<p><img src="/images/Cat.png" alt="ПРИ-И-И-ВЕТ!!!"> </p>
<form action="${pageContext.request.contextPath}/result.html">
    <input type="submit" value="К результатам последнего поиска"/>
</form>
<br/>

<form action="${pageContext.request.contextPath}/list.html">
    <input type="submit" value="К списку книг"/>
</form>
</p>
</body>
</html>
