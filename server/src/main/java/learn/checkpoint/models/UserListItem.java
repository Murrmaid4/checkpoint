package learn.checkpoint.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "user_list_items")
public class UserListItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_list_items_id")
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_list_id", nullable = false)
    private UserList userList; // Many user list items belong to one user list

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game; // Optionally, link to a 'Game' entity or another entity

    public UserListItem() {
    }

    public UserListItem(int id, UserList userList, Game game) {
        this.id = id;
        this.userList = userList;
        this.game = game;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserListItem that = (UserListItem) o;
        return id == that.id && Objects.equals(userList, that.userList) && Objects.equals(game, that.game);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userList, game);
    }
}
