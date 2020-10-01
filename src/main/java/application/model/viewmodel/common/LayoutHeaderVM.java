package application.model.viewmodel.common;


import java.util.List;

public class LayoutHeaderVM {
    private String urlLogoCompany;
    private List<HeaderMenuVM> headerMenuVMList;

    public List<HeaderMenuVM> getHeaderMenuVMList() {
        return headerMenuVMList;
    }

    public void setHeaderMenuVMList(List<HeaderMenuVM> headerMenuVMList) {
        this.headerMenuVMList = headerMenuVMList;
    }


    public String getUrlLogoCompany() {
        return urlLogoCompany;
    }

    public void setUrlLogoCompany(String urlLogoCompany) {
        this.urlLogoCompany = urlLogoCompany;
    }
}
