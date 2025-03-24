package learn.checkpoint.domain;

import learn.checkpoint.data.GameLogRepository;
import learn.checkpoint.models.Game;
import learn.checkpoint.models.GameLog;
import learn.checkpoint.models.GameStatus;
import learn.checkpoint.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class GameLogServiceTest {

    @Autowired
    GameLogService service;

    @MockBean
    GameLogRepository repository;

//    create - happy and unhappy
//    user is null
//    game is null
//    find by Id - happy and unhappy
    // find by user id - happy and unhappy
    // delete - happy and unhappy
    // update - happy and unhappy


    User user = new User (1,"testUser1", "$2y$10$Ww8dXn9Xujg5guCJoTVeS.JuYpj3akufOudfR0XE0U38QU3/tdC3G", "email@email.com", "Test", "User");
    Game testGame = new Game(1, "Super Mario Bros.", "NES", 1985, "Platformer", "Nintendo", "https://upload.wikimedia.org/wikipedia/en/0/03/Super_Mario_Bros._box.png");

    Game addGame = new Game(5,"Stardew Valley", "PC", 2016, "Simulation", "ConcernedApe", "https://upload.wikimedia.org/wikipedia/en/3/34/Stardew_Valley_logo.png");
    //
    GameLog testGameLog = new GameLog(1,testGame,user , GameStatus.PLAYING, "world1-2", LocalDateTime.now());

    GameLog updateGameLog = new GameLog(1,addGame,user , GameStatus.PLAYING, "world1-2", LocalDateTime.now());

    @Test
    void shouldCreate() {
       GameLog toAdd = testGameLog;
        GameLog afterAdd = testGameLog;
        when(repository.save(toAdd)).thenReturn(afterAdd);
        Result <GameLog> expected = new Result<>();
        expected.setPayload(afterAdd);

        Result<GameLog> actual = service.create(toAdd);

        assertEquals(expected, actual);

    }

    @Test
    void shouldNotCreate() {
        GameLog toAdd = testGameLog;
        toAdd.setUser(null);
        Result<GameLog> actual = service.create(toAdd);
        assertEquals(ResultType.INVALID, actual.getResultType());


    }



    @Test
    void findByUserId() {

        List<GameLog> expected = List.of(testGameLog);
        when(repository.findByUserId(1)).thenReturn(expected);

        List<GameLog> actual = service.findByUserId(1);
        assertEquals(expected, actual);


    }

    @Test
    void shouldNotFindByUserId() {
        List<GameLog> expected = List.of();
        when(repository.findByUserId(1)).thenReturn(expected);

        List<GameLog> actual = service.findByUserId(1);
        assertEquals(expected, actual);
    }

//    @Test
//    void findById() {
//        GameLog log = testGameLog;
//        when(repository.findById(1)).thenReturn(java.util.Optional.of(log));
//
//        Result<GameLog> expected = new Result<>();
//        expected.setPayload(log);
//
//       Result<GameLog> actual = service.findById(1);
//        assertEquals(expected, actual);
//        assertEquals(ResultType.SUCCESS, actual.getResultType());
//    }

//    @Test
//    void shouldNotFindById() {
//
//        GameLog log = testGameLog;
//        when(repository.findById(1)).thenReturn(java.util.Optional.of(log));
//
//        Result<GameLog> expected = new Result<>();
//        expected.setPayload(log);
//
//        Result<GameLog> actual = service.findById(2);
//
//        assertEquals(ResultType.NOT_FOUND , actual.getResultType());
//    }



    @Test
    void update() {
        GameLog log = testGameLog;
        when(repository.findById(1)).thenReturn(java.util.Optional.of(log));


        Result <GameLog> actual;


        log.setStatus(GameStatus.ON_HOLD);
        log.setNotes("moving on to Breathe of the Wild, left off in World 2-4");

        actual = service.update(log);
        assertEquals(ResultType.SUCCESS, actual.getResultType());
    }

    @Test
    void shouldNotUpdate(){

        when(repository.findById(1)).thenReturn(java.util.Optional.of(testGameLog));


        Result <GameLog> actual = service.update(updateGameLog);



        assertEquals(ResultType.INVALID, actual.getResultType());
        assertEquals("Game ID cannot be changed.", actual.getErrorMessages().get(0));

    }

    @Test
    void deleteById() {
// Create a separate instance to avoid reference issues
        GameLog storedGameLog = new GameLog(1, testGame, user, GameStatus.PLAYING, "world1-2", LocalDateTime.now());

        // Mock repository behavior
        when(repository.findById(1)).thenReturn(Optional.of(storedGameLog));
        doNothing().when(repository).deleteById(1); // Simulate successful deletion


        Result<Void> actual = service.deleteById(1);

        assertEquals(ResultType.SUCCESS, actual.getResultType());

    }

    @Test
    void shouldNotDelete() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        Result<Void> actual = service.deleteById(1);

        assertEquals(ResultType.NOT_FOUND, actual.getResultType());
    }
}
