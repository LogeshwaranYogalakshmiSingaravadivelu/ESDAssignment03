<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Multiple Books</title>
</head>
<body>

<h2>Add <c:out value="${count}"/> Books</h2>

<!-- Form to insert multiple books based on the count -->
<form action="part7_insert" method="post">
    <c:forEach var="i" begin="1" end="${count}">
        <div class="book-entry">
            <h3>Book ${i}</h3>
            <label for="isbn${i}">ISBN:</label>
            <input type="text" name="isbn" required><br><br>

            <label for="title${i}">Title:</label>
            <input type="text" name="title" required><br><br>

            <label for="authors${i}">Authors:</label>
            <input type="text" name="authors" required><br><br>

            <label for="price${i}">Price:</label>
            <input type="number" step="0.01" name="price" required><br><br>
        </div>
        <hr>
    </c:forEach>

    <button type="submit">Add Books</button>
</form>

<a href="${pageContext.request.contextPath}/part7_list">Back to Book List</a>

</body>
</html>