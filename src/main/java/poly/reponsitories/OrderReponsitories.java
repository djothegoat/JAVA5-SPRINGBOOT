package poly.reponsitories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import poly.entity.Order;

@Repository
public interface OrderReponsitories extends CrudRepository<Order, Integer> {
}
