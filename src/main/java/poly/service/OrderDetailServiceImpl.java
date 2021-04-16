package poly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.entity.OrderDetail;
import poly.reponsitories.OrderDetailReponsitories;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{
    @Autowired
    OrderDetailReponsitories orderDetailReponsitories;
    @Override
    public OrderDetail  save(OrderDetail s) {
        return orderDetailReponsitories.save(s);
    }
}
