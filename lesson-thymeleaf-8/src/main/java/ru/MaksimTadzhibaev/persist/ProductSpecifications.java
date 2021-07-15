package ru.MaksimTadzhibaev.persist;

import org.springframework.data.jpa.domain.Specification;

public final class ProductSpecifications {

    public static Specification<Product> productTitlePrefix (String prefix){
        return (root, query, builder) -> builder.like(root.get("title"), prefix + "%");
    }

    public static Specification<Product> minCost (Double minCost){
        return (root, query, builder) -> builder.ge(root.get("cost"), minCost);
    }

    public static Specification<Product> maxCost (Double maxCost){
        return (root, query, builder) -> builder.le(root.get("cost"), maxCost);
    }
}
