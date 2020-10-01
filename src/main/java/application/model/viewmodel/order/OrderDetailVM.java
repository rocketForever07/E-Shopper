package application.model.viewmodel.order;

import application.model.viewmodel.common.LayoutHeaderAdminVM;
import application.model.viewmodel.common.LayoutHeaderVM;

import java.util.Date;
import java.util.List;

public class OrderDetailVM {

    private LayoutHeaderVM layoutHeaderVM;
    private LayoutHeaderAdminVM layoutHeaderAdminVM;

    public LayoutHeaderAdminVM getLayoutHeaderAdminVM() {
        return layoutHeaderAdminVM;
    }

    public void setLayoutHeaderAdminVM(LayoutHeaderAdminVM layoutHeaderAdminVM) {
        this.layoutHeaderAdminVM = layoutHeaderAdminVM;
    }

    private List<OrderProductVM> orderProductVMS;
    private int orderId;
    private String totalPrice;
    private int totalProduct;
    private String customerName;
    private Date createdDate;
    private int orderStatus;

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public LayoutHeaderVM getLayoutHeaderVM() {
        return layoutHeaderVM;
    }

    public void setLayoutHeaderVM(LayoutHeaderVM layoutHeaderVM) {
        this.layoutHeaderVM = layoutHeaderVM;
    }

    public List<OrderProductVM> getOrderProductVMS() {
        return orderProductVMS;
    }

    public void setOrderProductVMS(List<OrderProductVM> orderProductVMS) {
        this.orderProductVMS = orderProductVMS;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalProduct() {
        return totalProduct;
    }

    public void setTotalProduct(int totalProduct) {
        this.totalProduct = totalProduct;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
