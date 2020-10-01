package application.controller.web;

import application.data.model.Item;
import application.data.model.Product;
import application.data.model.ProductImage;
import application.data.model.Size;
import application.data.service.ProductService;
import application.model.viewmodel.common.ProductImageVM;
import application.model.viewmodel.common.ProductVM;
import application.model.viewmodel.productdetail.ProductDetailVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/product")
public class ProductController extends BaseController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{productId}")
    public String productDetail(@PathVariable Integer productId,
                                Model model,
                                @Valid @ModelAttribute("productname") ProductVM productName) {

        ProductDetailVM vm = new ProductDetailVM();

        Product productEntity = productService.findOne(productId);
        ProductVM productVM = new ProductVM();

        if(productEntity != null) {
            productVM.setId(productEntity.getId());
            productVM.setName(productEntity.getName());
            productVM.setShortDesc(productEntity.getShortDesc());
            productVM.setMainImage(productEntity.getMainImage());
            productVM.setPrice(productEntity.getPrice());

            /**
             * set productVms
             */

            List<ProductImageVM> productImageVMList = new ArrayList<>();
            for (ProductImage productImage : productEntity.getProductImageList()) {
                ProductImageVM productImageVM = new ProductImageVM();

                productImageVM.setId(productImage.getId());
                productImageVM.setLink(productImage.getLink());
                productImageVMList.add(productImageVM);
            }
            productVM.setProductImageVMS(productImageVMList);


            List<Size> sizeList=new ArrayList<>();
            try {
                List<Item> itemList = productEntity.getItemLists();
            }catch (Exception e){
                e.getMessage();
            }

            try {
                if (productEntity.getItemLists() != null) {
                    for (Item item : productEntity.getItemLists()) {
                        sizeList.add(item.getSize());
                    }

                }
            }catch (Exception e){
                e.getMessage();
            }
            productVM.setSizeList(sizeList);

        }

        vm.setProductVM(productVM);
        vm.setLayoutHeaderVM(this.getLayoutHeaderVM());


        model.addAttribute("vm",vm);

        return "/product-detail";
    }

}
