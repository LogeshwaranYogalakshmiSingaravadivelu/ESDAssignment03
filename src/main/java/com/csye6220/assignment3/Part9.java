package com.csye6220.assignment3;

import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.PageContext;
import java.io.IOException;
import java.sql.*;

public class Part9 extends SimpleTagSupport {

    private String csvFileName; // Name of the CSV file

    // Setter method for the attribute
    public void setCsvFileName(String csvFileName) {
        this.csvFileName = csvFileName;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        
         try {
            Class.forName("org.relique.jdbc.csv.CsvDriver");
        } catch (ClassNotFoundException e) {
            throw new JspException("CSV JDBC Driver not found", e);
        }

        PageContext pageContext = (PageContext) getJspContext();
        String location = pageContext.getServletContext().getRealPath("/csv");
        String url = "jdbc:relique:csv:" + location; 
//        + "?" +
//                "separator=;" + "&" + "fileExtension=.csv";

        try (Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement(); ResultSet results = stmt.executeQuery("SELECT \"Facility Type\", \"Entity Name\", \"Number of Spaces\" FROM " + csvFileName)) {
            out.println("<table border='1'>");
            out.println("<tr><th>Facility Type</th><th>Entity Name</th><th>Number of Spaces</th></tr>");

            while (results.next()) {
                String facilityType = results.getString("Facility Type");
                String entityName = results.getString("Entity Name");
                String numberOfSpaces = results.getString("Number of Spaces");

                out.println("<tr>");
                out.println("<td>" + facilityType + "</td>");
                out.println("<td>" + entityName + "</td>");
                out.println("<td>" + numberOfSpaces + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");

        } catch (SQLException e) {
            throw new JspException("Error accessing CSV file", e);
        }
    }
}
