package poly.reponsitories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import poly.entity.OrderDetail;

@Repository
public interface OrderDetailReponsitories extends CrudRepository<OrderDetail, Integer> {
}
