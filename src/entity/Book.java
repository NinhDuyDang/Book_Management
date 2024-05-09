package entity;

public class Book {
    private int book_Id;
    private String title;
    private String category;
    private String author;
    private int quantity;
    private String published_date;

    @Override
    public String toString() {
        return "Book{" +
                "book_Id=" + book_Id +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", author='" + author + '\'' +
                ", quantity=" + quantity +
                ", published_date='" + published_date + '\'' +
                '}';
    }

    public int getBook_Id() {
        return book_Id;
    }

    public void setBook_Id(int book_Id) {
        this.book_Id = book_Id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPublished_date() {
        return published_date;
    }

    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }

    public Book(int book_Id, String title, String category, String author, int quantity, String published_date) {
        this.book_Id = book_Id;
        this.title = title;
        this.category = category;
        this.author = author;
        this.quantity = quantity;
        this.published_date = published_date;
    }
}
