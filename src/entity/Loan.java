package entity;

public class Loan {
    private int id;
    private int book_Id;
    private int customer_Id;
    private String borrowedDate;
    private String dueDate;
    private String status;

    public Loan(int loanId) {
    }

    public Loan(String borrowDate) {
    }


    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", book_Id=" + book_Id +
                ", customer_Id=" + customer_Id +
                ", borrowedDate='" + borrowedDate + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBook_Id() {
        return book_Id;
    }

    public void setBook_Id(int book_Id) {
        this.book_Id = book_Id;
    }

    public int getCustomer_Id() {
        return customer_Id;
    }

    public void setCustomer_Id(int customer_Id) {
        this.customer_Id = customer_Id;
    }

    public String getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(String borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Loan(int id, int book_Id, int customer_Id, String borrowedDate, String dueDate, String status) {
        this.id = id;
        this.book_Id = book_Id;
        this.customer_Id = customer_Id;
        this.borrowedDate = borrowedDate;
        this.dueDate = dueDate;
        this.status = status;
    }

    public void add(Loan loan) {
    }
}
