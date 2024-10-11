<%-- 
    Document   : movie-form
    Created on : 11 Oct 2024, 4:13:45?pm
    Author     : yslog
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Movie Form</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>

<h2>Add New Movie</h2>

<form action="insert" method="post">
    <label for="title">Title:</label>
    <input type="text" id="title" name="title" required><br><br>

    <label for="actor">Actor:</label>
    <input type="text" id="actor" name="actor" required><br><br>

    <label for="actress">Actress:</label>
    <input type="text" id="actress" name="actress" required><br><br>

    <label for="genre">Genre:</label>
    <input type="text" id="genre" name="genre" required><br><br>

    <label for="year">Year:</label>
    <input type="number" id="year" name="year" required><br><br>

    <button type="submit">Add Movie</button>
</form>

<a href="list">Back to Movie List</a>

</body>
</html>

