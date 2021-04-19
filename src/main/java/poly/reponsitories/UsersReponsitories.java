package poly.reponsitories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import poly.entity.Users;


@Repository
public interface UsersReponsitories extends CrudRepository<Users, Integer> {
    @Query("SELECT u from Users u where u.email = ?1 ")
    public Users findByUsername(String email);
}
