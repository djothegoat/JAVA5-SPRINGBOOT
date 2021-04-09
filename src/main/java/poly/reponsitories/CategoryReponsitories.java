package poly.reponsitories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import poly.entity.Category;

@Repository
public interface CategoryReponsitories extends CrudRepository<Category, Integer> {
}
