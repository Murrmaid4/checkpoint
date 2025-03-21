package learn.checkpoint.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import learn.checkpoint.models.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class ReviewRepositoryTest {

    @PersistenceContext
    private EntityManager entityManager;

    @BeforeEach
    void setup() {
        entityManager.createNativeQuery("CALL set_known_good_state();").executeUpdate();
    }

    @Autowired
    ReviewRepository reviewRepository;


    @Test
    void findByGameTitle() {
        List<Review> reviews = reviewRepository.findByGameTitle("Super Mario Bros.");

        assertEquals(1, reviews.size());
    }

    @Test
    void findByGameId() {
        List<Review> reviews = reviewRepository.findByGameId(1);

        assertEquals(1, reviews.size());
    }

    @Test
    void gameIdNotFound() {
        List<Review> reviews = reviewRepository.findByGameId(12);

        assertEquals(0, reviews.size());
    }

    @Test
    void findByUserId() {
        List<Review> reviews = reviewRepository.findByUserId(1);

        assertEquals(2, reviews.size());
    }

    @Test
    void userIdNotFound() {
        List<Review> reviews = reviewRepository.findByUserId(6);

        assertEquals(0, reviews.size());

}





}