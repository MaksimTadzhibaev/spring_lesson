package ru.MaksimTadzhibaev;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.MaksimTadzhibaev.dao.CustomerDao;
import ru.MaksimTadzhibaev.dao.ProductDao;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        CustomerDao costumerDao = context.getBean("customerDao", CustomerDao.class);

        ProductDao productDao = context.getBean("productDao", ProductDao.class);

        Service service = context.getBean("service", Service.class);

        service.findAllProducts(1L);
        service.findAllCostumers(4L);
    }
}
