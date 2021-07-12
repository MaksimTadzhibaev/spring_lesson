package ru.MaksimTadzhibaev;

import ru.MaksimTadzhibaev.entity.Product;

public class Main {
    public static void main(String[] args) {
        ProductDao productDao = new ProductDao();
//        productDao.insert(new Product(null, "pear", 160D));
//        System.out.println(productDao.findById(10L));
//        productDao.deleteById(10L);
//        productDao.saveOrUpdate(new Product(8L, "melon", 550D));
        System.out.println(productDao.findAll());

    }
}
