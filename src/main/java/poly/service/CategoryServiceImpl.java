package poly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.entity.Category;
import poly.reponsitories.CategoryReponsitories;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{
        @Autowired
        CategoryReponsitories categoryReponsitories;

    @Override
    public Category save(Category s) {
        return categoryReponsitories.save(s);
    }
    @Override
    public List<Category> findAll() {
        return (List<Category> ) categoryReponsitories.findAll();
    }
}
