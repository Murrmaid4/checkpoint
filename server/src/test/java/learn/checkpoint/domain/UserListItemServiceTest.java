package learn.checkpoint.domain;

import learn.checkpoint.data.UserListItemRepository;
import learn.checkpoint.models.Game;
import learn.checkpoint.models.User;
import learn.checkpoint.models.UserList;
import learn.checkpoint.models.UserListItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserListItemServiceTest {

    @Autowired
    UserListItemService service;

    @MockBean
    UserListItemRepository repository;

    User user = new User (1,"testUser1", "$2y$10$Ww8dXn9Xujg5guCJoTVeS.JuYpj3akufOudfR0XE0U38QU3/tdC3G", "email@email.com", "Test", "User");

    UserList testUserList = new UserList(1, user, "Favorites");

    Game addGame = new Game(5,"Stardew Valley", "PC", 2016, "Simulation", "ConcernedApe", "https://upload.wikimedia.org/wikipedia/en/3/34/Stardew_Valley_logo.png");


    UserListItem testItem = new UserListItem(0, testUserList, addGame);



    @Test
    void add() {
       UserListItem toAdd = testItem;
         UserListItem afterAdd = testItem;
         when(repository.save(toAdd)).thenReturn(afterAdd);
            Result<UserListItem> expected = new Result<>();
            expected.setPayload(afterAdd);

            Result<UserListItem> actual = service.add(toAdd);

            assertEquals(expected, actual);
            assertEquals(ResultType.SUCCESS, actual.getResultType());

    }

    @Test
    void shouldNotAdd() {
        UserListItem toAdd = testItem;
        toAdd.setGame(null);
        Result<UserListItem> actual = service.add(toAdd);
        assertEquals(ResultType.INVALID, actual.getResultType());
    }

    @Test
   void shouldDelete(){
        // Arrange
        when(repository.findById(1)).thenReturn(Optional.of(testItem));

        // Act
       service.deleteById(1);

        // Assert
        verify(repository, times(1)).findById(1);
        verify(repository, times(1)).delete(testItem);
    }


}