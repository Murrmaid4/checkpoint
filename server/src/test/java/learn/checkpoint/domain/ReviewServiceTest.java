package learn.checkpoint.domain;

import learn.checkpoint.data.ReviewRepository;
import learn.checkpoint.models.Game;
import learn.checkpoint.models.Review;
import learn.checkpoint.models.User;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ReviewServiceTest {

    @Autowired
    ReviewService reviewService;

    @MockBean
    ReviewRepository repository;

    User user = new User (1,"testUser1", "$2y$10$Ww8dXn9Xujg5guCJoTVeS.JuYpj3akufOudfR0XE0U38QU3/tdC3G", "email@email.com", "Test", "User");
    Game testGame = new Game(1, "Super Mario Bros.", "NES", 1985, "Platformer", "Nintendo", "https://upload.wikimedia.org/wikipedia/en/0/03/Super_Mario_Bros._box.png");
    //

    Review test = new Review(0, testGame, user, 5.5, "Great game!", LocalDateTime.now());



@Nested
public class Create {

    @Test
    void create() {
        Review toAdd = test;
        Review afterAdd = test;
        when(repository.save(toAdd)).thenReturn(afterAdd);
        Result <Review> expected = new Result<>();
        expected.setPayload(afterAdd);

        Result<Review> actual = reviewService.create(toAdd);

        assertEquals(expected, actual);

    }

    @Test
    void failsWhenRatingNegative() {
        Review toAdd = test;
        toAdd.setRating(-1);

        Result<Review> actual = reviewService.create(toAdd);

        assertTrue(actual.getErrorMessages().contains("Rating must be between 0.0 and 10.0."));
        assertEquals(ResultType.INVALID, actual.getResultType());

    }

    @Test
    void failsWhenRatingOverTen() {
        Review toAdd = test;
        toAdd.setRating(11);

        Result<Review> actual = reviewService.create(toAdd);

        assertTrue(actual.getErrorMessages().contains("Rating must be between 0.0 and 10.0."));
        assertEquals(ResultType.INVALID, actual.getResultType());

    }


    @Test
    void failsWhenUserNull() {
        Review toAdd = test;
        toAdd.setUser(null);

        Result<Review> actual = reviewService.create(toAdd);


        assertEquals(ResultType.INVALID, actual.getResultType());

    }

    @Test
    void failsWhenGameNull() {
        Review toAdd = test;
        toAdd.setGame(null);

        Result<Review> actual = reviewService.create(toAdd);

        assertEquals(ResultType.INVALID, actual.getResultType());

    }
}

   @Nested
   public class findBy{
    @Test
    void findByGameId() {
        List<Review> expected = List.of(test);
        when(repository.findByGameId(1)).thenReturn(expected);

        List<Review> actual = reviewService.findByGameId(1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFindByGameId() {
        List<Review> expected = List.of();
        when(repository.findByGameId(1)).thenReturn(expected);

        List<Review> actual = reviewService.findByGameId(1);
        assertEquals(expected, actual);
    }

    @Test
    void findByUserId() {
        List<Review> expected = List.of(test);
        when(repository.findByUserId(1)).thenReturn(expected);

        List<Review> actual = reviewService.findByUserId(1);
        assertEquals(expected, actual);

    }
    @Test
    void shouldNotFindByUserId() {
        List<Review> expected = List.of();
        when(repository.findByUserId(1)).thenReturn(expected);

        List<Review> actual = reviewService.findByUserId(1);
        assertEquals(expected, actual);
    }

    @Test
    void findByGameTitle() {
        List<Review> expected = List.of(test);
        when(repository.findByGameTitle("Super Mario Bros.")).thenReturn(expected);

        List<Review> actual = reviewService.findByGameTitle("Super Mario Bros.");
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFindByGameTitle() {
        List<Review> expected = List.of();
        when(repository.findByGameTitle("Super Mario Bros.")).thenReturn(expected);

        List<Review> actual = reviewService.findByGameTitle("Super Mario Bros.");
        assertEquals(expected, actual);
    }

   }


   @Nested
    public class Update {

        @Test
        void update() {
           Review toUpdate = test;
             when(repository.findById(1)).thenReturn(java.util.Optional.of(toUpdate));

            Result<Review> actual;
            toUpdate.setId(1);
            toUpdate.setRating(7.5);

            actual = reviewService.update(toUpdate);

            assertEquals(ResultType.SUCCESS, actual.getResultType());

           }

       @Test
         void failsWhenRatingNegative() {
              Review toUpdate = test;
              when(repository.findById(1)).thenReturn(java.util.Optional.of(toUpdate));
              toUpdate.setRating(-1);

              Result<Review> actual = reviewService.update(toUpdate);

              assertTrue(actual.getErrorMessages().contains("Rating must be between 0.0 and 10.0."));
              assertEquals(ResultType.INVALID, actual.getResultType());

         }

         @Test
        void shouldNotUpdate(){
            when(repository.findById(1)).thenReturn(java.util.Optional.of(test));

            Result<Review> actual = reviewService.update(test);

            assertEquals(ResultType.INVALID, actual.getResultType());
         }

   }

    @Nested
    public class Delete {


        @Test
        void deleteById() {
            Review review = new Review(1, testGame, user, 5.5, "Great game!", LocalDateTime.now());

            when(repository.findById(1)).thenReturn(java.util.Optional.of(review));
            doNothing().when(repository).deleteById(1);

            Result<Review> actual = reviewService.deleteById(1);
            assertEquals(ResultType.SUCCESS, actual.getResultType());

        }

        @Test
        void shouldNotDelete() {
            when(repository.findById(1)).thenReturn(Optional.empty());

            Result<Review> actual = reviewService.deleteById(1);
            assertEquals(ResultType.NOT_FOUND, actual.getResultType());
        }

    }
}