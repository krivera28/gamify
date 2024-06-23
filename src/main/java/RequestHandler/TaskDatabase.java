package RequestHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Task.Task;

public class TaskDatabase {
    private Connection connection;

    public TaskDatabase(String filePath) {
        try {
            // Use DriverManager to establish a connection to the local SQLite database file
            connection = DriverManager.getConnection("jdbc:sqlite:" + filePath);
            createTablesIfNotExists();
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }

    public void createTablesIfNotExists() {
        String createUserTableSQL = """
                CREATE TABLE IF NOT EXISTS users (
                    id INTEGER PRIMARY KEY,
                    username TEXT UNIQUE NOT NULL,
                    password TEXT NOT NULL
                );""";

        String createTaskTableSQL = """
                CREATE TABLE IF NOT EXISTS tasks (
                    id INTEGER PRIMARY KEY,
                    user_id INTEGER,
                    name TEXT NOT NULL,
                    description TEXT,
                    goal_date TEXT,
                    progress INTEGER,
                    category TEXT,
                    FOREIGN KEY (user_id) REFERENCES users(id)
                );""";

        try (PreparedStatement createUserTableStmt = connection.prepareStatement(createUserTableSQL);
             PreparedStatement createTaskTableStmt = connection.prepareStatement(createTaskTableSQL)) {
            createUserTableStmt.execute();
            createTaskTableStmt.execute();
        } catch (SQLException e) {
            System.out.println("Error creating tables: " + e.getMessage());
        }
    }

    public boolean isValidUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Error checking user validity: " + e.getMessage());
            return false;
        }
    }

    public List<Task> getUserTasks(String username) {
        String sql = "SELECT * FROM tasks WHERE user_id = (SELECT id FROM users WHERE username = ?)";
        ArrayList<Task> tasks = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Task task = new Task(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("goal_date"),
                        rs.getDouble("progress"),
                        rs.getString("category")
                );
                tasks.add(task);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching user tasks: " + e.getMessage());
        }

        return tasks;
    }
    public boolean addUser(String username, String password) {
        String sql = "INSERT INTO users(username, password) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Success adding user");
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error adding user: " + e.getMessage());
            return false;
        }
    }
    private int getUserId(String username, String password) {
        String sql = "SELECT id FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching user ID: " + e.getMessage());
        }
        return -1; // Return -1 if user not found or error occurs
    }
    public boolean addTasksForUser(String username, String password, List<Task> tasks) {
        int userId = getUserId(username,password);
        String sql = "INSERT INTO tasks(user_id, name, description, goal_date, progress, category) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (Task task : tasks) {
                stmt.setInt(1, userId);
                stmt.setString(2, task.getName());
                stmt.setString(3, task.getDescription());
                stmt.setString(4, task.getCompleteGoal());
                stmt.setDouble(5, task.getProgress());
                stmt.setString(6, task.getCategory());
                stmt.addBatch();
            }
            int[] rowsAffected = stmt.executeBatch();
            return rowsAffected.length == tasks.size();
        } catch (SQLException e) {
            System.out.println("Error adding tasks for user: " + e.getMessage());
            return false;
        }
    }
    public boolean removeTask(String username, int taskId) {
        String sql = "DELETE FROM tasks WHERE user_id = (SELECT id FROM users WHERE username = ?) AND id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setInt(2, taskId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error removing task: " + e.getMessage());
            return false;
        }
    }
}
