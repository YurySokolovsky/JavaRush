<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<?xml version="1.0" encoding="UTF-8" ?>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <title>Список книг</title>

  <link rel="stylesheet" href="/css/styleTabl.css" type="text/css">

</head>
<body>
<h1>Результат поиска</h1>
<p>Здесь отображаются результаты вашего поиска</p>
<!--Таблица книг-->
<table  class="bordered">
  <thead>
  <tr>
    <th width="15px">ID</th>
    <th width="150px">Наименование</th>
    <th width="300px">Аннотация</th>
    <th width="100px">Автор</th>
    <th width="140px">ISBN</th>
    <th width="40px">Год</th>
    <th width="50px">Читать</th>
    <th width="50px">Редактировать</th>
    <th width="30px">Удалить</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="book" items="${books}">
    <tr>
      <td>${book.id}</td>
      <td>${book.title}</td>
      <td>${book.description}</td>
      <td>${book.author}</td>
      <td>${book.isbn}</td>
      <td>${book.printYear}</td>
      <td>
        <form method="GET" action="${pageContext.request.contextPath}/read/${book.id}.html">
             <input type="submit" value=${book.readAlready?"Прочитано":"Читать"} />
        </form>
       </td>
      <td>
        <form method="GET" action="${pageContext.request.contextPath}/edit/${book.id}.html">
                <input type="submit" value="Редактировать"/>
        </form>
      </td>
      <td>
          <form method="GET" action="${pageContext.request.contextPath}/delete/${book.id}.html">
                <input type="submit" value="Удалить"/>
          </form>
      </td>
    </tr>
  </c:forEach>

  <form method="GET" action="${pageContext.request.contextPath}/search-init.html">
    <input type="submit" value="Поиск"/>
  </form>
  <br/>
  <br/>
  <br/>
  <b>Найдено книг: ${sizeresult} </b>
  <br/>
  <br/>
  <br/>
  </tbody>
</table>

<br/>
<form method="GET" action="${pageContext.request.contextPath}/add.html">
  <input type="submit" value="Добавить книгу"/>
</form>
<br/>
<form method="GET" action="${pageContext.request.contextPath}/list.html">
  <input type="submit" value="К списку книг   "/>
</form>
<br/>
<br/>
<div id="pagination">

  <c:url value="/result" var="prev" >
    <c:param name="page" value="${page-1}"/>
  </c:url>
  <c:if test="${page > 1}">
    <a href="<c:out value="${prev}" />" class="pn prev">Предыдущая </a>
  </c:if>

  <c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
    <c:choose>
      <c:when test="${page == i.index}">
        <span>${i.index}</span>
      </c:when>
      <c:otherwise>
        <c:url value="/result" var="url">
          <c:param name="page" value="${i.index}"/>
        </c:url>
        <a href='<c:out value="${url}" />'>${i.index}</a>
      </c:otherwise>
    </c:choose>
  </c:forEach>
  <c:url value="/result" var="next">
    <c:param name="page" value="${page + 1}"/>
  </c:url>
  <c:if test="${page + 1 <= maxPages}">
    <a href='<c:out value="${next}" />' class="pn next"> Следующая</a>
  </c:if>
</div>

</body>
</html>