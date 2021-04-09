package poly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.entity.Users;
import poly.reponsitories.UsersReponsitories;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UsersReponsitories UsersReponsitories;

    @Override
    public Users save(Users s) {
        return UsersReponsitories.save(s);
    }

    @Override
    public List<Users> findAll() {
        return (List<Users>) UsersReponsitories.findAll();
    }

    @Override
    public void deleteById(Integer integer) {
        UsersReponsitories.deleteById(integer);
    }
    @Override
    public Optional<Users> findById(Integer integer) {
        return UsersReponsitories.findById(integer);
    }
}
