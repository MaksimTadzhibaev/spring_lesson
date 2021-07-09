package ru.MaksimTadzhibaev;

import ru.MaksimTadzhibaev.entity.Customer;
import ru.MaksimTadzhibaev.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Service {
    private EntityManagerFactory emFactory;

    public Service(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    // метод находит все продукты у покупателя по его id
    public void findAllProducts(Long id) {
        EntityManager em = emFactory.createEntityManager();
        Customer customer = em.createQuery("select c from Customer c join fetch c.product where c.id = :id", Customer.class)
                .setParameter("id", id).getSingleResult();
        System.out.println(customer.getName()+" купил(а):");
        customer.getProduct().forEach(System.out::println);
    }

    // метод находит всех покупателей у продукта по его id
    public void findAllCostumers(Long id) {
        EntityManager em = emFactory.createEntityManager();
        Product product = em.createQuery("select p from Product p join fetch p.customer where p.id = :id", Product.class)
                .setParameter("id", id).getSingleResult();
        System.out.println(product.getTitle()+" был приобретён покупателями:");
        product.getCustomer().forEach(System.out::println);
}
}
