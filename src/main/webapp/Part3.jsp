<%-- 
    Document   : index
    Created on : 9 Oct 2024, 10:04:49 pm
    Author     : yslog
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <sql:setDataSource var="myDataSource" driver="com.mysql.cj.jdbc.Driver"
                           url="jdbc:mysql://localhost:3306/proj?useSSL=false;"
                           user="root" password="password" />
        <c:set var="Date" value="<%=new java.util.Date()%>" />  
        <c:out value="${'Assignment 3 - Part 3'}"/> 
        <c:set var="income" scope="session" value="${4000*4}"/> 

        <%!
            public String printGreeting(String name) {
                return "Welcome! " + name;
            }
        %>
        <%
            String user = request.getParameter("name");
            if (user != null) {
        %>
        <p><%= printGreeting(user)%></p>
        <%
        } else {
        %>
        <p>Please login</p>
        <%
            }
        %>
        <c:if test="${income > 8000}">  
            <p>My income is: <c:out value="${income}"/><p>  
            </c:if>  
            <c:forEach var="i" begin="1" end="5">
            <p>${i}</p>
        </c:forEach>
        <c:set var="String" value="Welcome to javatpoint"/>  

        <c:if test="${fn:contains(String, 'javatpoint')}">  
            <p>Found javatpoint string<p>  
            </c:if>  

            <c:if test="${fn:containsIgnoreCase(String, 'JAVATPOINT')}">  
            <p>Found JAVATPOINT string<p>  
            </c:if>  

        <p>${fn:substring(String, 0, 10)}</p>
        <c:set var="Amount" value="786.970" />  

        <fmt:parseNumber var="j" type="number" value="${Amount}" />  
        <p><i>Amount is:</i>  <c:out value="${j}" /></p>  
        <c:set var="date" value="12-08-2016" />  

        <fmt:parseDate value="${date}" var="parsedDate"  pattern="dd-MM-yyyy" />  
        <p><c:out value="${parsedDate}" /></p> 
        <p>  
            Formatted Date and Time in long style :  
            <fmt:formatDate type="both" dateStyle="long" timeStyle="long"  
                            value="${Date}" />  
        </p>  
        <c:set var="vegetable">  
        <vegetables>  
            <vegetable>  
                <name>onion</name>  
                <price>40</price>  
            </vegetable>    
        </vegetables>  
    </c:set>  
    <x:parse xml="${vegetable}" var="output"/>  
    <b>Name of the vegetable is</b>:  
    <x:out select="$output/vegetables/vegetable[1]/name" /><br>
    <x:set var="fragment" select="$output/vegetables/vegetable[1]/price"/>  
    <b>The price of the vegetable is</b>:  
    <x:out select="$fragment" /> 
    <br><br>
    <x:if select="$output/vegetables/vegetable[1]/price < 100">  
        Vegetables prices are very low.  
    </x:if>
</body>
</html>




