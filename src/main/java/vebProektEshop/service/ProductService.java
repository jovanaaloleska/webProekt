package vebProektEshop.service;

import org.springframework.data.domain.Page;
import vebProektEshop.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();
    Optional<Product> findById(Long id);
    Optional<Product> findByName(String name);
    Optional<Product> save(String name, Double price, Integer quantity, String image, Long category, Long manufacturer);
    void deleteById(Long id);

    Optional<Product> edit(Long id, String name, Double price, Integer quantity, String image, Long category, Long manufacturer);

    List<Product> findByCategoryName(String name);
    Product like(Long id);
    List<Product> listProductsByName(String name);
}
