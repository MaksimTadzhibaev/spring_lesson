package ru.MaksimTadzhibaev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


@Component
public class Cart {

    private final Map<Long, Product> cartMap = new ConcurrentHashMap<>();

    private final AtomicLong identity = new AtomicLong(0);

    @Autowired
    private ProductRepository productRepository;

    // добавление продукта
    public void saveCart(Product product) {
        if (product.getId() == null) {
            long id = identity.incrementAndGet();
            product.setId(id);
        }
        cartMap.put(product.getId(), product);
    }

    // добавить продукт по id
    public void saveCartId(long id) {
        saveCart(productRepository.findId(id));
    }

    // удаление продукта по id
    public void deleteCart(long id) {
        cartMap.remove(id);
    }

    // просмотр корзины
    public void showCart() {
        ArrayList<Product> cartProduct = new ArrayList<>(cartMap.values());
        System.out.println("List of products in the cart:");
        for (Product product: cartProduct) {
            System.out.println(("id product - " + product.getId() +
                    ", name product - "+ product.getName()+
                    ", cost product - " + product.getCost()));
        }
        System.out.println("________________");
    }
}
