package controller;
import entity.Employee;
import entity.Loan;
import jdbc.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeController {
    private Connection conn;

    public EmployeeController() {
        conn = DatabaseManager.getConnection();
    }

    public void closeConnection() {
        DatabaseManager.closeConnection(conn);
    }

    public void addEmployee(String name, int age, String startedDate, String type) {
        // Add employee
        String sql = "INSERT INTO employees (name, age, startedDate, type) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, age);
            statement.setString(3, startedDate);
            statement.setString(4, type);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Thêm nhân viên thành công");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm nhân viên");
            e.printStackTrace();
        }
    }

    public void updateEmployee(int employeeId, String newName, int newAge, String newStartedDate, String newType) {
        // Update employee
        String sql = "UPDATE employees SET name = ?, age = ?, startedDate = ?, type = ? WHERE id = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, newName);
            statement.setInt(2, newAge);
            statement.setString(3, newStartedDate);
            statement.setString(4, newType);
            statement.setInt(5, employeeId);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Cập nhật thông tin nhân viên thành công");
            } else {
                System.out.println("Không tìm thấy nhân viên cần cập nhật");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật thông tin nhân viên");
            e.printStackTrace();
        }
    }

    public void deleteEmployee(int employeeId) {
        // Delete employee
        String sql = "DELETE FROM employees WHERE id = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, employeeId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Xóa nhân viên thành công");
            } else {
                System.out.println("Không tìm thấy nhân viên cần xóa");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa nhân viên");
            e.printStackTrace();
        }
    }

    public Employee getEmployee(int employeeId) {
        // Get employee
        String sql = "SELECT * FROM employees WHERE id = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, employeeId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String startedDate = resultSet.getString("startedDate");
                String type = resultSet.getString("type");
                return new Employee(employeeId, name, age, startedDate, type);
            } else {
                System.out.println("Không tìm thấy nhân viên");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy thông tin nhân viên");
            e.printStackTrace();
        }
        return null;
    }

    public List<Employee> getAllEmployees() {
        // Get all employees
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String startedDate = resultSet.getString("startedDate");
                String type = resultSet.getString("type");
                Employee newEmployee = new Employee(id,name,age,startedDate, type);
                employees.add(newEmployee);
            }

            // In danh sách phiếu mượn ra console
            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.printf("%5s %10s %10s %8s %20s ", "ID", "NAME", "AGE", "STARTED_DATE", "TYPE");
            System.out.println();
            System.out.println("---------------------------------------------------------------------------------------------");
            for (Employee employee : employees) {
                System.out.format("%7s %14s %7s %10s %25s", employee.getId(), employee.getName(), employee.getAge(),employee.getStarted_date(),employee.getType());
                System.out.println();
            }
            System.out.println("----------------------------------------------------------------------------------------------");

        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy thông tin nhân viên");
            e.printStackTrace();
        }
        return employees;
    }
}
