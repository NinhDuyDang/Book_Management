import controller.BookController;
import controller.CustomerController;
import controller.EmployeeController;
import controller.LoanController;
import entity.Book;
import entity.Customer;
import entity.Employee;
import entity.Loan;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static BookController bookController = new BookController();
    public static CustomerController customerController = new CustomerController();
    public static EmployeeController employeeController = new EmployeeController();
    public static LoanController loanController = new LoanController();

    public static void main(String[] args) {
        startApplication();
    }

    public static void startApplication() {
        System.out.println("Chào mừng bạn đến với ứng dụng quản lý thuê sách");
        System.out.println("1. Quản lý khách hàng");
        System.out.println("2. Quản lý nhân viên");
        System.out.println("3. Quản lý sách");
        System.out.println("4. Quản lý phiếu mượn");
        System.out.println("5. Thoát chương trình");
        System.out.print("Chọn chức năng bạn muốn sử dụng (1-4): ");
        int choice = Integer.parseInt(sc.nextLine());
        switch (choice) {
            case 1:
                customerManagement();
                break;
            case 2:
                employeeManagement();
                break;
            case 3:
                bookManagement();
                break;
            case 4:
                loanManagement();
                break;
            case 5:
                System.exit(0);
            default:
                System.out.println("Chức năng không tồn tại. Vui lòng chọn lại");
                startApplication();
        }
    }

    public static void customerManagement() {
        System.out.println("1. Thêm khách hàng");
        System.out.println("2. Cập nhật thông tin khách hàng");
        System.out.println("3. Xóa khách hàng");
        System.out.println("4. Hiển thị danh sách khách hàng");
        System.out.println("5. Quay lại");
        System.out.print("Chọn chức năng bạn muốn sử dụng (1-5): ");
        int choice = Integer.parseInt(sc.nextLine());
        switch (choice) {
            case 1:
                System.out.print("Nhập tên khách hàng: ");
                String name = sc.nextLine();
                System.out.print("Nhập số điện thoại: ");
                String phone = sc.nextLine();
                customerController.addCustomer(name, phone);
                customerManagement();
                break;
            case 2:
                System.out.print("Nhập mã khách hàng cần cập nhật: ");
                int customerId = Integer.parseInt(sc.nextLine());
                Customer customer = customerController.getCustomer(customerId);
                if (customer == null) {
                    System.out.println("Khách hàng không tồn tại");
                    customerManagement();
                }
                System.out.print("Nhập tên mới: ");
                String newName = sc.nextLine();
                if (newName.isEmpty()) {
                    newName = customer.getName();
                }
                System.out.print("Nhập số điện thoại mới: ");
                String newPhone = sc.nextLine();
                if (newPhone.isEmpty()) {
                    newPhone = customer.getPhone();
                }
                customerController.updateCustomer(customerId, newName, newPhone);
                customerManagement();
                break;
            case 3:
                System.out.print("Nhập mã khách hàng cần xóa: ");
                int deleteCustomerId = Integer.parseInt(sc.nextLine());
                Customer deleteCustomer = customerController.getCustomer(deleteCustomerId);
                if (deleteCustomer == null) {
                    System.out.println("Khách hàng không tồn tại");
                    customerManagement();
                }
                customerController.deleteCustomer(deleteCustomerId);
                customerManagement();
                break;
            case 4:
                List<Customer> customers = customerController.getAllCustomers();
                for (Customer c : customers) {
                    System.out.println(c);
                }
                customerManagement();
                break;
            case 5:
                startApplication();
                break;
            default:
                System.out.println("Chức năng không tồn tại. Vui lòng chọn lại");
                customerManagement();
        }
    }

    public static void employeeManagement() {
        System.out.println("1. Thêm nhân viên");
        System.out.println("2. Cập nhật thông tin nhân viên");
        System.out.println("3. Xóa nhân viên");
        System.out.println("4. Hiển thị danh sách nhân viên");
        System.out.println("5. Quay lại");
        System.out.print("Chọn chức năng bạn muốn sử dụng (1-5): ");
        int choice = Integer.parseInt(sc.nextLine());
        switch (choice) {
            case 1:
                System.out.print("Nhập tên nhân viên: ");
                String name = sc.nextLine();
                System.out.print("Nhập tuổi: ");
                int age = Integer.parseInt(sc.nextLine());
                System.out.print("Nhập ngày bắt đầu làm việc: ");
                String startedDate = sc.nextLine();
                System.out.print("Nhập chức vụ: ");
                String type = sc.nextLine();
                employeeController.addEmployee(name, age, startedDate, type);
                employeeManagement();
                break;
            case 2:
                System.out.print("Nhập mã nhân viên cần cập nhật: ");
                int employeeId = Integer.parseInt(sc.nextLine());
                Employee employee = employeeController.getEmployee(employeeId);
                if (employee == null) {
                    System.out.println("Nhân viên không tồn tại");
                    employeeManagement();
                }
                System.out.print("Nhập tên mới: ");
                String newName = sc.nextLine();
                if (newName.isEmpty()) {
                    newName = employee.getName();
                }
                System.out.print("Nhập tuổi mới: ");
                String newAge = sc.nextLine();
                if (newAge.isEmpty()) {
                    newAge = String.valueOf(employee.getAge());
                }
                System.out.print("Nhập ngày bắt đầu làm việc mới: ");
                String newStartedDate = sc.nextLine();
                if (newStartedDate.isEmpty()) {
                    newStartedDate = employee.getStarted_date();
                }
                System.out.print("Nhập chức vụ mới: ");
                String newType = sc.nextLine();
                if (newType.isEmpty()) {
                    newType = employee.getType();
                }
                employeeController.updateEmployee(employeeId, newName, Integer.parseInt(newAge), newStartedDate, newType);
                employeeManagement();
                break;
            case 3:
                System.out.print("Nhập mã nhân viên cần xóa: ");
                int deleteEmployeeId = Integer.parseInt(sc.nextLine());
                Employee deleteEmployee = employeeController.getEmployee(deleteEmployeeId);
                if (deleteEmployee == null) {
                    System.out.println("Nhân viên không tồn tại");
                    employeeManagement();
                }
                employeeController.deleteEmployee(deleteEmployeeId);
                employeeManagement();
                break;
            case 4:
                List<Employee> employees = employeeController.getAllEmployees();
                for (Employee e : employees) {
                    System.out.println(e);
                }
                employeeManagement();
                break;
            case 5:
                startApplication();
                break;
            default:
                System.out.println("Chức năng không tồn tại. Vui lòng chọn lại");
                employeeManagement();
        }
    }

    public static void bookManagement() {
        System.out.println("1. Thêm sách");
        System.out.println("2. Cập nhật thông tin sách");
        System.out.println("3. Xóa sách");
        System.out.println("4. Hiển thị danh sách sách");
        System.out.println("5. Quay lại");
        System.out.print("Chọn chức năng bạn muốn sử dụng (1-5): ");
        int choice = Integer.parseInt(sc.nextLine());
        switch (choice) {
            case 1:
                System.out.print("Nhập tên sách: ");
                String title = sc.nextLine();
                System.out.print("Nhập thể loại: ");
                String category = sc.nextLine();
                System.out.print("Nhập tác giả: ");
                String author = sc.nextLine();
                System.out.print("Nhập số lượng: ");
                int quantity = Integer.parseInt(sc.nextLine());
                System.out.print("Nhập ngày xuất bản: ");
                String publishedDate = sc.nextLine();
                bookController.addBook(title, category, author, quantity, publishedDate);
                bookManagement();
                break;
            case 2:
                System.out.print("Nhập mã sách cần cập nhật: ");
                int bookId = Integer.parseInt(sc.nextLine());
                Book book = bookController.getBook(bookId);
                if (book == null) {
                    System.out.println("Sách không tồn tại");
                    bookManagement();
                }
                System.out.print("Nhập tên mới: ");
                String newTitle = sc.nextLine();
                if (newTitle.isEmpty()) {
                    newTitle = book.getTitle();
                }
                System.out.print("Nhập thể loại mới: ");
                String newCategory = sc.nextLine();
                if (newCategory.isEmpty()) {
                    newCategory = book.getCategory();
                }
                System.out.print("Nhập tác giả mới: ");
                String newAuthor = sc.nextLine();
                if (newAuthor.isEmpty()) {
                    newAuthor = book.getAuthor();
                }
                System.out.print("Nhập số lượng mới: ");
                String newQuantity = sc.nextLine();
                if (String.valueOf(newQuantity).isEmpty()) {
                    newQuantity = String.valueOf(book.getQuantity());
                }
                System.out.print("Nhập ngày xuất bản mới: ");
                String newPublishedDate = sc.nextLine();
                if (newPublishedDate.isEmpty()) {
                    newPublishedDate = book.getPublished_date();
                }
                bookController.updateBook(bookId, newTitle, newCategory, newAuthor, Integer.parseInt(newQuantity), newPublishedDate);
                bookManagement();
                break;
            case 3:
                System.out.print("Nhập mã sách cần xóa: ");
                int deleteBookId = Integer.parseInt(sc.nextLine());
                Book deleteBook = bookController.getBook(deleteBookId);
                if (deleteBook == null) {
                    System.out.println("Sách không tồn tại");
                }
                bookController.deleteBook(deleteBookId);
                bookManagement();
                break;
            case 4:
                List<Book> books = bookController.getAllBooks();
                for (Book b : books) {
                    System.out.println(b);
                }
                bookManagement();
                break;
            case 5:
                startApplication();
                break;
        }
    }

    public static void loanManagement() {
        Book book = null;
        Customer customer = null;
        System.out.println("1. Thêm phiếu mượn");
        System.out.println("2. Cập nhật thông tin phiếu mượn");
        System.out.println("3. Xóa phiếu mượn");
        System.out.println("4. Hiển thị danh sách phiếu mượn");
        System.out.println("5. Quay lại");
        System.out.print("6.Tìm kiếm\n");
        System.out.print("Chọn chức năng bạn muốn sử dụng (1-5): ");
        int choice = Integer.parseInt(sc.nextLine());
        switch (choice) {
            case 1:
                System.out.print("Nhập mã sách: ");
                int bookId = Integer.parseInt(sc.nextLine());
                book = new BookController().getBook(bookId);
                if (book == null) {
                    System.out.println("Sách không tồn tại");
                    loanManagement();
                }
                System.out.print("Nhập mã khách hàng: ");
                int customerId = Integer.parseInt(sc.nextLine());
                customer = new CustomerController().getCustomer(customerId);
                if (customer == null) {
                    System.out.println("Khách hàng không tồn tại");
                    loanManagement();
                }
                System.out.print("Nhập ngày mượn: ");
                String borrowDate = sc.nextLine();
                System.out.print("Nhập ngày hết hạn: ");
                String dueDate = sc.nextLine();
                System.out.print("Nhập trạng thái: ");
                String status = sc.nextLine();
                loanController.addLoan(bookId, customerId, borrowDate, dueDate, status);
                bookController.updateBook(bookId, book.getTitle(), book.getCategory(), book.getAuthor(), book.getQuantity() - 1, book.getPublished_date());
                loanManagement();
                break;
            case 2:
                System.out.print("Nhập mã phiếu mượn cần cập nhật: ");
                int loanId = Integer.parseInt(sc.nextLine());
                System.out.print("Nhập mã sách mới: ");
                String newBookId = sc.nextLine();
                if (newBookId.isEmpty()){
                    newBookId = String.valueOf(loanController.getLoan(loanId).getBook_Id());
                }
                book = new BookController().getBook(Integer.parseInt(newBookId));
                if (book == null || book.getQuantity() <= 0){
                    System.out.println("Sách không tồn tại hoặc đã hết hàng");
                    loanManagement();
                }
                System.out.print("Nhập mã khách hàng mới: ");
                String newCustomerId = sc.nextLine();
                if (newCustomerId.isEmpty()){
                    newCustomerId = String.valueOf(loanController.getLoan(loanId).getCustomer_Id());
                }
                customer = new CustomerController().getCustomer(Integer.parseInt(newCustomerId));
                if (customer == null) {
                    System.out.println("Khách hàng không tồn tại");
                    loanManagement();
                }
                System.out.print("Nhập ngày mượn mới: ");
                String newBorrowDate = sc.nextLine();
                if (newBorrowDate.isEmpty()) {
                    newBorrowDate = loanController.getLoan(loanId).getBorrowedDate();
                }
                System.out.print("Nhập ngày hết hạn mới: ");
                String newDueDate = sc.nextLine();
                if (newDueDate.isEmpty()){
                    newDueDate = loanController.getLoan(loanId).getDueDate();
                }
                System.out.print("Nhập trạng thái mới: ");
                String newStatus = sc.nextLine();
                if (newStatus.isEmpty()){
                    newStatus = loanController.getLoan(loanId).getStatus();
                }
                loanController.updateLoan(loanId, Integer.parseInt(newBookId), Integer.parseInt(newCustomerId), newBorrowDate, newDueDate, newStatus);
                loanManagement();
                break;
            case 3:
                System.out.print("Nhập mã phiếu mượn cần xóa: ");
                int deleteLoanId = Integer.parseInt(sc.nextLine());
                Loan deleteLoan = loanController.getLoan(deleteLoanId);
                if (deleteLoan == null) {
                    System.out.println("Phiếu mượn không tồn tại");
                    loanManagement();
                }
                loanController.deleteLoan(deleteLoanId);
                loanManagement();
                break;
            case 4:
                List<Loan> loans = loanController.getAllLoans();


//                }
                loanManagement();
                break;
            case 5:
                startApplication();
                break;
            case 6:
                System.out.print("Nhập tên sách: ");
                String searchBookTitle = sc.nextLine().trim();
                List<Loan> titleLoans = loanController.searchLoanByBookTitle(searchBookTitle);
                for (Loan l : titleLoans) {
                }

            default:
                System.out.println(" Vui lòng chọn lại");
                loanManagement();
        }
    }
}
