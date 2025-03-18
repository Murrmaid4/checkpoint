package learn.checkpoint.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import learn.checkpoint.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.simple.JdbcClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Use real DB if needed
@Transactional
class UserRepositoryTest {
    @PersistenceContext
    private EntityManager entityManager;

    @BeforeEach
    void setup() {
        entityManager.createNativeQuery("CALL set_known_good_state();").executeUpdate();
    }

    @Autowired
    UserRepository userRepository;

    @Test
    public void testFindAllUsers() {
        User user = new User (4,"testUser4", "ye@haw.com", "$2y$10$Ww8dXn9Xujg5guCJoTVeS.JuYpj3akufOudfR0XE0U38QU3/tdC3G", "Test4", "User4");
        userRepository.save(user);

        assertThat(userRepository.findAll()).hasSize(4);

    }

    @Test
    void findById() {
        User user = userRepository.findById(1);
        assertNotNull(user);
        assertEquals("testUser1", user.getUsername());
    }

    @Test
    void create() {
        User user = new User (4,"testUser4", "ye@haw.com", "$2y$10$Ww8dXn9Xujg5guCJoTVeS.JuYpj3akufOudfR0XE0U38QU3/tdC3G", "Test4", "User4");
        userRepository.save(user);
        assertThat(userRepository.findAll()).hasSize(4);
    }
}