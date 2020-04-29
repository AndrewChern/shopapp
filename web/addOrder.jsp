<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create orders</title>
</head>
<body>

<p>Welcome</p>

<table border="1">
    <c:forEach items="${productList}" var="prod">
        <tr>
            <td><c:out value="${prod.code}"/></td>
            <td><c:out value="${prod.name}"/></td>
            <td><c:out value="${prod.count}"/></td>
            <td><c:out value="${prod.price}"/></td>
        </tr>
    </c:forEach>
</table>

<form action="/addOrder" method="POST">
    <p><select name= "idPerson">
        <option > Select client</option>
        <c:forEach items="${clientList}" var="person">
            <option value="${person.id}" > ${person.name} </option>
        </c:forEach>
    </select></p>

    <p><select name = "idCode">
        <option > Select product</option>
        <c:forEach items="${productList}" var="product">
            <option value="${product.code}" > ${product.name} </option>
        </c:forEach>
    </select></p>

    <p>Quantity of product:</p> <input type="text" name="count">

    <p><input type="submit" value="Send"></p>

</form>

<p><a href="/adminPanel.html">EXIT</a></p>

</body>
</html>