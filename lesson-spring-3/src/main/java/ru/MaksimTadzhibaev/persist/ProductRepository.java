package ru.MaksimTadzhibaev.persist;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {

    // добавление продуктов при старте
    @PostConstruct
    public void init() {
        this.save(new Product("apricot", 200D));
        this.save(new Product("banana", 60D));
        this.save(new Product("cherry", 350D));
        this.save(new Product("grape", 150D));
        this.save(new Product("lemon", 100D));
    }

    private final Map<Long, Product> productMap = new ConcurrentHashMap<>();

    private final AtomicLong identity = new AtomicLong(0);

    // получение всего списка продуктов
    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }

    // получение продукта по id
    public Product findId(long id) {
        return productMap.get(id);
    }

    // добавление продукта в список
    public void save(Product product) {
        long id = identity.incrementAndGet();
        product.setId(id);
        productMap.put(product.getId(), product);
    }

    // удаление продукта из списка
    public void delete(long id) {
        productMap.remove(id);
    }

    // просмотр списка всех продуктов
    public void show() {
        ArrayList<Product> cartProduct = new ArrayList<>(productMap.values());
        System.out.println("List of products in the repository:");
        for (Product product : cartProduct) {
            System.out.println("id product - " + product.getId() +
                    ", name product - " + product.getName() +
                    ", cost product - " + product.getCost());
        }
        System.out.println("________________");
    }

    // изменение продукта
    public void update(Product product) {
        productMap.put(product.getId(), product);
    }
}
