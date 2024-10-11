<%-- 
    Document   : movie-details
    Created on : 11 Oct 2024, 4:15:55?pm
    Author     : yslog
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Movie Details</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>

<h2>Movie Details</h2>

<table>
    <tr>
        <th>Title:</th>
        <td>${movie.title}</td>
    </tr>
    <tr>
        <th>Actor:</th>
        <td>${movie.actor}</td>
    </tr>
    <tr>
        <th>Actress:</th>
        <td>${movie.actress}</td>
    </tr>
    <tr>
        <th>Genre:</th>
        <td>${movie.genre}</td>
    </tr>
    <tr>
        <th>Year:</th>
        <td>${movie.year}</td>
    </tr>
</table>

<a href="list">Back to Movie List</a>

</body>
</html>

