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
    <title>Tasks</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="tasks-container">
        <h2>Your Tasks</h2>
        <form action="TaskServlet" method="post">
            <input type="hidden" name="action" value="add">
            <input type="text" name="title" placeholder="Task title" required>
            <input type="date" name="due_date" required>
            <select name="category">
                <option value="Work">Work</option>
                <option value="Personal">Personal</option>
            </select>
            <select name="priority">
                <option value="High">High</option>
                <option value="Medium">Medium</option>
                <option value="Low">Low</option>
            </select>
            <button type="submit">Add Task</button>
        </form>
        <ul>
            <%
                List<Task> tasks = (List<Task>) request.getAttribute("tasks");
                if (tasks != null) {
                    for (Task task : tasks) {
            %>
                        <li>
                            <span><%= task.getTitle() %> - <%= task.getDueDate() %></span>
                            <form action="TaskServlet" method="post" class="inline-form">
                                <input type="hidden" name="task_id" value="<%= task.getId() %>">
                                <input type="hidden" name="action" value="archive">
                                <button type="submit">Archive</button>
                            </form>
                            <form action="TaskServlet" method="post" class="inline-form">
                                <input type="hidden" name="task_id" value="<%= task.getId() %>">
                                <input type="hidden" name="action" value="delete">
                                <button type="submit">Delete</button>
                            </form>
                        </li>
            <%
                    }
                }
            %>
        </ul>
    </div>
</body>
</html>
