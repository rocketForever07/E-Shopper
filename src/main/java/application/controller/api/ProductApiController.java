package application.controller.api;

import application.controller.web.BaseController;
import application.data.model.Category;
import application.data.model.Product;
import application.data.repository.ProductRepository;
import application.data.service.CategoryService;
import application.data.service.ProductService;
import application.model.api.BaseApiResult;
import application.model.api.DataApiResult;
import application.model.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(path = "/api/product")
public class ProductApiController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    @PostMapping(value = "/create")
    public BaseApiResult createProduct(@RequestBody ProductDTO dto){
        DataApiResult result = new DataApiResult();

        try {
            Product product = new Product();
            product.setName(dto.getName());
            product.setShortDesc(dto.getShortDesc());
            product.setPrice(dto.getPrice());
            product.setMainImage(dto.getMainImage());
            product.setCategory(categoryService.findOne(dto.getCategoryId()));
            product.setCreatedDate(new Date());
            productService.addNewProduct(product);
            result.setData(product.getId());
            result.setMessage("Save product successfully: " + product.getId());
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @PostMapping("/update/{productId}")
    public BaseApiResult updateProduct(@PathVariable int productId,
                                       @RequestBody ProductDTO dto) {
        BaseApiResult result = new BaseApiResult();

        try {
            Product product = productService.findOne(productId);
            product.setName(dto.getName());
            product.setShortDesc(dto.getShortDesc());
            product.setPrice(dto.getPrice());
            product.setMainImage(dto.getMainImage());
            product.setCategory(categoryService.findOne(dto.getCategoryId()));
            product.setCreatedDate(new Date());
            productService.addNewProduct(product);
            result.setSuccess(true);
            result.setMessage("Update product successfully");
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }


    @GetMapping("/detail/{productId}")
    public DataApiResult detailProduct(@PathVariable int productId){
        DataApiResult result= new DataApiResult();

        try {
            Product productEntity = productService.findOne(productId);
            if(productEntity == null) {
                result.setSuccess(false);
                result.setMessage("Can't find this product");
            } else {
                ProductDTO dto = new ProductDTO();
                dto.setId(productEntity.getId());
                if(productEntity.getCategory() != null) {
                    dto.setCategoryId(productEntity.getCategory().getId());
                }
                dto.setMainImage(productEntity.getMainImage());
                dto.setName(productEntity.getName());
                dto.setPrice(productEntity.getPrice());
                dto.setShortDesc(productEntity.getShortDesc());
                dto.setCreatedDate(new Date());
                result.setSuccess(true);
                result.setData(dto);
                result.setMessage("Success");
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }

    @DeleteMapping("/delete")
    public BaseApiResult deleteAll(){
        BaseApiResult result=new BaseApiResult();

        try{
            productRepository.deleteAll();

            result.setMessage("done");
            result.setSuccess(true);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("false");
        }
        return result;
    }



}
