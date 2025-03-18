package learn.checkpoint.data;

import learn.checkpoint.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    public User findById(int userId);

    public static User create(User user) {
        return null;
    }
}
