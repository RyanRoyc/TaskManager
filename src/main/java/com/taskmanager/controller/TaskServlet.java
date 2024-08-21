package com.taskmanager.controller;

import com.taskmanager.dao.TaskDAO;
import com.taskmanager.model.Task;
import com.taskmanager.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TaskServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");
        TaskDAO taskDAO = new TaskDAO();

        if ("add".equals(action)) {
            Task task = new Task();
            task.setUserId(user.getId());
            task.setTitle(request.getParameter("title"));
            task.setDueDate(java.sql.Date.valueOf(request.getParameter("due_date")));
            task.setCategory(request.getParameter("category"));
            task.setPriority(request.getParameter("priority"));
            task.setStatus("active");

            try {
                taskDAO.addTask(task);
                response.sendRedirect("tasks.jsp");
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                response.sendRedirect("tasks.jsp?error=Task creation failed");
            }
        } else if ("update".equals(action)) {
            int taskId = Integer.parseInt(request.getParameter("task_id"));
            Task task = new Task();
            task.setId(taskId);
            task.setUserId(user.getId());
            task.setTitle(request.getParameter("title"));
            task.setDueDate(java.sql.Date.valueOf(request.getParameter("due_date")));
            task.setCategory(request.getParameter("category"));
            task.setPriority(request.getParameter("priority"));
            task.setStatus(request.getParameter("status"));

            try {
                taskDAO.updateTask(task);
                response.sendRedirect("tasks.jsp");
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                response.sendRedirect("tasks.jsp?error=Task update failed");
            }
        } else if ("delete".equals(action)) {
            int taskId = Integer.parseInt(request.getParameter("task_id"));

            try {
                taskDAO.deleteTask(taskId);
                response.sendRedirect("tasks.jsp");
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                response.sendRedirect("tasks.jsp?error=Task deletion failed");
            }
        } else if ("archive".equals(action)) {
            int taskId = Integer.parseInt(request.getParameter("task_id"));

            try {
                taskDAO.archiveTask(taskId);
                response.sendRedirect("tasks.jsp");
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                response.sendRedirect("tasks.jsp?error=Task archiving failed");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        TaskDAO taskDAO = new TaskDAO();
        try {
            List<Task> tasks = taskDAO.getTasksByUserId(user.getId());
            request.setAttribute("tasks", tasks);
            request.getRequestDispatcher("tasks.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("tasks.jsp?error=Failed to load tasks");
        }
    }
}
