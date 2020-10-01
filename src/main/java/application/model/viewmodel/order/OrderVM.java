package application.model.viewmodel.order;

import java.util.Date;

public class OrderVM {

    private int id;
    private String customerName;
    private String phoneNumber;
    private String address;
    private String email;
    private Date createdDate;
    private String price;
    private int status;


    public OrderVM(){}

    public OrderVM(int id, String customerName, String phoneNumber, String address, String email, Date createdDate,String price,int status) {
        this.id = id;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.createdDate = createdDate;
        this.price=price;
        this.status=status;
    }

    public OrderVM(int id, String customerName, String phoneNumber, String address, String email, Date createdDate,Double price,int status) {
        this.id = id;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.createdDate = createdDate;
        this.price=price.toString();
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
