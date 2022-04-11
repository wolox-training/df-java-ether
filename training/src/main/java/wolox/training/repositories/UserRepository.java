package wolox.training.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import wolox.training.models.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    @Autowired
    List<User> findByUsername(String username);
}
