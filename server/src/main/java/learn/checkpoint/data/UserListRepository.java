package learn.checkpoint.data;

import learn.checkpoint.models.UserList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserListRepository extends JpaRepository<UserList, Integer> {

    List<UserList> findByUserId(int id);

}
