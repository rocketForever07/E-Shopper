package application.controller.web;

import application.data.model.Category;
import application.data.model.Product;
import application.data.service.CategoryService;
import application.data.service.ProductService;
import application.model.viewmodel.common.CategoryVM;
import application.model.viewmodel.common.ProductVM;
import application.model.viewmodel.home.BannerVM;
import application.model.viewmodel.home.HomeLandingVM;
import application.model.viewmodel.home.ShopLandingVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
public class HomeController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping(value = "")
    public String home(Model model,
                       @Valid @ModelAttribute("productname") ProductVM productName,
                       @RequestParam(name = "categoryId", required = false) Integer categoryId,
                       @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                       @RequestParam(name = "size", required = false, defaultValue = "9") Integer size,
                       @RequestParam(name = "sortByPrice", required = false) String sort,
                       HttpServletResponse response,
                       HttpServletRequest request,
                       final Principal principal) {



        this.checkCookie(response,request,principal);
        HomeLandingVM vm = new HomeLandingVM();

        /**
         * set list bannerVM
         */
        List<BannerVM> listBanners = new ArrayList<>();
        listBanners.add(new BannerVM("", "Text 1","https://forums.oscommerce.com/uploads/monthly_04_2016/post-336856-0-18918000-1459704022.jpg"));
        listBanners.add(new BannerVM("", "Text 2","https://media.doisongvietnam.vn/u/rootimage/editor/2020/01/23/20/33/w825/e11579764812_3938.jpg"));


        /**
         * set list categoryVM
         */
        List<CategoryVM> categoryVMList=categoryService.getListCategories();
//        for(Category category : categoryList) {
//            CategoryVM categoryVM = new CategoryVM();
//            categoryVM.setId(category.getId());
//            categoryVM.setName(category.getName());
//            categoryVMList.add(categoryVM);
//        }

        /*
        * set list product
        * */

        Sort sortable = new Sort(Sort.Direction.ASC,"id");
        if(sort != null) {
            if (sort.equals("ASC")) {
                sortable = new Sort(Sort.Direction.ASC,"price");
            }else {
                sortable = new Sort(Sort.Direction.DESC,"price");
            }
        }

        Pageable pageable = new PageRequest(page, size, sortable);
        Page<Product> productPage;

        if(categoryId != null) {
            productPage = productService.getListProductByCategoryOrProductNameContaining(pageable,categoryId,null);
            Category category = categoryService.findOne(categoryId);
            vm.setKeyword(category.getName());
        } else if (productName.getName() != null && !productName.getName().isEmpty()) {
            productPage = productService.getListProductByCategoryOrProductNameContaining(pageable,null,productName.getName().trim());
            vm.setKeyword("Find with key: " + productName.getName());
        } else {
            productPage = productService.getListProductByCategoryOrProductNameContaining(pageable,null,null);
        }

        List<ProductVM> productVMList = new ArrayList<>();

        for(Product product : productPage.getContent()) {
            ProductVM productVM = new ProductVM();
            productVM.setId(product.getId());
            productVM.setName(product.getName());
            productVM.setMainImage(product.getMainImage());
            productVM.setPrice(product.getPrice());
            productVM.setShortDesc(product.getShortDesc());

            productVMList.add(productVM);
        }

        vm.setLayoutHeaderVM(this.getLayoutHeaderVM());
        vm.setListBanners(listBanners);
        vm.setCategoryVMList(categoryVMList);
        vm.setProductVMList(productVMList);

        if(productVMList.size() == 0){
            vm.setKeyword("Not found any product");
        }

        model.addAttribute("vm",vm);
        model.addAttribute("page",productPage);
        return "home";
    }

    @GetMapping(value = "/shop")
    public String shop(Model model,HttpServletResponse response,
                       HttpServletRequest request,
                       final Principal principal) {



        this.checkCookie(response,request,principal);
        ShopLandingVM vm = new ShopLandingVM();

        /**
         * set list bannerVM
         */
        List<BannerVM> listBanners = new ArrayList<>();
        listBanners.add(new BannerVM("", "Text 1","https://forums.oscommerce.com/uploads/monthly_04_2016/post-336856-0-18918000-1459704022.jpg"));
        listBanners.add(new BannerVM("", "Text 2","https://media.doisongvietnam.vn/u/rootimage/editor/2020/01/23/20/33/w825/e11579764812_3938.jpg"));


        /**
         * set list categoryVM
         */

        List<ProductVM> listNewProduct=new ArrayList<>();

        List<ProductVM> listMostPopular =new ArrayList<>();





        for(Product product : productService.getListNewProduct()) {
            ProductVM productVM = new ProductVM();
            productVM.setId(product.getId());
            productVM.setName(product.getName());
            productVM.setMainImage(product.getMainImage());
            productVM.setPrice(product.getPrice());
            productVM.setShortDesc(product.getShortDesc());

            listNewProduct.add(productVM);
        }

        for(Product product : productService.getListProductMostPopular()) {
            ProductVM productVM = new ProductVM();
            productVM.setId(product.getId());
            productVM.setName(product.getName());
            productVM.setMainImage(product.getMainImage());
            productVM.setPrice(product.getPrice());
            productVM.setShortDesc(product.getShortDesc());

            listMostPopular.add(productVM);
        }

        vm.setLayoutHeaderVM(this.getLayoutHeaderVM());
        vm.setListBanners(listBanners);
        vm.setListNewProducts(listNewProduct);
        vm.setListPopularProducts(listMostPopular);

        model.addAttribute("vm",vm);
        return "shop";
    }




}