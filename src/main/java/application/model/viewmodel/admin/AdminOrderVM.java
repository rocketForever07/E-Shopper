package application.model.viewmodel.admin;

import application.model.viewmodel.common.LayoutHeaderAdminVM;
import application.model.viewmodel.order.OrderVM;

import java.util.List;

public class AdminOrderVM {
    private LayoutHeaderAdminVM layoutHeaderAdminVM;
    private List<OrderVM> orderVMS;
    private String keyword;

    public LayoutHeaderAdminVM getLayoutHeaderAdminVM() {
        return layoutHeaderAdminVM;
    }

    public void setLayoutHeaderAdminVM(LayoutHeaderAdminVM layoutHeaderAdminVM) {
        this.layoutHeaderAdminVM = layoutHeaderAdminVM;
    }

    public List<OrderVM> getOrderVMS() {
        return orderVMS;
    }

    public void setOrderVMS(List<OrderVM> orderVMS) {
        this.orderVMS = orderVMS;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
