package poly.service;


import poly.entity.Orders;

import java.util.List;
import java.util.Optional;

public interface OrderService {


    void save(Orders user);

    Optional<Orders> findById(Integer integer);

    void deleteById(Integer integer);
}
