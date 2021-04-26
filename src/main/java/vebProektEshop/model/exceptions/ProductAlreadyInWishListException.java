package vebProektEshop.model.exceptions;

public class ProductAlreadyInWishListException extends RuntimeException{
    public ProductAlreadyInWishListException(Long id, String username) {
        super(String.format("Product with id: %d already exists in wish list for user with username %s", id, username));
    }
}
