package poly.reponsitories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import poly.entity.Product;

import java.util.List;

@Repository
public interface ProductReponsitories extends CrudRepository<Product, Integer> {

    @Query("SELECT p from  Product p where p.name like %?1%" + "or p.categoryByCategory.name like %?1%")
    public List<Product> findByName(String name);
}
