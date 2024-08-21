package com.taskmanager.dao;

import com.taskmanager.model.Task;
import com.taskmanager.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {
    
    public void addTask(Task task) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO tasks(user_id, title, due_date, category, priority, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, task.getUserId());
            stmt.setString(2, task.getTitle());
            stmt.setDate(3, new java.sql.Date(task.getDueDate().getTime()));
            stmt.setString(4, task.getCategory());
            stmt.setString(5, task.getPriority());
            stmt.setString(6, task.getStatus());
            stmt.executeUpdate();
        }
    }

    public List<Task> getTasksByUserId(int userId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM tasks WHERE user_id = ? AND status = 'active'";
        List<Task> tasks = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt("id"));
                task.setUserId(rs.getInt("user_id"));
                task.setTitle(rs.getString("title"));
                task.setDueDate(rs.getDate("due_date"));
                task.setCategory(rs.getString("category"));
                task.setPriority(rs.getString("priority"));
                task.setStatus(rs.getString("status"));
                tasks.add(task);
            }
        }
        return tasks;
    }

    public void updateTask(Task task) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE tasks SET title = ?, due_date = ?, category = ?, priority = ?, status = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, task.getTitle());
            stmt.setDate(2, new java.sql.Date(task.getDueDate().getTime()));
            stmt.setString(3, task.getCategory());
            stmt.setString(4, task.getPriority());
            stmt.setString(5, task.getStatus());
            stmt.setInt(6, task.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteTask(int taskId) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE tasks SET status = 'deleted' WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, taskId);
            stmt.executeUpdate();
        }
    }

    public void archiveTask(int taskId) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE tasks SET status = 'archived' WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, taskId);
            stmt.executeUpdate();
        }
    }

    public List<Task> getArchivedTasksByUserId(int userId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM tasks WHERE user_id = ? AND status = 'archived'";
        List<Task> tasks = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt("id"));
                task.setUserId(rs.getInt("user_id"));
                task.setTitle(rs.getString("title"));
                task.setDueDate(rs.getDate("due_date"));
                task.setCategory(rs.getString("category"));
                task.setPriority(rs.getString("priority"));
                task.setStatus(rs.getString("status"));
                tasks.add(task);
            }
        }
        return tasks;
    }

    public List<Task> getDeletedTasksByUserId(int userId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM tasks WHERE user_id = ? AND status = 'deleted'";
        List<Task> tasks = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt("id"));
                task.setUserId(rs.getInt("user_id"));
                task.setTitle(rs.getString("title"));
                task.setDueDate(rs.getDate("due_date"));
                task.setCategory(rs.getString("category"));
                task.setPriority(rs.getString("priority"));
                task.setStatus(rs.getString("status"));
                tasks.add(task);
            }
        }
        return tasks;
    }
}
