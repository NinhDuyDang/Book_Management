package entity;

public class Customer {
    private int customer_Id;
    private String name;
    private String phone;

    @Override
    public String toString() {
        return "Customer{" +
                "customer_Id=" + customer_Id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public int getCustomer_Id() {
        return customer_Id;
    }

    public void setCustomer_Id(int customer_Id) {
        this.customer_Id = customer_Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Customer(int customer_Id, String name, String phone) {
        this.customer_Id = customer_Id;
        this.name = name;
        this.phone = phone;
    }
}
