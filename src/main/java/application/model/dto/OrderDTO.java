package application.model.dto;

public class OrderDTO {

    private int id;

    private int orderStatus;

    public OrderDTO(int id, int orderStatus) {
        this.id = id;
        this.orderStatus = orderStatus;
    }

    public OrderDTO(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

}
