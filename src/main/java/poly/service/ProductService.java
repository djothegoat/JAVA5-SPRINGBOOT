package poly.service;

import poly.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {


    Product save(Product s);

    List<Product> findAll();

    void deleteById(Integer integer);

    Optional<Product> findById(Integer integer);

    List<Product> findByName(String name);
}
