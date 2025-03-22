package learn.checkpoint.domain;

import learn.checkpoint.data.GameRepository;
import learn.checkpoint.models.Game;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class GameServiceTest {

    @Autowired
    GameService service;

    @MockBean
    GameRepository repository;

    Game addGame = new Game(0,"Stardew Valley", "PC", 2016, "Simulation", "ConcernedApe", "https://upload.wikimedia.org/wikipedia/en/3/34/Stardew_Valley_logo.png");


//    Happy path, null title, long year, year in the past, year in the future, invalid img url, valid img url

    @Test
    void add() {

        Game toAdd = addGame;
        Game afterAdd = addGame;
        when(repository.save(toAdd)).thenReturn(afterAdd);
        Result<Game> expected = new Result<>();
        expected.setPayload(afterAdd);

        Result<Game> actual = service.add(toAdd);

        assertEquals(expected, actual);
    }

    @Test
    void addNullTitle() {
        Game toAdd = addGame;
        toAdd.setTitle(null);

        Result<Game> actual = service.add(toAdd);

        assertTrue(actual.getErrorMessages().contains("Title is required"));
        assertEquals(ResultType.INVALID, actual.getResultType());
    }

    @Test
    void addLongYear() {
        Game toAdd = addGame;
        toAdd.setRelease_year(202245411);

        Result<Game> actual = service.add(toAdd);

        assertTrue(actual.getErrorMessages().contains("Please enter year between 1950 and " + java.time.LocalDate.now().getYear()));
        assertEquals(ResultType.INVALID, actual.getResultType());
    }

    @Test
    void addYearInPast() {
        Game toAdd = addGame;
        toAdd.setRelease_year(1949);

        Result<Game> actual = service.add(toAdd);

        assertTrue(actual.getErrorMessages().contains("Please enter year between 1950 and " + java.time.LocalDate.now().getYear()));
        assertEquals(ResultType.INVALID, actual.getResultType());
    }

    @Test
    void addYearInFuture() {
        Game toAdd = addGame;
        toAdd.setRelease_year(2027);

        Result<Game> actual = service.add(toAdd);

        assertTrue(actual.getErrorMessages().contains("Please enter year between 1950 and " + java.time.LocalDate.now().getYear()));
        assertEquals(ResultType.INVALID, actual.getResultType());
    }

    @Test
    void addInvalidImgUrl() {
        Game toAdd = addGame;
        toAdd.setThumbnail("invalid");

        Result<Game> actual = service.add(toAdd);

        assertTrue(actual.getErrorMessages().contains("Invalid image URL. It must be a valid link ending with png, jpg, jpeg, gif, svg, or webp."));
        assertEquals(ResultType.INVALID, actual.getResultType());
    }

    @Test
    void addValidImgUrl() {
        Game toAdd = addGame;
        toAdd.setThumbnail("https://upload.wikimedia.org/wikipedia/en/3/34/Stardew_Valley_logo.png");

        Result<Game> actual = service.add(toAdd);

        assertEquals(ResultType.SUCCESS, actual.getResultType());
    }

    @Test
    void shouldNotAdd() {


        Result<Game> actual = service.add(new Game(0, null, "PC", 2016, "Simulation", "ConcernedApe", "https://upload.wikimedia.org/wikipedia/en/3/34/Stardew_Valley_logo.png"));


        assertEquals(ResultType.INVALID, actual.getResultType());
    }
}