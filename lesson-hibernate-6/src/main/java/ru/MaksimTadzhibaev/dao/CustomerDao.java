package ru.MaksimTadzhibaev.dao;

import ru.MaksimTadzhibaev.EMFactory;
import ru.MaksimTadzhibaev.entity.Customer;
import ru.MaksimTadzhibaev.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class CustomerDao {

    private EntityManagerFactory emFactory;

    public CustomerDao(EntityManagerFactory emFactory){
        this.emFactory = emFactory;
    }

    //       CREATE (INSERT (внесение данных в таблицу))
    public void insert (Customer customer){
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
        em.close();
    }

    //        READ (SELECT BY ID (получение данных по id)
    public Customer findById (Long id){
        EntityManager em = emFactory.createEntityManager();
        Customer customer = em.find(Customer.class, id);
        em.close();
        return customer;
    };

    //        READ (SELECT ALL (плучение всех данных таблицы)
    List<Customer> findAll(){
        EntityManager em = emFactory.createEntityManager();
        List <Customer> allCustomers = em.createQuery("select c from Customer c", Customer.class).getResultList();
        em.close();
        return allCustomers;
    }

    //        UPDATE (обновление данных в существующим поле или создание нового при отсутствии данных)
    Customer saveOrUpdate (Customer customer) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(customer);
        em.getTransaction().commit();
        em.close();
        return customer;
    }

    //        DELETE (удаление данных по id)
    public void deleteById (Long id) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.getReference(Customer.class, id));
        em.getTransaction().commit();
        em.close();
    }
}
