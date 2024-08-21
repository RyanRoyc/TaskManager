<%@page import="com.taskmanager.model.Task"%>
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
    <title>Archived Tasks</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="tasks-container">
        <h2>Archived Tasks</h2>
        <ul>
            <%
                List<Task> archivedTasks = (List<Task>) request.getAttribute("archivedTasks");
                if (archivedTasks != null) {
                    for (Task task : archivedTasks) {
            %>
                        <li><%= task.getTitle() %> - <%= task.getDueDate() %></li>
            <%
                    }
                }
            %>
        </ul>
    </div>
</body>
</html>
