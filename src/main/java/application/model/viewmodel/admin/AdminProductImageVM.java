package application.model.viewmodel.admin;

import application.model.viewmodel.common.LayoutHeaderAdminVM;
import application.model.viewmodel.common.ProductImageVM;

import java.util.List;

public class AdminProductImageVM {

    private LayoutHeaderAdminVM layoutHeaderAdminVM;
    private List<ProductImageVM> productImageVMList;
    private int productId;

    public LayoutHeaderAdminVM getLayoutHeaderAdminVM() {
        return layoutHeaderAdminVM;
    }

    public void setLayoutHeaderAdminVM(LayoutHeaderAdminVM layoutHeaderAdminVM) {
        this.layoutHeaderAdminVM = layoutHeaderAdminVM;
    }

    public List<ProductImageVM> getProductImageVMList() {
        return productImageVMList;
    }

    public void setProductImageVMList(List<ProductImageVM> productImageVMList) {
        this.productImageVMList = productImageVMList;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
