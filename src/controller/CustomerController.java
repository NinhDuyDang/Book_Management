package controller;

import entity.Customer;
import jdbc.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerController {
    private Connection conn;

    public CustomerController() {
        conn = DatabaseManager.getConnection();
    }

    public void closeConnection() {
        DatabaseManager.closeConnection(conn);
    }

    public void addCustomer(String name, String phone) {
        // Add customer
        String sql = "INSERT INTO customers (name, phone) VALUES (?, ?)";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, phone);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Thêm khách hàng thành công");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm khách hàng");
            e.printStackTrace();
        }
    }

    public void updateCustomer(int customer_Id, String newName, String newPhone) {
        // Update customer
        String sql = "UPDATE customers SET name = ?, phone = ? WHERE customer_id = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, newName);
            statement.setString(2, newPhone);
            statement.setInt(3, customer_Id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Cập nhật thông tin khách hàng thành công");
            } else {
                System.out.println("Không tìm thấy khách hàng cần cập nhật");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật thông tin khách hàng");
            e.printStackTrace();
        }
    }

    public void deleteCustomer(int customer_Id) {
        // Delete customer
        String sql = "DELETE FROM customers WHERE customer_id = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, customer_Id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Xóa khách hàng thành công");
            } else {
                System.out.println("Không tìm thấy khách hàng cần xóa");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa khách hàng");
            e.printStackTrace();
        }
    }

    public Customer getCustomer(int customer_Id) {
        // Get customer
        String sql = "SELECT * FROM customers WHERE customer_Id = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, customer_Id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                return new Customer(customer_Id, name, phone);
            } else {
                System.out.println("Không tìm thấy khách hàng");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy thông tin khách hàng");
            e.printStackTrace();
        }
        return null; // Trả về null nếu không tìm thấy khách hàng
    }

    public List<Customer> getAllCustomers() {
        // Get all customers
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers";
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("customer_Id");
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                Customer newCustomer = new Customer(id, name, phone);
                customers.add(newCustomer);

            }
            // In danh sách phiếu mượn ra console
            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.printf("%5s %10s %10s ", "ID", "NAME", "PHONE");
            System.out.println();
            System.out.println("---------------------------------------------------------------------------------------------");
            for (Customer customer : customers) {
                System.out.format("%7s %14s %7s", customer.getCustomer_Id(),customer.getName(),customer.getPhone());
                System.out.println();
            }
            System.out.println("----------------------------------------------------------------------------------------------");
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy danh sách khách hàng");
            e.printStackTrace();
        }
        return customers;
    }
}
