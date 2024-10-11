<%-- 
    Document   : movie-list
    Created on : 11 Oct 2024, 4:14:33?pm
    Author     : yslog
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Movie List</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>

<h2>Movie List</h2>

<table border="1">
    <thead>
        <tr>
            <th>Title</th>
            <th>Actor</th>
            <th>Actress</th>
            <th>Genre</th>
            <th>Year</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="movie" items="${listMovies}">
            <tr>
                <td>${movie.title}</td>
                <td>${movie.actor}</td>
                <td>${movie.actress}</td>
                <td>${movie.genre}</td>
                <td>${movie.year}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<h3>Search Movies</h3>
<form action="searchByTitle" method="get">
    <label for="title">Search by Title:</label>
    <input type="text" id="title" name="title" required>
    <button type="submit">Search</button>
</form>

<form action="searchByActor" method="get">
    <label for="actor">Search by Actor:</label>
    <input type="text" id="actor" name="actor" required>
    <button type="submit">Search</button>
</form>

<form action="searchByActress" method="get">
    <label for="actress">Search by Actress:</label>
    <input type="text" id="actress" name="actress" required>
    <button type="submit">Search</button>
</form>

<a href="new">Add New Movie</a>
<a href="list">Back to Home</a>
</body>
</html>

