package controller;

import entity.Book;
import entity.Customer;
import entity.Loan;
import jdbc.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class LoanController {
    private Connection conn;

    public LoanController() {
        conn = DatabaseManager.getConnection();
    }

    public void closeConnection() {
        DatabaseManager.closeConnection(conn);
    }

    public void addLoan(int book_Id, int customer_Id, String borrowDate, String dueDate, String status) {
        // Add loan
        String sql = "INSERT INTO loans (book_Id, customer_Id, borrowDate, dueDate, status) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, book_Id);
            statement.setInt(2, customer_Id);
            statement.setString(3, borrowDate);
            statement.setString(4, dueDate);
            statement.setString(5, status);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Thêm phiếu mượn thành công");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm phiếu mượn");
            e.printStackTrace();
        }
    }

    public void updateLoan(int loanId, int book_Id, int customer_Id, String borrowDate, String dueDate, String status) {
        // Update loan
        String sql = "UPDATE loans SET bookId = ?, customerId = ?, borrowDate = ?, dueDate = ?, status = ? WHERE id = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, book_Id);
            statement.setInt(2, customer_Id);
            statement.setString(3, borrowDate);
            statement.setString(4, dueDate);
            statement.setString(5, status);
            statement.setInt(6, loanId);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Cập nhật thông tin phiếu mượn thành công");
            } else {
                System.out.println("Không tìm thấy phiếu mượn cần cập nhật");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật thông tin phiếu mượn");
            e.printStackTrace();
        }
    }
    public void deleteLoan(int loanId) {
        // Delete loan
        String sql = "DELETE FROM loans WHERE id = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, loanId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Xóa phiếu mượn thành công");
            } else {
                System.out.println("Không tìm thấy phiếu mượn cần xóa");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa phiếu mượn");
            e.printStackTrace();
        }
    }

    public Loan getLoan(int loanId) {
        // Get loan
        String sql = "SELECT * FROM loans WHERE id = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, loanId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int bookId = resultSet.getInt("book_Id");
                int customerId = resultSet.getInt("customer_Id");
                String borrowDate = resultSet.getString("borrowDate");
                String dueDate = resultSet.getString("dueDate");
                String status = resultSet.getString("status");
                return new Loan(loanId, bookId, customerId, borrowDate, dueDate, status);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy thông tin phiếu mượn");
            e.printStackTrace();
        }
        return null;
    }

public List<Loan> getAllLoans() {
    List<Loan> loans = new ArrayList<>();
    String sql = "SELECT * FROM loans";

    try (PreparedStatement statement = conn.prepareStatement(sql);
         ResultSet resultSet = statement.executeQuery()) {

        while (resultSet.next()) {
            int loanId = resultSet.getInt("id");
            int bookId = resultSet.getInt("book_Id");
            int customerId = resultSet.getInt("customer_Id");
            String borrowDate = resultSet.getString("borrowDate");
            String dueDate = resultSet.getString("dueDate");
            String status = resultSet.getString("status");

            Loan newLoan = new Loan(loanId, bookId, customerId, borrowDate, dueDate, status);
            loans.add(newLoan);
        }


        Map<Integer, Customer> loanCustomers = new HashMap<>(); // Cover list sang map
        for (Loan loan : loans) {
            Customer customer = new CustomerController().getCustomer(loan.getCustomer_Id());
            loanCustomers.put(loan.getId(), customer);
        }

        Map<Integer, Book> loansMap = new HashMap<>();
        for (Loan loan : loans) {
            Book book = new BookController().getBook(loan.getBook_Id());
            loansMap.put(loan.getId(), book);

        }

        // In thông tin về mượn sách
        System.out.format("%5s %20s %20s %15s %15s %15s\n", "Loan ID", "Book Title", "Customer Name", "Borrow Date", "Due Date", "Status");
        for (Loan loan : loans) {
            Customer customer = loanCustomers.get(loan.getId());
            Book book = loansMap.get(loan.getId());
            System.out.format("%5s %20s %20s %15s %15s %15s\n", loan.getId(), book.getTitle(), customer.getName() ,loan.getBorrowedDate(), loan.getDueDate(), loan.getStatus());
        }

    } catch (SQLException e) {
        System.out.println("Error retrieving loan information");
        e.printStackTrace();
    }

    return loans;
}
    public List<Loan> searchLoanByBookTitle(String bookTitle) {
        List<Loan> result = new ArrayList<>();

        int bookId = getBookIdByTitle(bookTitle);

        if (bookId == -1) {
            System.out.println("Không tìm thấy sách");
            return result;
        }

        String loanQuery = "SELECT * FROM loans WHERE book_Id = ?";
        try (PreparedStatement loanStatement = conn.prepareStatement(loanQuery)) {
            loanStatement.setInt(1, bookId);
            try (ResultSet loanResultSet = loanStatement.executeQuery()) {
                while (loanResultSet.next()) {
                    int loanId = loanResultSet.getInt("id");
                    int customerId = loanResultSet.getInt("customer_Id");
                    String borrowDate = loanResultSet.getString("borrowDate");
                    String dueDate = loanResultSet.getString("dueDate");
                    String status = loanResultSet.getString("status");

                    Loan loan = new Loan(loanId, bookId, customerId, borrowDate, dueDate, status);
                    result.add(loan);
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi tìm kiếm phiếu mượn");
            e.printStackTrace();
        }

        displayLoansWithCustomers(result);

        return result;
    }

    private void displayLoansWithCustomers(List<Loan> loans) {
        Map<Integer, Customer> loanCustomers = new HashMap<>();
        for (Loan loan : loans) {
            Customer customer = new CustomerController().getCustomer(loan.getCustomer_Id());
            loanCustomers.put(loan.getId(), customer);
        }

        System.out.format("%5s %20s %20s %15s %15s %15s\n", "Loan ID", "Customer Name", "Borrow Date", "Due Date", "Status", "Book Title");
        for (Loan loan : loans) {
            Customer customer = loanCustomers.get(loan.getId());
            System.out.format("%5d %20s %20s %15s %15s %15s\n", loan.getId(), customer.getName(), loan.getBorrowedDate(), loan.getDueDate(), loan.getStatus(), getBookTitleById(loan.getBook_Id()));
        }
    }

    private String getBookTitleById(int bookId) {
        String bookTitle = "";
        String bookQuery = "SELECT title FROM books WHERE book_Id = ?";

        try (PreparedStatement bookStatement = conn.prepareStatement(bookQuery)) {
            bookStatement.setInt(1, bookId);
            try (ResultSet bookResultSet = bookStatement.executeQuery()) {
                if (bookResultSet.next()) {
                    bookTitle = bookResultSet.getString("title");
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy tiêu đề sách từ ID sách");
            e.printStackTrace();
        }

        return bookTitle;
    }

    private int getBookIdByTitle(String bookTitle) {
        int bookId = -1;
        String sql = "SELECT book_Id FROM BOOKS WHERE title LIKE ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, "%" + bookTitle + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    bookId = resultSet.getInt("book_Id");
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy ID của sách từ tên sách");
            e.printStackTrace();
        }
        return bookId;
    }




}





