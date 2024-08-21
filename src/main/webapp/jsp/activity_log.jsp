<%@page import="com.taskmanager.model.ActivityLog"%>
<%@page import="java.util.List"%>
<%@page import="jakarta.servlet.http.HttpSession"%>
<%
    HttpSession currentSession = request.getSession(false);
    if (currentSession == null || currentSession.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Activity Log</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="log-container">
        <h2>Activity Log</h2>
        <ul>
            <%
                List<ActivityLog> logs = (List<ActivityLog>) request.getAttribute("logs");
                if (logs != null) {
                    for (ActivityLog log : logs) {
            %>
                        <li><%= log.getTimestamp() %> - <%= log.getAction() %></li>
            <%
                    }
                }
            %>
        </ul>
    </div>
</body>
</html>
