package learn.checkpoint.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import learn.checkpoint.models.Game;
import learn.checkpoint.models.GameLog;
import learn.checkpoint.models.GameStatus;
import learn.checkpoint.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // If using MySQL
@Transactional
class GameLogRepositoryTest {


    @PersistenceContext
    private EntityManager entityManager;

    @BeforeEach
    void setup() {
        entityManager.createNativeQuery("CALL set_known_good_state();").executeUpdate();
    }

    @Autowired
    GameLogRepository gameLogRepository;

    //make a user then put that user in gamelog and test it
//
    User user = new User (1,"testUser1", "$2y$10$Ww8dXn9Xujg5guCJoTVeS.JuYpj3akufOudfR0XE0U38QU3/tdC3G", "email@email.com", "Test", "User");
    Game testGame = new Game(1, "Super Mario Bros.", "NES", 1985, "Platformer", "Nintendo", "https://upload.wikimedia.org/wikipedia/en/0/03/Super_Mario_Bros._box.png");
//
    GameLog testGameLog = new GameLog(1,testGame,user , GameStatus.PLAYING, "world1-2", LocalDateTime.now());


    @Test
    void findById() {
        Optional<GameLog> actual = gameLogRepository.findById(1);

        assertTrue(actual.isPresent());

    }
    @Test
    @DisplayName("Test findById() - should return empty Optional if not found")
    public void testFindById_NotFound() {
        // Act: Try to find a non-existing GameLog
        Optional<GameLog> foundGameLog = gameLogRepository.findById(999);

        // Assert: It should return empty
        assertFalse(foundGameLog.isPresent(), "GameLog should not be found");
    }

    @Test
    void findByUserId() {
        List<GameLog> actual = gameLogRepository.findByUserId(1);


    }

    @Test
    void create() {
        GameLog gameLog = new GameLog(2,testGame,user , GameStatus.PLAYING, "world1-2", LocalDateTime.now());
        gameLogRepository.save(gameLog);
        assertEquals(2, gameLog.getId());
    }

    @Test
    void update() {
        GameLog gameLog = new GameLog(1,testGame,user , GameStatus.PLAYING, "world1-2", LocalDateTime.now());
        gameLogRepository.save(gameLog);
        gameLog.setStatus(GameStatus.COMPLETED);
        gameLogRepository.save(gameLog);
        GameLog actual = gameLogRepository.findById(1).get();
        assertEquals(GameStatus.COMPLETED, actual.getStatus());
    }

    @Test
    void delete() {
        gameLogRepository.deleteById(1);
        assertEquals(0, gameLogRepository.findAll().size());
    }
}