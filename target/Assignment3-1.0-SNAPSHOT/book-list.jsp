<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Book List</title>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
            padding: 10px;
        }
    </style>
</head>
<body>

<h2>Book List</h2>

<!-- Table to display all books -->
<table>
    <thead>
        <tr>
            <th>ISBN</th>
            <th>Title</th>
            <th>Authors</th>
            <th>Price</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="book" items="${listBooks}">
            <tr>
                <td>${book.isbn}</td>
                <td>${book.title}</td>
                <td>${book.authors}</td>
                <td>${book.price}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<!-- Link to add a new book -->
<a href="book-count.jsp">Add New Book</a>

</body>
</html>