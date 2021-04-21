package poly.reponsitories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import poly.entity.Orders;

import java.util.List;

@Repository
public interface OrderReponsitories extends CrudRepository<Orders, Integer> {
    @Query("SELECT o from Orders o where o.usersByUserId.id = ?1 ")
     List<Orders> findByUserId(Integer id);

}
