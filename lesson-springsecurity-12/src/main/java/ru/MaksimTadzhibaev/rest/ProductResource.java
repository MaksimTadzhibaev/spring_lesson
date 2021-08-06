package ru.MaksimTadzhibaev.rest;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.MaksimTadzhibaev.controller.ProductListParams;
import ru.MaksimTadzhibaev.persist.Product;
import ru.MaksimTadzhibaev.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
public class ProductResource {

    private final ProductService productService;

    @Autowired
    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @Secured("ROLE_SUPER_ADMIN")
    @GetMapping(path = "/all", produces = "application/json")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @Secured("ROLE_SUPER_ADMIN")
    @GetMapping(path = "/{id}", produces = "application/json")
    public Product findById(@PathVariable("id") Long id) throws NotFoundException {
        return productService.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found"));
    }

    @Secured("ROLE_SUPER_ADMIN")
    @PostMapping(produces = "application/json")
    public Product create(@RequestBody Product product) {
        if (product.getId() != null) {
            throw new BadRequestException("Product Id should be null");
        }
        productService.save(product);
        return product;
    }

    @Secured("ROLE_SUPER_ADMIN")
    @PutMapping(produces = "application/json")
    public void update(@RequestBody Product product) {
        if (product.getId() == null) {
            throw new BadRequestException("Product Id shouldn't be null");
        }
        productService.save(product);
    }

    @Secured("ROLE_SUPER_ADMIN")
    @DeleteMapping(path = "/{id}", produces = "application/json")
    public void delete(@PathVariable("id") Long id) {
        productService.deleteById(id);
    }

    @Secured("ROLE_SUPER_ADMIN")
    @GetMapping(path = "/filter", produces = "application/json")
    public Page<Product> findWithFilter(ProductListParams productListParams) {
        return productService.findWithFilter(productListParams);
    }
}