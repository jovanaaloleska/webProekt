package vebProektEshop.web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vebProektEshop.model.Category;
import vebProektEshop.model.Manufacturer;
import vebProektEshop.service.ManufacturerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/manufacturers")
public class ManufacturersController {

    private final ManufacturerService manufacturerService;

    public ManufacturersController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public String getManufacturersPage(Model model)
    {

        List<Manufacturer> manufacturers = this.manufacturerService.listManufacturers();
        model.addAttribute("manufacturers", manufacturers);

        model.addAttribute("bodyContent", "manufacturers");
        return "master-template";
    }

    @PostMapping("/add")
    public String addNewManufacturer(@RequestParam String name, @RequestParam String address)
    {
        Manufacturer manufacturer = new Manufacturer(name, address);
        this.manufacturerService.create(name, address);
        return "redirect:/manufacturers";
    }
}
