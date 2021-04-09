package poly.service;

import poly.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {


    Order save(Order s);

    List<Order> findAll();

    void deleteById(Integer integer);

    Optional<Order> findById(Integer integer);
}
