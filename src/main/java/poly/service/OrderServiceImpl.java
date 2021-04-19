package poly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.entity.Orders;
import poly.reponsitories.OrderReponsitories;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderReponsitories orderReponsitories;

    @Override
    public void save(Orders user) {
        orderReponsitories.save(user);
    }

    @Override
    public Optional<Orders> findById(Integer integer) {
        return orderReponsitories.findById(integer);
    }

    @Override
    public void deleteById(Integer integer) {
        orderReponsitories.deleteById(integer);
    }
}
