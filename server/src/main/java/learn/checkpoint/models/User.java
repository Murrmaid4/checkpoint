package learn.checkpoint.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

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

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<UserList> userLists; // User's custom lists
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<GameLog> gameLogs; // Games the user is currently playing
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Review> reviews; // Reviews posted by user


    public User() {
    }

    public User(int userId, String username, String password, String email, String first_name, String last_name) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(first_name, user.first_name) && Objects.equals(last_name, user.last_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, password, email, first_name, last_name);
    }
}

