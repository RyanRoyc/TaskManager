package com.taskmanager.controller;

import com.taskmanager.dao.ActivityLogDAO;
import com.taskmanager.model.ActivityLog;
import com.taskmanager.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ActivityLogServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        ActivityLogDAO activityLogDAO = new ActivityLogDAO();
        try {
            List<ActivityLog> logs = activityLogDAO.getActivityLogByUserId(user.getId());
            request.setAttribute("logs", logs);
            request.getRequestDispatcher("activity_log.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("activity_log.jsp?error=Failed to load activity log");
        }
    }
}
