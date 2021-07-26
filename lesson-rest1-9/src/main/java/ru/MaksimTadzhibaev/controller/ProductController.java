package ru.MaksimTadzhibaev.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.MaksimTadzhibaev.persist.Product;
import ru.MaksimTadzhibaev.persist.ProductRepository;
import ru.MaksimTadzhibaev.persist.ProductSpecifications;
import ru.MaksimTadzhibaev.service.ProductService;
import ru.MaksimTadzhibaev.service.ProductServiceImpl;

import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String listPage(Model model, ProductListParams productListParams) {
        logger.info("Product list page requested");
        model.addAttribute("products", productService.findWithFilter
                (productListParams));
        return "products";
    }

    @GetMapping("/new")
    public String newProductForm(Model model) {
        logger.info("New product page requested");
        model.addAttribute("product", new Product());
        return "product_form";
    }

    @GetMapping("/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model) {
        logger.info("Edit product page requested");
        model.addAttribute("product", productService.findById(id));
        return "product_form";
    }

    @PostMapping
    public String update(Product product) {
        logger.info("Saving product or save product changes");
        productService.save(product);
        return "redirect:/product";
    }

    @GetMapping("/{id}/delete")
    public String deleteProduct(@PathVariable("id") Long id) {
        logger.info("Delete product");
        productService.deleteById(id);
        return "redirect:/product";
    }
}

