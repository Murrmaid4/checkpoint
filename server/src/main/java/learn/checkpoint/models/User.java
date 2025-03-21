package learn.checkpoint.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Setter
@Getter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @NotBlank(message = "Username is required")
    @Size( min = 4, max = 50, message = "Username must be less than 50 characters")
    private String username;

    @Column(nullable = false)
    @Size(min = 8, max = 255, message = "Password must be between 8 and 255 characters")
    private String password; // Store only the **hashed** password!


    @NotBlank(message = "Email is required")
    @NotNull(message = "Email is required")
    @Size(max = 105, message = "Email must be less than 105 characters")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "First name is required")
    @NotNull(message = "First name is required")
    @Size(max = 105, message = "First name must be less than 105 characters")
    private String first_name;

    @NotBlank(message = "Last name is required")
    @NotNull(message = "Last name is required")
    @Size(max = 105, message = "Last name must be less than 105 characters")
    private String last_name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserList> userLists; // User's custom lists

     @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GameLog> gameLogs = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews; // Reviews posted by user


    public User() {
    }

    public User(int id, String username, String password, String email, String first_name, String last_name) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;

    }

    public User(int id, String username, String password, String email, String first_name, String last_name, List<GameLog> gameLogs) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gameLogs = gameLogs;
    }

    public User(int id, String username, String password, String email, String first_name, List<UserList> userLists, String last_name, List<GameLog> gameLogs) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.first_name = first_name;
        this.userLists = userLists;
        this.last_name = last_name;
        this.gameLogs = gameLogs;
    }

    // Helper method to add a UserList
    public void addUserList(UserList userList) {
        userLists.add(userList);
        userList.setUser(this); // Ensure the relationship is properly set
    }
    // Helper method to remove a UserList
    public void removeUserList(UserList userList) {
        userLists.remove(userList);
        userList.setUser(null); // Avoid stale references
    }

    // Helper method to add a GameLog
    public void addGameLog(GameLog gameLog) {
        gameLogs.add(gameLog);
        gameLog.setUser(this); // Ensure the relationship is properly set
    }

    // Helper method to remove a GameLog
    public void removeGameLog(GameLog gameLog) {
        gameLogs.remove(gameLog);
        gameLog.setUser(null); // Avoid stale references
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(first_name, user.first_name) && Objects.equals(last_name, user.last_name) && Objects.equals(userLists, user.userLists) && Objects.equals(gameLogs, user.gameLogs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, first_name, last_name, userLists, gameLogs);
    }
}

