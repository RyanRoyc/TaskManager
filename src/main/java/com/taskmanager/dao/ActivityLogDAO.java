package com.taskmanager.dao;

import com.taskmanager.model.ActivityLog;
import com.taskmanager.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActivityLogDAO {

    public void logAction(ActivityLog activityLog) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO activity_log(user_id, action) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, activityLog.getUserId());
            stmt.setString(2, activityLog.getAction());
            stmt.executeUpdate();
        }
    }

    public List<ActivityLog> getActivityLogByUserId(int userId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM activity_log WHERE user_id = ? ORDER BY timestamp DESC";
        List<ActivityLog> logs = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ActivityLog log = new ActivityLog();
                log.setId(rs.getInt("id"));
                log.setUserId(rs.getInt("user_id"));
                log.setAction(rs.getString("action"));
                log.setTimestamp(rs.getTimestamp("timestamp"));
                logs.add(log);
            }
        }
        return logs;
    }
}
