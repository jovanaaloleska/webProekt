package vebProektEshop.web.controller;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vebProektEshop.model.User;
import vebProektEshop.model.WishList;
import vebProektEshop.service.WishListService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/wish-list")
public class WishListController {
        private final WishListService wishListService;

    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }
    @GetMapping
    public String getWishListPage(@RequestParam(required = false) String error,
                                      HttpServletRequest req,
                                      Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        String username = req.getRemoteUser();
        WishList wishList = this.wishListService.getActiveWishList(username);
        model.addAttribute("products", this.wishListService.listAllProductsInWishList(wishList.getId()));
        model.addAttribute("bodyContent", "wish-list");
        return "master-template";
    }

    @PostMapping("/add-product/{id}")
    public String addProductToWishList(@PathVariable Long id, HttpServletRequest req, Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            this.wishListService.addProductToWishList(user.getUsername(), id);
            return "redirect:/wish-list";
        } catch (RuntimeException exception) {
            return "redirect:/wish-list?error=" + exception.getMessage();
        }
    }

}
