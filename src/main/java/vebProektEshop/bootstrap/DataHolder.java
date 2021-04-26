package vebProektEshop.bootstrap;

import lombok.Getter;
import vebProektEshop.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class DataHolder {

    public static List<Category> categories = new ArrayList<>();
    public static List<User> users = new ArrayList<>();
    public static List<Product> products = new ArrayList<>();
    public static List<Manufacturer> manufacturers = new ArrayList<>();
    public static List<ShoppingCart> shoppingCarts = new ArrayList<>();


}
