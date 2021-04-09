package poly.reponsitories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import poly.entity.Users;

@Repository
public interface UsersReponsitories extends CrudRepository<Users, Integer> {
}
