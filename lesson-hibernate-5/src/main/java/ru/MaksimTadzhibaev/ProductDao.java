package ru.MaksimTadzhibaev;

import org.hibernate.cfg.Configuration;
import ru.MaksimTadzhibaev.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ProductDao {
    EntityManagerFactory emFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    //       CREATE (INSERT (внесение данных в таблицу))
    public void insert (Product product){
        EntityManager em = emFactory.createEntityManager();

        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
        em.close();
    }

    //        READ (SELECT BY ID (получение данных по id)
    public Product findById (Long id){
        EntityManager em = emFactory.createEntityManager();
        Product product = em.find(Product.class, id);
        em.close();

        return product;

    };

    //        READ (SELECT ALL (плучение всех данных таблицы)
    List <Product> findAll(){
        EntityManager em = emFactory.createEntityManager();
        List <Product> allProducts = em.createQuery("select p from Product p", Product.class).getResultList();
        em.close();

        return allProducts;
    }

    //        UPDATE (обновление данных в существующим поле или создание нового при отсутствии данных)
    Product saveOrUpdate (Product product) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(product);
        em.getTransaction().commit();
        em.close();

        return product;
    }

    //        DELETE (удаление данных по id)
    public void deleteById (Long id) {
        EntityManager em = emFactory.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.getReference(Product.class, id));
        em.getTransaction().commit();
        em.close();
    }
}
