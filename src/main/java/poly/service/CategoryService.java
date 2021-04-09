package poly.service;

import poly.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {


    Category save(Category s);

    List<Category> findAll();
}
