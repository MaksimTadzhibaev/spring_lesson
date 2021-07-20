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
                           @RequestParam("maxCost") Optional<Double> maxCost,
                           @RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size,
                           @RequestParam("sorting") Optional<String> sorting) {
        logger.info("Product list page requested");

        Specification<Product> prodSpec = Specification.where(null);
        if (productTitleFilter.isPresent() && !productTitleFilter.get().isBlank()) {
            prodSpec = prodSpec.and(ProductSpecifications.productTitlePrefix(productTitleFilter.get()));
        }
        if (minCost.isPresent()) {
            prodSpec = prodSpec.and(ProductSpecifications.minCost(minCost.get()));
        }
        if (maxCost.isPresent()) {
            prodSpec = prodSpec.and(ProductSpecifications.maxCost(maxCost.get()));
        }
        if (sorting.isPresent() && !sorting.get().isBlank()) {
            model.addAttribute("products", productRepository.findAll
                    (prodSpec, PageRequest.of(page.orElse(1) - 1, size.orElse(3), Sort.by(sorting.get()))));
        } else {
            model.addAttribute("products", productRepository.findAll
                    (prodSpec, PageRequest.of(page.orElse(1) - 1, size.orElse(3))));
        }
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

