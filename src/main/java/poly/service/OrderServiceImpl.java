package poly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.entity.Order;
import poly.reponsitories.OrderReponsitories;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderReponsitories OrderReponsitories;
    @Override
    public Order save(Order order) {
        return  OrderReponsitories.save(order);
    }

    @Override
    public List<Order> findAll() {
        return (List<Order>) OrderReponsitories.findAll();
    }

    @Override
    public void deleteById(Integer integer) {
        OrderReponsitories.deleteById(integer);
    }
    @Override
    public Optional<Order> findById(Integer integer) {
        return OrderReponsitories.findById(integer);
    }
}
