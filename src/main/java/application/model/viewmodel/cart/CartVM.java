package application.model.viewmodel.cart;

import application.model.viewmodel.common.LayoutHeaderVM;

import java.util.List;

public class CartVM {

    private int productAmount;
    private List<CartItemVM> cartItemVMS;
    private LayoutHeaderVM layoutHeaderVM;
    private String totalPrice;

    public int getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    public List<CartItemVM> getCartItemVMS() {
        return cartItemVMS;
    }

    public void setCartItemVMS(List<CartItemVM> cartItemVMS) {
        this.cartItemVMS = cartItemVMS;
    }

    public LayoutHeaderVM getLayoutHeaderVM() {
        return layoutHeaderVM;
    }

    public void setLayoutHeaderVM(LayoutHeaderVM layoutHeaderVM) {
        this.layoutHeaderVM = layoutHeaderVM;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
