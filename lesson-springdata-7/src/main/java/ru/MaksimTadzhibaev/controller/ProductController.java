package ru.MaksimTadzhibaev.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.MaksimTadzhibaev.persist.Product;
import ru.MaksimTadzhibaev.persist.ProductRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String listPage(Model model,
                           @RequestParam("productTitleFilter") Optional<String> productTitleFilter,
                           @RequestParam("minCost") Optional<Double> minCost,
                           @RequestParam("maxCost") Optional<Double> maxCost) {
        logger.info("Product list page requested");

        List<Product> products;

        if (productTitleFilter.isPresent() && !minCost.isPresent() && !maxCost.isPresent()) {
            products = productRepository.findByTitleStartsWith(productTitleFilter.get());
        } else if (productTitleFilter.isPresent() && minCost.isPresent() && !maxCost.isPresent()) {
            products = productRepository.findByTitleStartsWithAndCostGreaterThanEqual(productTitleFilter.get(), minCost.get());
        } else if (productTitleFilter.isPresent() && !minCost.isPresent() && maxCost.isPresent()) {
            products = productRepository.findByTitleStartsWithAndCostLessThanEqual(productTitleFilter.get(), maxCost.get());
        } else if (productTitleFilter.isPresent() && minCost.isPresent() && maxCost.isPresent()) {
            products = productRepository.findByTitleStartsWithAndCostBetween(productTitleFilter.get(), minCost.get(), maxCost.get());
        } else {
            products = productRepository.findAll();
        }

        model.addAttribute("products", products);
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
        model.addAttribute("product", productRepository.findById(id));
        return "product_form";
    }

    @PostMapping
    public String update(Product product) {
        logger.info("Saving product or save product changes");
        productRepository.save(product);
        return "redirect:/product";
    }

    @GetMapping("/{id}/delete")
    public String deleteProduct(@PathVariable("id") Long id) {
        logger.info("Delete product");
        productRepository.deleteById(id);
        return "redirect:/product";
    }
}

