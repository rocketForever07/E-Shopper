package application.controller.api;

import application.data.model.*;
import application.data.service.*;
import application.model.api.BaseApiResult;
import application.model.dto.CartItemDTO;
import application.model.dto.CartProductDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/cart-item")
public class CartItemApiController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartProductService cartProductService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ItemService itemService;



    @PostMapping("/add")
    public BaseApiResult addToCart(@RequestBody CartItemDTO dto) {
        BaseApiResult result = new BaseApiResult();
        String guidTest=dto.getGuid();
        try {
            if(dto.getGuid() != null && dto.getAmount() > 0 && dto.getItemId() > 0) {

                Cart cartEntity = cartService.findFirstCartByGuid(dto.getGuid());
                Item itemEntity = itemService.findOne(dto.getItemId());
                if(cartEntity != null && itemEntity != null)  {
                    CartItem cartItemEntity = cartItemService.findFirstCartItemByCartIdAndItemId(cartEntity.getId(),itemEntity.getId());
                    if(cartItemEntity != null) {
                        cartItemEntity.setAmount(cartItemEntity.getAmount() + dto.getAmount());
                        cartItemService.updateCartItem(cartItemEntity);
                    } else {
                        CartItem cartItem = new CartItem();
                        cartItem.setAmount(dto.getAmount());
                        cartItem.setCart(cartEntity);
                        cartItem.setItem(itemEntity);
                        cartItemService.addNewCartItem(cartItem);
                    }
                    result.setMessage("Add to cart successfully!");
                    result.setSuccess(true);
                    return result;
                }
            }
        } catch (Exception e) {
        }
        result.setMessage("Fail!");
        result.setSuccess(false);
        return result;
    }

    @PostMapping("/update")
    public BaseApiResult updateCartItem(@RequestBody CartItemDTO dto) {
        BaseApiResult result = new BaseApiResult();

        try {
            if(dto.getId()>0 && dto.getAmount() > 0) {
                CartItem cartItemEntity = cartItemService.findOne(dto.getId());

                if(cartItemEntity != null) {
                    cartItemEntity.setAmount(dto.getAmount());
                    cartItemService.updateCartItem(cartItemEntity);
                    result.setMessage("Update Cart Product success !");
                    result.setSuccess(true);
                    return result;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setMessage("Fail!");
        result.setSuccess(false);
        return result;
    }

    @GetMapping("/delete/{cartProductId}")
    public BaseApiResult deleteCartItem(@PathVariable int cartItemId) {
        BaseApiResult result = new BaseApiResult();

        try {
            if(cartItemService.deleteCartItem(cartItemId) == true) {
                result.setMessage("Delete success");
                result.setSuccess(true);
                return result;
            }
        } catch (Exception e) {
        }
        result.setSuccess(false);
        result.setMessage("Fail!");
        return result;
    }

}