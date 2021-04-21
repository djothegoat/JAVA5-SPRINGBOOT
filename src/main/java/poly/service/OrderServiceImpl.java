package poly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.entity.Orders;
import poly.reponsitories.OrderReponsitories;

import java.util.List;
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
    public List<Orders> findByUserId(Integer id){
        return orderReponsitories.findByUserId(id);
    }

    @Override
    public List<Orders> findAll() {
        return (List<Orders>) orderReponsitories.findAll();
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
