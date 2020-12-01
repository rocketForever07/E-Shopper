package application.controller.web;

import application.data.model.Cart;
import application.data.model.CartProduct;
import application.data.service.CartService;
import application.model.viewmodel.cart.CartItemVM;
import application.model.viewmodel.cart.CartVM;
import application.model.viewmodel.common.ProductVM;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/cart")
public class CartController extends BaseController {

    private static final Logger logger = LogManager.getLogger(CartController.class);

    @Autowired
    private CartService cartService;


    @GetMapping("")
    public String cart(Model model,
                       @Valid @ModelAttribute("productname") ProductVM productName,
                       HttpServletResponse response,
                       HttpServletRequest request,
                       final Principal principal) {


        this.checkCookie(response,request,principal);


        CartVM vm = new CartVM();


        int itemAmount = 0;
        double totalPrice = 0;
        List<CartItemVM> cartItemVMS = new ArrayList<>();

        String guid = getGuid(request);

        DecimalFormat df = new DecimalFormat("####0.00");

        try {
            if(guid != null) {
                Cart cartEntity = cartService.findFirstCartByGuid(guid);
                if(cartEntity != null) {
                    itemAmount = cartEntity.getListCartProducts().size();
                    for(CartProduct cartProduct : cartEntity.getListCartProducts()) {
                        CartItemVM cartItemVM = new CartItemVM();
                        cartItemVM.setId(cartProduct.getId());
                        cartItemVM.setProductId(cartProduct.getProduct().getId());
                        cartItemVM.setProductName(cartProduct.getProduct().getName());
                        cartItemVM.setMainImage(cartProduct.getProduct().getMainImage());
                        cartItemVM.setAmount(cartProduct.getAmount());
                        cartItemVM.setDesc(cartProduct.getProduct().getShortDesc());

                        double productPrice=cartProduct.getProduct().getPrice();
                        cartItemVM.setProductPrice(df.format(productPrice));

                        double price = cartProduct.getAmount()*cartProduct.getProduct().getPrice();
                        cartItemVM.setPrice(df.format(price));
                        totalPrice += price;
                        cartItemVMS.add(cartItemVM);
                    }
                }
            }
        } catch (Exception e) {
        }

        vm.setProductAmount(itemAmount);
        vm.setCartItemVMS(cartItemVMS);
        vm.setTotalPrice(df.format(totalPrice));
        vm.setLayoutHeaderVM(this.getLayoutHeaderVM());

        model.addAttribute("vm",vm);

        return "/cart";
    }

    public String getGuid(HttpServletRequest request) {
        Cookie cookie[] = request.getCookies();

        if(cookie!=null) {
            for(Cookie c :cookie) {
                if(c.getName().equals("guid"))  return c.getValue();
            }
        }
        return null;
    }

}
