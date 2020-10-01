package application.controller.web;

import application.data.model.*;
import application.data.repository.OrderRepository;
import application.data.service.CategoryService;
import application.data.service.OrderService;
import application.data.service.ProductImageService;
import application.data.service.ProductService;
import application.model.viewmodel.admin.*;
import application.model.viewmodel.common.CategoryVM;
import application.model.viewmodel.common.ChartDataVM;
import application.model.viewmodel.common.ProductImageVM;
import application.model.viewmodel.common.ProductVM;
import application.model.viewmodel.order.OrderDetailVM;
import application.model.viewmodel.order.OrderProductVM;
import application.model.viewmodel.order.OrderVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/admin")
public class AdminController extends BaseController{

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductImageService productImageService;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("")
    public String home(Model model){

        HomeAdminVM vm=new HomeAdminVM();
        vm.setLayoutHeaderAdminVM(this.getLayoutHeaderAdminVM());

        model.addAttribute("vm",vm);

        return "/admin/home";
    }

    @GetMapping("/product")
    public String product(Model model,
                          @Valid @ModelAttribute("productname")ProductVM productName,
                          @RequestParam(name="page" ,required = false,defaultValue = "0") Integer page,
                          @RequestParam(name="size",required = false,defaultValue = "8") Integer size){

        AdminProductVM vm=new AdminProductVM();

        /*
        set list category
        * */
        List<Category> categoryList=categoryService.getListAllCategories();
        List<CategoryVM> categoryVMList=new ArrayList<>();

        for(Category category:categoryList){
            CategoryVM categoryVM=new CategoryVM();
            categoryVM.setId(category.getId());
            categoryVM.setId(category.getId());
            categoryVM.setName(category.getName());
            categoryVMList.add(categoryVM);
        }

        Pageable pageable=new PageRequest(page,size);

        Page<Product> productPage=null;

        if(productName.getName() != null && !productName.getName().isEmpty()){
            productPage=productService.getListProductByCategoryOrProductNameContaining(pageable,null,productName.getName());
        }else{
            productPage=productService.getListProductByCategoryOrProductNameContaining(pageable,null,null);
        }

        List<ProductVM> productVMList = new ArrayList<>();
        for (Product product : productPage.getContent()) {
            ProductVM productVM = new ProductVM();
            if (product.getCategory() == null) {
                productVM.setCategoryName("Unknown");
            } else {
                productVM.setCategoryName(product.getCategory().getName());
            }
            productVM.setId(product.getId());
            productVM.setName(product.getName());
            productVM.setMainImage(product.getMainImage());
            productVM.setPrice(product.getPrice());
            productVM.setShortDesc(product.getShortDesc());
            productVM.setCreatedDate(product.getCreatedDate());

            productVMList.add(productVM);
        }

        vm.setLayoutHeaderAdminVM(this.getLayoutHeaderAdminVM());
        vm.setCategoryVMList(categoryVMList);
        vm.setProductVMList(productVMList);
        if (productVMList.size() == 0) {
            vm.setKeyword("Not found any product");
        }
        
        model.addAttribute("vm", vm);
        model.addAttribute("page", productPage);

        return "/admin/product";
    }

    @GetMapping("/category")
    public String category(Model model,
                           @Valid @ModelAttribute("categoryname") CategoryVM categoryName,
                           @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                           @RequestParam(name = "size", required = false, defaultValue = "8") Integer size
    ) {
        AdminCategoryVM vm = new AdminCategoryVM();

        Pageable pageable = new PageRequest(page, size);

        Page<Category> categoryPage =  null;

        if (categoryName.getName() != null && !categoryName.getName().isEmpty()) {
            categoryPage = categoryService.getListCategoryByCategoryNameContaining(pageable, categoryName.getName().trim());
            vm.setKeyword("Find with key: " + categoryName.getName());
        } else categoryPage = categoryService.getListCategoryByCategoryNameContaining(pageable, null);

        List<CategoryVM> categoryVMList = new ArrayList<>();

        for (Category category : categoryPage.getContent()) {
            CategoryVM categoryVM = new CategoryVM();

            categoryVM.setId(category.getId());
            categoryVM.setName(category.getName());
            categoryVM.setShortDesc(category.getShortDesc());
            categoryVM.setCreatedDate(category.getCreatedDate());

            categoryVMList.add(categoryVM);
        }

        vm.setLayoutHeaderAdminVM(this.getLayoutHeaderAdminVM());
        vm.setCategoryVMList(categoryVMList);
        if (categoryVMList.size() == 0) {
            vm.setKeyword("Not found any category");
        }

        model.addAttribute("vm", vm);
        model.addAttribute("page", categoryPage);

        return "/admin/category";
    }

    @GetMapping("/product-image/{productId}")
    public String product(Model model, @PathVariable int productId) {
        AdminProductImageVM vm = new AdminProductImageVM();

        Product productEntity = productService.findOne(productId);


        List<ProductImageVM> productImageVMS = new ArrayList<>();

        for (ProductImage productImage : productEntity.getProductImageList()) {
            ProductImageVM productImageVM = new ProductImageVM();
            productImageVM.setId(productImage.getId());
            productImageVM.setLink(productImage.getLink());
            productImageVM.setCreatedDate(productImage.getCreatedDate());
            productImageVMS.add(productImageVM);
        }

        vm.setLayoutHeaderAdminVM(this.getLayoutHeaderAdminVM());
        vm.setProductImageVMList(productImageVMS);
        vm.setProductId(productId);


        model.addAttribute("vm", vm);

        return "/admin/product-image";
    }

    @GetMapping("/order")
    public String Order(Model model,
                          @Valid @ModelAttribute("phonenumber") OrderVM phoneNumber,
                          @RequestParam(name="page" ,required = false,defaultValue = "0") Integer page,
                          @RequestParam(name="size",required = false,defaultValue = "10") Integer size){

        AdminOrderVM vm=new AdminOrderVM();

        /*
        set list category
        * */
        List<CategoryVM> categoryVMList=categoryService.getListCategories();

        Pageable pageable=new PageRequest(page,size);

//        Page<OrderVM> orderPage=null;
        Page<Order> orderPage=null;

        if(phoneNumber.getPhoneNumber() != null && !phoneNumber.getPhoneNumber().isEmpty()){
            orderPage=orderRepository.getListOrdersByPhoneNumberContaining(pageable,phoneNumber.getPhoneNumber());
            vm.setKeyword("Find with key: " + phoneNumber.getPhoneNumber());
        }else{
            orderPage=orderRepository.getListOrdersByPhoneNumberContaining(pageable,null);
        }

        List<OrderVM> orderVMS=new ArrayList<>();

        for(Order order:orderPage.getContent()){
            OrderVM orderVM = new OrderVM();
            orderVM.setId(order.getId());
            orderVM.setCustomerName(order.getCustomerName());
            orderVM.setCreatedDate(order.getCreatedDate());
            orderVM.setEmail(order.getEmail());
            orderVM.setPhoneNumber(order.getPhoneNumber());
            orderVM.setStatus(order.getStatus());
            orderVM.setPrice(Double.toString(order.getPrice()));
            orderVM.setAddress(order.getAddress());

            orderVMS.add(orderVM);
        }

        vm.setLayoutHeaderAdminVM(this.getLayoutHeaderAdminVM());
        vm.setOrderVMS(orderVMS);
        if (orderVMS.size() == 0) {
            vm.setKeyword("Not found any order");
        }

        model.addAttribute("vm", vm);
        model.addAttribute("page", orderPage);

        return "/admin/orderTest";
    }

    @GetMapping("/order/{orderId}")
    public String orderDetail(Model model,
                              @PathVariable("orderId")int orderId){

        OrderDetailVM vm = new OrderDetailVM();

        DecimalFormat df = new DecimalFormat("####0.00");

        double totalPrice = 0;

        List<OrderProductVM> orderProductVMS = new ArrayList<>();

        Order orderEntity = orderRepository.findOne(orderId);

        if(orderEntity != null) {
            vm.setOrderStatus(orderEntity.getStatus());
            for(OrderProduct orderProduct : orderEntity.getListProductOrders()) {
                OrderProductVM orderProductVM = new OrderProductVM();

                orderProductVM.setProductId(orderProduct.getProduct().getId());
                orderProductVM.setMainImage(orderProduct.getProduct().getMainImage());
                orderProductVM.setAmount(orderProduct.getAmount());
                orderProductVM.setName(orderProduct.getProduct().getName());

                orderProductVM.setPrice(df.format(orderProduct.getPrice()));

                orderProductVM.setProductPrice(df.format(orderProduct.getProduct().getPrice()));

                totalPrice += orderProduct.getPrice();

                orderProductVMS.add(orderProductVM);
            }
        }

        vm.setLayoutHeaderAdminVM(this.getLayoutHeaderAdminVM());
        vm.setOrderProductVMS(orderProductVMS);
        vm.setTotalPrice(df.format(totalPrice));
        vm.setTotalProduct(orderProductVMS.size());

        vm.setCustomerName(orderEntity.getCustomerName());
        vm.setCreatedDate(orderEntity.getCreatedDate());

        model.addAttribute("vm",vm);

        return "/admin/order-detail";
    }

    @GetMapping("/chart")
    public String chart(Model model) {

        ChartVM vm = new ChartVM();

        vm.setLayoutHeaderAdminVM(this.getLayoutHeaderAdminVM());

        List<ChartDataVM> chartDataVMS = new ArrayList<>();

        List<Category> categories = categoryService.getListAllCategories();

        for(Category category : categories) {
            chartDataVMS.add(new ChartDataVM(category.getName(), (long) category.getListProducts().size()));
        }

//        vm.setChartDataVMS(chartDataVMS);
        vm.setLayoutHeaderAdminVM(this.getLayoutHeaderAdminVM());

        model.addAttribute("vm", vm);

        return "/admin/chart";
    }

    @GetMapping("/blog")
    public String blog(Model model){

        model.addAttribute("vm",this.getLayoutHeaderAdminVM());

        return "/admin/blog";
    }


}
