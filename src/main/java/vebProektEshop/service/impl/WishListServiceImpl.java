package vebProektEshop.service.impl;


import org.springframework.stereotype.Service;
import vebProektEshop.model.Product;
import vebProektEshop.model.User;
import vebProektEshop.model.WishList;
import vebProektEshop.model.enumerations.WishListStatus;
import vebProektEshop.model.exceptions.*;
import vebProektEshop.repository.UserRepository;
import vebProektEshop.repository.WishListRepository;
import vebProektEshop.service.ProductService;
import vebProektEshop.service.WishListService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WishListServiceImpl implements WishListService {

    private final WishListRepository wishListRepository;
    private final UserRepository userRepository;
    private final ProductService productService;

    public WishListServiceImpl(WishListRepository wishListRepository, UserRepository userRepository, ProductService productService) {
        this.wishListRepository = wishListRepository;
        this.userRepository = userRepository;
        this.productService = productService;
    }

    @Override
    public List<Product> listAllProductsInWishList(Long wishListId) {
        if(!this.wishListRepository.findById(wishListId).isPresent())
            throw new WishListNotFoundException(wishListId);
        return this.wishListRepository.findById(wishListId).get().getProducts();
    }

    @Override
    public WishList getActiveWishList(String username) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return this.wishListRepository
                .findByUserAndStatus(user, WishListStatus.CREATED)
                .orElseGet(() -> {
                    WishList list = new WishList(user);
                    return this.wishListRepository.save(list);
                });

    }

    @Override
    public WishList addProductToWishList(String username, Long productId) {
        WishList wishList = this.getActiveWishList(username);
        Product product = this.productService.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        if(wishList.getProducts()
                .stream().filter(i -> i.getId().equals(productId))
                .collect(Collectors.toList()).size() > 0)
            throw new ProductAlreadyInWishListException(productId, username);
        wishList.getProducts().add(product);
        return this.wishListRepository.save(wishList);
    }


}
