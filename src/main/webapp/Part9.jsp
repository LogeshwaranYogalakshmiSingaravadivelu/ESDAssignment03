<%@ taglib uri="http://csye6220.com/csvtags" prefix="csv" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>CSV File Reader</title>
    </head>
    <body>
        <h1>CSV File Data</h1>

        <!-- Form to submit the CSV file name -->
        <form action="Part9.jsp" method="post">
            <label for="csvFileName">Enter CSV File Name:</label>
            <input type="text" id="csvFileName" name="csvName" placeholder="parking_facilities" required>
            <button type="submit">Submit</button>
        </form>

        <!-- Processing the submitted CSV file name -->
        <%
            String csvFileName = request.getParameter("csvName");
            if (csvFileName != null && !csvFileName.isEmpty()) {
        %>
            <!-- Call the custom tag to read and display the CSV data -->
            <csv:csvReader csvFileName="<%= csvFileName %>"/>
        <% } %>

    </body>
</html>
