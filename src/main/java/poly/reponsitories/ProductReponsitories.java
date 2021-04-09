package poly.reponsitories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import poly.entity.Product;

@Repository
public interface ProductReponsitories extends CrudRepository<Product, Integer> {
}
