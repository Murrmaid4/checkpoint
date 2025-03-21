package learn.checkpoint.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import learn.checkpoint.models.User;
import learn.checkpoint.models.UserList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class UserListRepositoryTest {
    @PersistenceContext
    private EntityManager entityManager;

    @BeforeEach
    void setup() {
        entityManager.createNativeQuery("CALL set_known_good_state();").executeUpdate();
    }

    @Autowired
    UserListRepository userListRepository;

    User user = new User (1,"testUser1", "$2y$10$Ww8dXn9Xujg5guCJoTVeS.JuYpj3akufOudfR0XE0U38QU3/tdC3G", "email@email.com", "Test", "User");

    UserList testUserList = new UserList(1, user, "Favorites");

    UserList newList = new UserList(0, user, "New List");


    @Test
    void findByUserId() {

        List<UserList> actual = userListRepository.findByUserId(1);

        assertEquals(2, actual.size());
    }

    @Test
    void findByListId() {
        Optional<UserList> actual = userListRepository.findById(1);
        UserList expected = testUserList;

        assertEquals(expected.getId(), actual.get().getId());
        assertNotNull(actual);
    }

    @Test
    void add() {
        UserList actual = userListRepository.save(newList);

        assertEquals(6, actual.getId());
    }

    @Test
    void update() {
        UserList userList = new UserList();
        userList.setId(1);
        userList.setList_name("New List Name");

        userListRepository.save(userList);

        Optional<UserList> actual = userListRepository.findById(1);
        assertEquals("New List Name", actual.get().getList_name());

        
    }

    @Test
    void delete() {
        userListRepository.deleteById(1);
        assertEquals(4, userListRepository.findAll().size());


    }


}