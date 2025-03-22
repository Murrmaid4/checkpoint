package learn.checkpoint.domain;

import learn.checkpoint.data.UserListRepository;
import learn.checkpoint.models.Game;
import learn.checkpoint.models.GameLog;
import learn.checkpoint.models.User;
import learn.checkpoint.models.UserList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserListServiceTest {

    @Autowired
    UserListService service;

    @MockBean
    UserListRepository repository;

    User user = new User (1,"testUser1", "$2y$10$Ww8dXn9Xujg5guCJoTVeS.JuYpj3akufOudfR0XE0U38QU3/tdC3G", "email@email.com", "Test", "User");

    UserList testUserList = new UserList(1, user, "Favorites");

    UserList newList = new UserList(0, user, "New List");

    Game addGame = new Game(5,"Stardew Valley", "PC", 2016, "Simulation", "ConcernedApe", "https://upload.wikimedia.org/wikipedia/en/3/34/Stardew_Valley_logo.png");




    @Test
    void createList() {
    UserList toAdd = newList;
    UserList afterAdd = newList;
    when(repository.save(toAdd)).thenReturn(afterAdd);

    Result<UserList> expected = new Result<>();
    expected.setPayload(afterAdd);

    Result<UserList> actual = service.createList(toAdd);

    assertEquals(expected, actual);
    assertEquals(ResultType.SUCCESS, actual.getResultType());

    }

//    unhappy path

    //    list name too long
    @Test
    void unHappyCreateList() {
        UserList toAdd = newList;
        toAdd.setList_name("This is a really long list name that is too long to be a list name");
        Result<UserList> actual = service.createList(toAdd);
        assertEquals(ResultType.INVALID, actual.getResultType());
    }

    @Test
    void findByUserId() {

       List<UserList> expected = List.of(testUserList);
      when(repository.findByUserId(1)).thenReturn(expected);

        List<UserList> actual = service.findByUserId(1);
        assertEquals(expected, actual);

    }

//    can't find user id

    @Test
    void unHappyFindByUserId() {
        List<UserList> expected = List.of();
        when(repository.findByUserId(1)).thenReturn(expected);

        List<UserList> actual = service.findByUserId(1);
        assertEquals(expected, actual);
    }


    @Test
    void findListById() {
        UserList list = testUserList;
        when(repository.findById(1)).thenReturn(java.util.Optional.of(list));

        Result<UserList> expected = new Result<>();
        expected.setPayload(list);

        Result<UserList> actual = service.findById(1);

        assertEquals(expected, actual);
        assertEquals(ResultType.SUCCESS, actual.getResultType());
    }

    @Test
    void unHappyFindListById() {
        UserList list = testUserList;
        when(repository.findById(1)).thenReturn(java.util.Optional.of(list));

        Result<UserList> expected = new Result<>();
        expected.setPayload(list);

        Result<UserList> actual = service.findById(2);

        assertEquals(ResultType.NOT_FOUND, actual.getResultType());
    }

//    update

    @Test
    void updateList() {
        UserList list = testUserList;
        when(repository.findById(1)).thenReturn(java.util.Optional.of(list));

        Result<UserList> actual;

        list.setList_name("Updated List");

        actual = service.updateList(list);
        assertEquals(ResultType.SUCCESS, actual.getResultType());


    }

    @Test
    void unHappyUpdateList() {
        UserList toAdd = newList;
        UserList afterAdd = newList;
        when(repository.save(toAdd)).thenReturn(afterAdd);

        Result<UserList> expected = new Result<>();
        expected.setPayload(afterAdd);

        toAdd.setList_name(null);
        Result<UserList> actual = service.updateList(toAdd);

        assertEquals(ResultType.INVALID, actual.getResultType());

    }


//happy path delete
    @Test
    void deleteList() {
        UserList list = new UserList(1, user, "Favorites");


        when(repository.findById(1)).thenReturn(java.util.Optional.of(list));
       doNothing().when(repository).deleteById(1);

        Result<UserList> actual = service.deleteList(1);
        assertEquals(ResultType.SUCCESS, actual.getResultType());


    }

//    unhappy path delete

    @Test
    void unHappyDeleteList() {

        when(repository.findById(1)).thenReturn(Optional.empty());

        Result<UserList> actual = service.deleteList(1);

        assertEquals(ResultType.NOT_FOUND, actual.getResultType());
    }
}