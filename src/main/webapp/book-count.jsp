<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Select Number of Books</title>
</head>
<body>

<h2>How many books do you want to insert?</h2>

<form action="part7_showForm" method="post">
    <label for="count">Number of Books:</label>
    <input type="number" id="count" name="count" min="1" required><br><br>

    <button type="submit">Next</button>
</form>

<a href="${pageContext.request.contextPath}/part7_list">Back to Book List</a>

</body>
</html>