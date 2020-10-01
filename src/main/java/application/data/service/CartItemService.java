package application.data.service;

import application.data.model.CartItem;
import application.data.model.CartProduct;
import application.data.repository.CartItemRepository;
import application.data.repository.CartProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CartItemService {


    @Autowired
    private CartProductRepository cartProductRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    public void addNewCartItem(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

    public boolean updateCartItem(CartItem cartItem) {
        try {
            cartItemRepository.save(cartItem);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public CartItem findOne(int cartItemId) {
        return cartItemRepository.findOne(cartItemId);
    }

    public boolean deleteCartItem(int cartItemId) {
        try {
            cartItemRepository.delete(cartItemId);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    @Transactional
    public boolean deleteListCartItems(List<CartItem> cartItems) {
        try {
            cartItemRepository.delete(cartItems);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public CartItem findFirstCartItemByCartIdAndItemId(int cartId, int itemId) {
        try {
            return cartItemRepository.findFirstCartProductByCartIdAndProductId(cartId,itemId);
        }catch (Exception e) {
        }
        return null;
    }
}
