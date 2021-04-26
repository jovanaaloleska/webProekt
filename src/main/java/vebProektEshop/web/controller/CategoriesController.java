package vebProektEshop.web.controller;

import vebProektEshop.model.Category;
import vebProektEshop.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoriesController {

    private final CategoryService categoryService;

    public CategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getCategoriesPage(Model model)
    {

        List<Category> categories = this.categoryService.listCategories();
        model.addAttribute("categories", categories);

        model.addAttribute("bodyContent", "categories");
        return "master-template";
    }

    @PostMapping("/add")
    public String addNewCategory(@RequestParam String name,
                                 @RequestParam String description)
    {
        Category category = new Category(name, description);
        this.categoryService.create(name, description);
        return "redirect:/categories";
    }
}
