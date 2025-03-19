package learn.checkpoint.domain;

import learn.checkpoint.data.UserRepository;
import learn.checkpoint.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserServiceTest {

    @Autowired
    UserService service;

    private User testUser;
    User newUser = new User (4,"testUser4", "$2y$10$Ww8dXn9Xujg5guCJoTVeS.JuYpj3akufOudfR0XE0U38QU3/tdC3G", "hello@gmail.com", "Test4", "User4");

    @MockBean
    UserRepository repository;

//Tests for create user: valid email, no null passwords, unique user name and first and last names are not blank, and password is longer than 8 characters
    @Nested
    public class CreateUser {
        @Test
        void createUserHappyPath() {

            User toAdd = newUser;
            User afterAdd = newUser;
            when(repository.save(toAdd)).thenReturn(afterAdd);
            Result<User> expected = new Result<>();
            expected.setPayload(afterAdd);

            Result<User> actual = service.createUser(toAdd);

            assertEquals(expected, actual);
        }

        @Test
        void failsWhenUsernameBlank() {
            User toAdd = newUser;
            toAdd.setUsername("");

            Result<User> actual = service.createUser(toAdd);

            assertTrue(actual.getErrorMessages().contains("Username is required"));
            assertEquals(ResultType.INVALID, actual.getResultType());

        }

        @Test
        void failsWhenPasswordBlank() {
            User toAdd = newUser;
            toAdd.setPassword("here");
            Result<User> expected = new Result<>();
            expected.addErrorMessage("Password must be between 8 and 255 characters", ResultType.INVALID);

            Result<User> actual = service.createUser(toAdd);

            assertTrue(actual.getErrorMessages().contains("Password must be between 8 and 255 characters"));
            assertEquals(ResultType.INVALID, actual.getResultType());

        }

        @Test
        void failsWhenEmailIsBlank() {
            User toAdd = newUser;
            toAdd.setEmail("");
            Result<User> expected = new Result<>();
            expected.addErrorMessage("Email is required", ResultType.INVALID);

            Result<User> actual = service.createUser(toAdd);

            assertTrue(actual.getErrorMessages().contains("Email is required"));
            assertEquals(ResultType.INVALID, actual.getResultType());
        }

        @Test
        void failsWhenFirstNameBlank() {
            User toAdd = newUser;
            toAdd.setFirst_name("");
            Result<User> expected = new Result<>();
            expected.addErrorMessage("First name is required", ResultType.INVALID);

            Result<User> actual = service.createUser(toAdd);

            assertTrue(actual.getErrorMessages().contains("First name is required"));
            assertEquals(ResultType.INVALID, actual.getResultType());
        }

        @Test
        void failsWhenLastNameBlank() {
            User toAdd = newUser;
            toAdd.setLast_name("");
            Result<User> expected = new Result<>();
            expected.addErrorMessage("Last name is required", ResultType.INVALID);

            Result<User> actual = service.createUser(toAdd);

            assertTrue(actual.getErrorMessages().contains("Last name is required"));
            assertEquals(ResultType.INVALID, actual.getResultType());
        }

        @Test
        void failsWhenPasswordIsShorterThan8Characters() {
         User toAdd = newUser;
         toAdd.setPassword("1234567");
         Result<User> expected = new Result<>();
         expected.addErrorMessage("Password must be between 8 and 255 characters", ResultType.INVALID);

         Result<User> actual = service.createUser(toAdd);

         assertTrue(actual.getErrorMessages().contains("Password must be between 8 and 255 characters"));
         assertEquals(ResultType.INVALID, actual.getResultType());
     }

        @Test
        void failsWhenEmailIsInvalid(){
            User toAdd = newUser;
            toAdd.setEmail("hello");
            Result<User> expected = new Result<>();
            expected.addErrorMessage("Invalid email format", ResultType.INVALID);

            Result<User> actual = service.createUser(toAdd);

            assertTrue(actual.getErrorMessages().contains("Invalid email format"));
            assertEquals(ResultType.INVALID, actual.getResultType());
        }

        @Test
        void failsWhenUsernameIsAlreadyTaken(){
            User toAdd = newUser;
            when(repository.findByUsername(toAdd.getUsername())).thenReturn(toAdd);
            Result<User> expected = new Result<>();
            expected.addErrorMessage("Username is already taken", ResultType.INVALID);

            Result<User> actual = service.createUser(toAdd);


            assertEquals(ResultType.INVALID, actual.getResultType());
        }



    }


    @Nested
    public class FindByUsername {
        @Test
        void happyPath() {
            User user = newUser;
            when(repository.findByUsername(user.getUsername())).thenReturn(user);
            Result<User> expected = new Result<>();
            expected.setPayload(user);

            Result<User> actual = service.findByUsername(user.getUsername());

            assertEquals(expected, actual);
        }

        @Test
        void shouldNotFindMissingUser() {
            User user = newUser;
            when(repository.findByUsername(user.getUsername())).thenReturn(null);
            Result<User> expected = new Result<>();
            expected.addErrorMessage("User not found", ResultType.NOT_FOUND);

            Result<User> actual = service.findByUsername(user.getUsername());

            assertTrue(actual.getErrorMessages().contains("User not found"));
            assertEquals(ResultType.NOT_FOUND, actual.getResultType());
        }
    }


    @Nested
    public class FindById {
        @Test
        void happyPath() {
            User user = newUser;
            when(repository.findById(user.getUserId())).thenReturn(user);
            Result<User> expected = new Result<>();
            expected.setPayload(user);

            Result<User> actual = service.findById(user.getUserId());

            assertEquals(expected, actual);
        }

        @Test
        void shouldNotFindMissingUser() {
            User user = newUser;
            when(repository.findById(user.getUserId())).thenReturn(null);
            Result<User> expected = new Result<>();
            expected.addErrorMessage("User not found with id: " + user.getUserId(), ResultType.NOT_FOUND);

            Result<User> actual = service.findById(user.getUserId());

            assertTrue(actual.getErrorMessages().contains("User not found with id: " + user.getUserId()));
            assertEquals(ResultType.NOT_FOUND, actual.getResultType());
        }
    }

}