package ru.MaksimTadzhibaev;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan("ru.MaksimTadzhibaev")
public class AppConfig {

    @Bean
    public ProductRepository productRepository() {
        return new ProductRepository();
    }

    @Bean
    @Scope("prototype")
    public Cart cart() {
        return new Cart();
    }

}
