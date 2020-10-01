package application.model.viewmodel.home;

import application.model.viewmodel.common.LayoutHeaderVM;
import application.model.viewmodel.common.ProductVM;

import java.util.List;

public class ShopLandingVM {

    private LayoutHeaderVM layoutHeaderVM;
    private List<BannerVM> listBanners;
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


}
