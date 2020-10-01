package application.model.viewmodel.admin;

import application.model.viewmodel.common.CategoryVM;
import application.model.viewmodel.common.LayoutHeaderAdminVM;

import java.util.List;

public class AdminCategoryVM {
    private LayoutHeaderAdminVM layoutHeaderAdminVM;
    private String keyword;
    private List<CategoryVM> categoryVMList;

    public LayoutHeaderAdminVM getLayoutHeaderAdminVM() {
        return layoutHeaderAdminVM;
    }

    public void setLayoutHeaderAdminVM(LayoutHeaderAdminVM layoutHeaderAdminVM) {
        this.layoutHeaderAdminVM = layoutHeaderAdminVM;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<CategoryVM> getCategoryVMList() {
        return categoryVMList;
    }

    public void setCategoryVMList(List<CategoryVM> categoryVMList) {
        this.categoryVMList = categoryVMList;
    }
}
