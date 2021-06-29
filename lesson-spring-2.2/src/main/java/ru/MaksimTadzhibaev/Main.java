package ru.MaksimTadzhibaev;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ProductRepository productRepository = context.getBean("productRepository", ProductRepository.class);

        productRepository.show();

        Cart cart1 = context.getBean("cart", Cart.class);

        cart1.saveCartId(5L);
        cart1.saveCartId(3L);
        cart1.saveCartId(2L);
        cart1.showCart();
        cart1.deleteCart(3L);
        cart1.showCart();

        Cart cart2 = context.getBean("cart", Cart.class);
        cart2.saveCartId(1L);
        cart2.saveCartId(4L);
        cart2.showCart();
        cart2.deleteCart(4L);
        cart2.showCart();
    }
}
