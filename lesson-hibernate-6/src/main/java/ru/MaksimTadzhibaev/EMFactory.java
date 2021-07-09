package ru.MaksimTadzhibaev;

import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManagerFactory;

public class EMFactory {
    private EntityManagerFactory emFactory= new Configuration()
            .configure("hibernate.cfg.xml")
            .buildSessionFactory();

    public EntityManagerFactory getEmFactory() {
        return emFactory;
    }
}
