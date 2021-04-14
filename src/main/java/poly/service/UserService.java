package poly.service;

import poly.entity.Users;

import java.util.List;
import java.util.Optional;

public interface UserService {


    Users findByUsername(String email);

    Users save(Users s);

    List<Users> findAll();

    void deleteById(Integer integer);

    Optional<Users> findById(Integer integer);
}
