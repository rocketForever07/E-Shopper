package application.model.viewmodel.home;

import application.model.viewmodel.common.CategoryVM;
import application.model.viewmodel.common.LayoutHeaderVM;
import application.model.viewmodel.common.ProductVM;

import java.util.List;

public class HomeLandingVM {

    private LayoutHeaderVM layoutHeaderVM;
    private List<BannerVM> listBanners;
    private List<ProductVM> productVMList;
    private List<CategoryVM> categoryVMList;
    private List<ProductVM> listNewProducts;
    private List<ProductVM> listPopularProducts;

    public List<ProductVM> getListNewProducts() {
        return listNewProducts;
    }

    public void setListNewProducts(List<ProductVM> listNewProducts) {
        this.listNewProducts = listNewProducts;
    }

    public List<ProductVM> getListPopularProducts() {
        return listPopularProducts;
    }

    public void setListPopularProducts(List<ProductVM> listPopularProducts) {
        this.listPopularProducts = listPopularProducts;
    }

    private String keyword;

    public LayoutHeaderVM getLayoutHeaderVM() {
        return layoutHeaderVM;
    }

    public void setLayoutHeaderVM(LayoutHeaderVM layoutHeaderVM) {
        this.layoutHeaderVM = layoutHeaderVM;
    }

    public List<BannerVM> getListBanners() {
        return listBanners;
    }

    public void setListBanners(List<BannerVM> listBanners) {
        this.listBanners = listBanners;
    }

    public List<ProductVM> getProductVMList() {
        return productVMList;
    }

    public void setProductVMList(List<ProductVM> productVMList) {
        this.productVMList = productVMList;
    }

    public List<CategoryVM> getCategoryVMList() {
        return categoryVMList;
    }

    public void setCategoryVMList(List<CategoryVM> categoryVMList) {
        this.categoryVMList = categoryVMList;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
