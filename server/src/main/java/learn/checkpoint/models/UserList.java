package learn.checkpoint.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "user_lists")
public class UserList {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_list_id")
    private int id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @NotBlank
    @Size(max = 50, message = "List name must be less than 50 characters.")
    @Column(name = "list_name", nullable = false)
    private String list_name;

    @OneToMany(mappedBy = "userList", cascade = CascadeType.ALL, orphanRemoval = true) // Add this line
    private List<UserListItem> userListItems;

    public UserList() {
    }

    public UserList(int id, User user, String list_name) {
        this.id = id;
        this.user = user;
        this.list_name = list_name;
    }



    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserList userList = (UserList) o;
        return id == userList.id && Objects.equals(user, userList.user) && Objects.equals(list_name, userList.list_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, list_name);
    }
}
