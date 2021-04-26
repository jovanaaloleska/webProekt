package vebProektEshop.service.impl;

import vebProektEshop.model.Category;
import vebProektEshop.model.exceptions.CategoryNotFoundException;
import vebProektEshop.model.Manufacturer;
import vebProektEshop.model.Product;
import vebProektEshop.model.exceptions.ManufacturerNotFoundException;
import vebProektEshop.model.exceptions.ProductNotFoundException;
import vebProektEshop.repository.CategoryRepository;
import vebProektEshop.repository.ManufacturerRepository;
import vebProektEshop.repository.ProductRepository;
import vebProektEshop.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              ManufacturerRepository manufacturerRepository,
                              CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return this.productRepository.findById(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return this.productRepository.findByName(name);
    }

    @Override
    @Transactional
    public Optional<Product> save(String name, Double price, Integer quantity, String image, Long categoryId, Long manufacturerId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));

        this.productRepository.deleteByName(name);
        return Optional.of(this.productRepository.save(new Product(name, price, quantity, image, category, manufacturer)));
    }

    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }

    public Optional<Product> edit(Long id, String name, Double price, Integer quantity,String image, Long categoryId, Long manufacturerId) {

        Product product = this.productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setImage(image);

        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        product.setCategory(category);

        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));
        product.setManufacturer(manufacturer);

        return Optional.of(this.productRepository.save(product));
    }

    @Override
    public List<Product> findByCategoryName(String name){
        Category category=this.categoryRepository.findCategoryByName(name);
        return this.productRepository.findAllByCategory(category);
    }

    @Override
    public Product like(Long id) {
        Product product=this.productRepository.findById(id).orElseThrow(()->new ProductNotFoundException(id));
        Integer currentLikes=product.getLikes();
        product.setLikes(currentLikes+1);
        return this.productRepository.save(product);

    }

    @Override
    public List<Product> listProductsByName(String name) {
        String nameLike="%"+name+"%";
        if(name!=null)
        {
            return this.productRepository.findAllByNameLike(nameLike);
        }
        else{
            return this.productRepository.findAll();
        }
    }


}
