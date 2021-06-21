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
        productRepository.save(new Product(1L,"Product1", 100));
        productRepository.save(new Product(2L,"Product2", 200));
        productRepository.save(new Product(3L,"Product3", 300));
        productRepository.save(new Product(4L,"Product4", 400));
        productRepository.save(new Product(5L,"Product5", 500));
        productRepository.save(new Product(6L,"Product6", 600));
        productRepository.save(new Product(7L,"Product7", 700));
        productRepository.save(new Product(8L,"Product8", 800));
        productRepository.save(new Product(9L,"Product9", 900));
        productRepository.save(new Product(10L,"Product10", 1000));


        sc.setAttribute("productRepository", productRepository);

    }
}
