package learn.checkpoint.data;

import learn.checkpoint.models.UserListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserListItemRepository extends JpaRepository<UserListItem, Integer> {

}

