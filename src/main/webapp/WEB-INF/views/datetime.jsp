<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>

<!DOCTYPE html>
<html>
<head>
    <title>Current Date and Time</title>
</head>
<body>
    <h2>Current Date and Time</h2>

    <!-- Scriptlet: Displaying the current date and time -->
    <% 
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = sdf.format(date);
    %>
    <p><b>Using Scriptlet:</b> <%= formattedDate %></p>

    <!-- Expression: Directly displaying the current date and time -->
    <p><b>Using Expression:</b> ${currentDateTime}</p>

    <!-- Declaration: Declaring a variable for current date and time -->
    <%! 
        public String getCurrentDateTime() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        }
    %>
    <p><b>Using Declaration:</b> <%= getCurrentDateTime() %></p>

    <br>
    <form action="/" method="get">
        <input type="submit" value="Go Back to Home">
    </form>
</body>
</html>
