<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<p></p>
<table border="1">Table of Clients
    <tr>
        <td>id</td>
        <td>name</td>
        <td>secondName</td>
        <td>phoneNumber</td>
    </tr>
    <c:forEach items="${clientsList}" var="clientsTable">
        <tr>
            <td><c:out value="${clientsTable.id}"/></td>
            <td><c:out value="${clientsTable.name}"/></td>
            <td><c:out value="${clientsTable.secondName}"/></td>
            <td><c:out value="${clientsTable.phoneNumber}"/></td>
        </tr>
    </c:forEach>
</table>

<p></p>

<table border="1">Table of products
    <tr>
        <td>code</td>
        <td>name</td>
        <td>price</td>
        <td>amount</td>
    </tr>
    <c:forEach items="${productsList}" var="productsTable">
        <tr>
            <td><c:out value="${productTable.code}"/></td>
            <td><c:out value="${productTable.name}"/></td>
            <td><c:out value="${productTable.price}"/></td>
            <td><c:out value="${productTable.amount}"/></td>
        </tr>
    </c:forEach>
</table>

<p></p>

<table border="1">Table of orders
    <tr>
        <td>clientID</td>
        <td>productID</td>
        <td>totalPrice</td>
        <td>totalAmount</td>
        <td>orderDate</td>
    </tr>
    <c:forEach items="${ordersList}" var="ordersTable">
        <tr>
            <td><c:out value="${ordersTable.clientID}"/></td>
            <td><c:out value="${ordersTable.productID}"/></td>
            <td><c:out value="${ordersTable.totalPrice}"/></td>
            <td><c:out value="${ordersTable.totalAmount}"/></td>
            <td><c:out value="${ordersTable.orderDate}"/></td>
        </tr>
    </c:forEach>
</table>
<p><a href="/adminPanel.html">EXIT</a></p>

</body>
</html>