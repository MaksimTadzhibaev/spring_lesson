package ru.MaksimTadzhibaev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class ProductRepository {

    // добавление продуктов при старте
    @Autowired
    public ProductRepository() {
        save(new Product(1L, "apricot", 200D));
        save(new Product(2L, "banana", 60D));
        save(new Product(3L, "cherry", 350D));
        save(new Product(4L, "grape", 150D));
        save(new Product(5L, "lemon", 100D));
    }

    private final Map<Long, Product> productMap = new ConcurrentHashMap<>();

    private final AtomicLong identity = new AtomicLong(0);

    // получение всего списка продуктов
    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }

    // получение продукта по id
    public Product findId(long id) {
        for (Product product : findAll()) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    // добавление продукта в список
    public void save(Product product) {
        if (product.getId() == null) {
            long id = identity.incrementAndGet();
            product.setId(id);
        }
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
        for (Product product: cartProduct) {
            System.out.println("id product - " + product.getId() +
                    ", name product - "+ product.getName()+
                    ", cost product - " + product.getCost());
        }
        System.out.println("________________");
    }
}
