package application.model.viewmodel.productdetail;

import application.model.viewmodel.common.LayoutHeaderVM;
import application.model.viewmodel.common.ProductVM;

public class ProductDetailVM {
    private ProductVM productVM;
    private LayoutHeaderVM layoutHeaderVM;

    public ProductDetailVM(){}

    public ProductDetailVM(ProductVM productVM, LayoutHeaderVM layoutHeaderVM) {
        this.productVM = productVM;
        this.layoutHeaderVM = layoutHeaderVM;
    }

    public ProductVM getProductVM() {
        return productVM;
    }

    public void setProductVM(ProductVM productVM) {
        this.productVM = productVM;
    }

    public LayoutHeaderVM getLayoutHeaderVM() {
        return layoutHeaderVM;
    }

    public void setLayoutHeaderVM(LayoutHeaderVM layoutHeaderVM) {
        this.layoutHeaderVM = layoutHeaderVM;
    }
}
