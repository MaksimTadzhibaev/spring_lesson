package ru.MaksimTadzhibaev;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.MaksimTadzhibaev.dao.CustomerDao;
import ru.MaksimTadzhibaev.dao.ProductDao;
import ru.MaksimTadzhibaev.entity.Product;

import javax.persistence.EntityManagerFactory;

@Configuration
public class AppConfig {

    //    бин для EMF
    @Bean
    public EntityManagerFactory emFactory() {
        return new EMFactory().getEmFactory();
    }

    //    бин для управления таблицей покупателей
    @Bean
    public CustomerDao customerDao (EntityManagerFactory emFactory){
        return new CustomerDao(emFactory);
    }

    //    бин для управления таблицей продуктов
    @Bean
    public ProductDao productDao (EntityManagerFactory emFactory){
        return new ProductDao(emFactory);
    }

    //    бин для сервиса получения данных о покупках
    @Bean
    public Service service (EntityManagerFactory emFactory){
        return new Service(emFactory);
    }
}
