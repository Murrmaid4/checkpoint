package learn.checkpoint.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import learn.checkpoint.models.Game;
import learn.checkpoint.models.User;
import learn.checkpoint.models.UserList;
import learn.checkpoint.models.UserListItem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class UserListItemRepositoryTest {

    @PersistenceContext
    private EntityManager entityManager;

    @BeforeEach
    void setup() {
        entityManager.createNativeQuery("CALL set_known_good_state();").executeUpdate();
    }

    @Autowired
    UserListItemRepository userListItemRepository;
    User user = new User (1,"testUser1", "$2y$10$Ww8dXn9Xujg5guCJoTVeS.JuYpj3akufOudfR0XE0U38QU3/tdC3G", "email@email.com", "Test", "User");

    @Autowired
    UserListRepository userListRepository;

    UserList testUserList = new UserList(1, user, "Favorites");

    Game addGame = new Game(5,"Stardew Valley", "PC", 2016, "Simulation", "ConcernedApe", "https://upload.wikimedia.org/wikipedia/en/3/34/Stardew_Valley_logo.png");

    UserListItem testUserListItems = new UserListItem(0, testUserList, addGame);

@Test
void shouldAdd() {
    UserList userList = userListRepository.findById(1)
            .orElseThrow(() -> new RuntimeException("UserList not found"));

    UserListItem userListItem = new UserListItem();
    userListItem.setUserList(userList); // Attach managed entity
    userListItem.setGame(addGame);  // Assume you fetched the game properly

    userListItemRepository.save(userListItem);// Now persist it

    assertNotNull(userListItem);
    assertTrue(userListItem.getId() > 0);

}

@Test
void shouldDelete() {
    UserListItem userListItem = userListItemRepository.findById(1)
            .orElseThrow(() -> new RuntimeException("UserListItems not found"));

    userListItemRepository.delete(userListItem);

    assertNull(userListItemRepository.findById(1).orElse(null));
}


//    need assistance with JPA and testing here - later I'm going to make a route that grabs a list and all the games in said list not sure what method I will need to complete that task


}