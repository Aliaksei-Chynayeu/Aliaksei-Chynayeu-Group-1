<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Spring 3 MVC Series - Book Manager</title>
</head>
<body>
<h2>Book Manager</h2>
<form:form method="post" commandName="book" action="addBook">

    <table>
    <tr>
        <td><form:label path="title">Title</form:label></td>
        <td><form:input path="title" /></td> 
    </tr>
    <tr>
        <td><form:label path="author">Author</form:label></td>
        <td><form:input path="author" /></td>
    </tr>
    <tr>
        <td><form:label path="pages">Pages</form:label></td>
        <td><form:input path="pages" /></td>
    </tr>
    <tr>
        <td><form:label path="email">email</form:label></td>
        <td><form:input path="email" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Add Book"/>
        </td>
    </tr>
</table>    
    
</form:form>

<h3>Books</h3>
<c:if  test="${!empty bookList}">
<table class="data">
<tr>
    <th>Name</th>
    <th>Email</th>
    <th>Telephone</th>
    <th>&nbsp;</th>
</tr>
<c:forEach items="${bookList}" var="book">
    <tr>
        <td>${book.author}, ${book.title} </td>
        <td>${book.email}</td>
        <td>${book.pages}</td>
        <td><a href="delete/${book.id}">delete</a></td>
    </tr>
</c:forEach>
</table>
</c:if>
</body>
</html>

