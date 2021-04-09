package poly.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.entity.Product;
import poly.reponsitories.ProductReponsitories;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductReponsitories productReponsitories;

    @Override
    public List<Product> findAllProduct() {
        return (List<Product>) productReponsitories.findAll();
    }
    @Override
    public Optional<Product> findById(Integer integer) {
        return productReponsitories.findById(integer);
    }

    @Override
    public void saveProduct(Product product) {
        productReponsitories.save(product);
    }

    @Override
    public void deleteProduct(Integer id) {
        productReponsitories.deleteById(id);

    }


}
