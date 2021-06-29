package ru.MaksimTadzhibaev;

import ru.MaksimTadzhibaev.persist.Product;
import ru.MaksimTadzhibaev.persist.ProductRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class BootstrapListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();

        ProductRepository productRepository = new ProductRepository();
        productRepository.save(new Product(1L,"apricot", 200));
        productRepository.save(new Product(2L,"banana", 60));
        productRepository.save(new Product(3L,"cherry", 350));
        productRepository.save(new Product(4L,"grape", 150));
        productRepository.save(new Product(5L,"lemon", 100));
        productRepository.save(new Product(6L,"pear", 170));
        productRepository.save(new Product(7L,"plum", 400));
        productRepository.save(new Product(8L,"strawberry", 300));
        productRepository.save(new Product(9L,"raspberry", 250));
        productRepository.save(new Product(10L,"watermelon", 700));


        sc.setAttribute("productRepository", productRepository);

    }
}
