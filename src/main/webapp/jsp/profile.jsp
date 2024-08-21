<%@page import="com.taskmanager.model.User"%>
<%@page import="jakarta.servlet.http.HttpSession"%>
<%
    HttpSession currentSession = request.getSession(false);
    if (currentSession == null || currentSession.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    User user = (User) session.getAttribute("user");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="form-container">
        <form action="ProfileServlet" method="post">
            <h2>Your Profile</h2>
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" value="<%= user.getUsername() %>" required>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="<%= user.getEmail() %>" required>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" value="<%= user.getPassword() %>" required>
            <button type="submit">Update Profile</button>
        </form>
    </div>
</body>
</html>
