package poly.service;

import poly.entity.OrderDetail;

import java.util.List;
import java.util.Optional;

public interface OrderDetailService {


    OrderDetail save(OrderDetail s);

    List<OrderDetail> findAll();

    void deleteById(Integer integer);

    Optional<OrderDetail> findById(Integer integer);
}
