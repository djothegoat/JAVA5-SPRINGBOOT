package poly.service;

import poly.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAllProduct();


    Optional<Product> findById(Integer integer);

    void saveProduct(Product product);

    void deleteProduct(Integer id);
}
