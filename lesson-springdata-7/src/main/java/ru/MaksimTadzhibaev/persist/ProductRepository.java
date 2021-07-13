package ru.MaksimTadzhibaev.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByTitleStartsWith(String prefix);
    List<Product> findByCostBetween (Double min, Double max);
    List<Product> findByCostLessThanEqual(Double min);
    List<Product> findByCostGreaterThanEqual(Double max);
    List<Product> findByTitleStartsWithAndCostBetween(String prefix, Double min, Double max);
    List<Product> findByTitleStartsWithAndCostLessThanEqual(String prefix, Double min);
    List<Product> findByTitleStartsWithAndCostGreaterThanEqual(String prefix, Double max);

}
