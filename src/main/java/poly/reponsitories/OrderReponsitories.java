package poly.reponsitories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import poly.entity.Orders;

@Repository
public interface OrderReponsitories extends CrudRepository<Orders, Integer> {
}
