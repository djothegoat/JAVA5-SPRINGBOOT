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
    ProductReponsitories ProductReponsitories;

    @Override
    public Product save(Product s) {
        return ProductReponsitories.save(s);
    }

    @Override
    public List<Product> findAll() {
        return (List<Product>) ProductReponsitories.findAll();
    }

    @Override
    public void deleteById(Integer integer) {
        ProductReponsitories.deleteById(integer);
    }
    @Override
    public Optional<Product> findById(Integer integer) {
        return ProductReponsitories.findById(integer);
    }
    @Override
    public List<Product> findByName(String name){
        return ProductReponsitories.findByName(name);
    }
}
