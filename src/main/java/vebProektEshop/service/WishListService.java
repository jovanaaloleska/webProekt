package vebProektEshop.service;

import vebProektEshop.model.Product;
import vebProektEshop.model.ShoppingCart;
import vebProektEshop.model.WishList;

import java.util.List;

public interface WishListService {
    List<Product> listAllProductsInWishList(Long wishListId);
    WishList addProductToWishList(String username, Long productId);
    WishList getActiveWishList(String username);

}
