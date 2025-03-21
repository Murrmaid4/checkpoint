package learn.checkpoint.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import learn.checkpoint.models.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class GameRepositoryTest {


    @PersistenceContext
    private EntityManager entityManager;

    @BeforeEach
    void setup() {
        entityManager.createNativeQuery("CALL set_known_good_state();").executeUpdate();
    }

    @Autowired
    GameRepository gameRepository;

    Game testGame = new Game(1, "Super Mario Bros.", "NES", 1985, "Platformer", "Nintendo", "https://upload.wikimedia.org/wikipedia/en/0/03/Super_Mario_Bros._box.png");

    @Test
    void testFindAllGames() {
        assertThat(gameRepository.findAll()).hasSize(10);
    }


    @Test
    void findById() {
        Game actual = gameRepository.findById(1);
        Game expected = testGame;
        assertNotNull(actual);
        assertEquals("Super Mario Bros.", actual.getTitle());
        ;
    }

    @Test
    void findByTitle() {
        Game actual = gameRepository.findByTitle("Super Mario Bros.");
        assertNotNull(actual);
        assertEquals("Super Mario Bros.", actual.getTitle());
    }

    @Test
    void create() {
        Game game = new Game(11, "The Legend of Zelda", "NES", 1986, "Action-Adventure", "Nintendo", "https://upload.wikimedia.org/wikipedia/en/4/41/The_Legend_of_Zelda_cover.png");
        gameRepository.save(game);
        assertThat(gameRepository.findAll()).hasSize(11);
    }
}