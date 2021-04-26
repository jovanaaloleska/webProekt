package vebProektEshop.model.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WishListNotFoundException extends RuntimeException{
    public WishListNotFoundException(Long id) {
        super(String.format("Wish List with id: %d was not found", id));
    }
}
