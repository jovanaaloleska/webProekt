package vebProektEshop.web.controller;

import vebProektEshop.model.Category;
import vebProektEshop.model.Manufacturer;
import vebProektEshop.model.Product;
import vebProektEshop.service.CategoryService;
import vebProektEshop.service.ManufacturerService;
import vebProektEshop.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

import static vebProektEshop.bootstrap.DataHolder.categories;
import static vebProektEshop.bootstrap.DataHolder.products;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ManufacturerService manufacturerService;

    public ProductController(ProductService productService,
                             CategoryService categoryService,
                             ManufacturerService manufacturerService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public String getProductPage(@RequestParam(required = false) String error, @RequestParam(required = false) String searchName, Model model) {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Product> products;

        if(searchName==null) {
            products = this.productService.findAll();
            products.sort(Comparator.comparing(Product::getName));
        }
        else{
            products=this.productService.listProductsByName(searchName);
            products.sort(Comparator.comparing(Product::getName));
        }
        List<Category> categories = this.categoryService.listCategories();
        model.addAttribute("products",products);
        model.addAttribute("categories", categories);
        model.addAttribute("bodyContent","products");
        return "master-template";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        this.productService.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping("/edit-form/{id}")
    public String editProductPage(@PathVariable Long id, Model model) {
        if(this.productService.findById(id).isPresent()){
            Product product = this.productService.findById(id).get();
            List<Manufacturer> manufacturers = this.manufacturerService.findAll();
            List<Category> categories = this.categoryService.listCategories();
            model.addAttribute("manufacturers", manufacturers);
            model.addAttribute("categories", categories);
            model.addAttribute("product", product);
            model.addAttribute("bodyContent","add-product");
            return "master-template";
        }
        return "redirect:/products?error=ProductNotFound";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addProductPage(Model model) {
        List<Manufacturer> manufacturers = this.manufacturerService.findAll();
        List<Category> categories = this.categoryService.listCategories();
        model.addAttribute("manufacturers", manufacturers);
        model.addAttribute("categories", categories);
        model.addAttribute("bodyContent","add-product");
        return "master-template";
    }

    @PostMapping("/add")
    public String saveProduct(@RequestParam (required = false) Long id,
                              @RequestParam String name,
                              @RequestParam Double price,
                              @RequestParam Integer quantity,
                              @RequestParam String image,
                              @RequestParam Long category,
                              @RequestParam Long manufacturer
                              ){
        if(id==null)
            this.productService.save(name, price, quantity,image, category, manufacturer);
        else
            this.productService.edit(id,name, price, quantity,image, category, manufacturer);
        //}

        return "redirect:/products";

    }

    @GetMapping("/{name}")
    public String getAllByCategory(@PathVariable String name,Model model){
        List<Product>products=this.productService.findByCategoryName(name);
        List<Manufacturer> manufacturers = this.manufacturerService.findAll();
        List<Category> categories = this.categoryService.listCategories();
        Product product=this.productService.findByCategoryName(name).stream().findAny().get();
        model.addAttribute("manufacturers", manufacturers);
        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        model.addAttribute("product", product);
        if(name.equals("Phones"))
        {
            model.addAttribute("bodyContent", "phones");
        }
        else if(name.equals("TV")) {
            model.addAttribute("bodyContent", "tv");
        }
        else if(name.equals("Computers")) {
            model.addAttribute("bodyContent", "computers");
        }
        else if(name.equals("Accessories")) {
            model.addAttribute("bodyContent", "accessories");
        }
        else if(name.equals("Smart Devices")) {
            model.addAttribute("bodyContent", "smart devices");
        }
        else{
            model.addAttribute("bodyContent", "products");
        }
        return "master-template";

    }

    @PostMapping("/{id}/like")
    public String Showlikes(@PathVariable Long id, Model model)
    {
        this.productService.like(id);
        Product product=this.productService.findById(id).get();
        String name=product.getCategory().getName();
        return "redirect:/products";

    }



}
