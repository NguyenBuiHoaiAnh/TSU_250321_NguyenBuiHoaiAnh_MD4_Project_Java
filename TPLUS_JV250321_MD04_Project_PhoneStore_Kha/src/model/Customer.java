package model;

public class Customer {
    private int id;
    private String name;
    private String phone;
    private String email;
    private String address;

    public Customer() {
    }

    public Customer(int id, String name, String phone, String email, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    // ----------------------- Getter and Setter ------------------------------

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // ----------------------------------------------------------------


    @Override
    public String toString() {
        return String.format("ID: %d, Name: %s, Phone: %s, Email:%s, Address: %s",
                this.id, this.name, this.phone, this.email, this.address);
    }
}
