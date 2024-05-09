package entity;

public class Employee {
    private int id;
    private String name;
    private int age;
    private String started_date;
    private String type;

    public Employee() {
    }

    public Employee(int id, String name, int age, String started_date, String type) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.started_date = started_date;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getStarted_date() {
        return started_date;
    }

    public void setStarted_date(String started_date) {
        this.started_date = started_date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", started_date='" + started_date + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
