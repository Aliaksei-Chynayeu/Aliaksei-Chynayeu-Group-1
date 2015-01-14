<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Spring 3 MVC Series - Book Manager</title>
</head>
<body>
<h2>Book Manager</h2>
<form:form method="post" commandName="book" action="addBook.html">

    <table>
    <tr>
        <td><form:label path="firstname">First Name</form:label></td>
        <td><form:input path="firstname" /></td> 
    </tr>
    <tr>
        <td><form:label path="lastname">Last Name</form:label></td>
        <td><form:input path="lastname" /></td>
    </tr>
    <tr>
        <td><form:label path="lastname">Email</form:label></td>
        <td><form:input path="email" /></td>
    </tr>
    <tr>
        <td><form:label path="lastname">Telephone</form:label></td>
        <td><form:input path="telephone" /></td>
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
        <td>${book.lastname}, ${book.firstname} </td>
        <td>${book.email}</td>
        <td>${book.telephone}</td>
        <td><a href="delete/${book.id}">delete</a></td>
    </tr>
</c:forEach>
</table>
</c:if>
</body>
</html>

