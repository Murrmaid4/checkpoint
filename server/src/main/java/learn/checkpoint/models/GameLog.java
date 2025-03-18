package learn.checkpoint.models;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "game_log")
public class GameLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int game_log_id;


    @JoinColumn(name = "game_id")
    private Game game;


    @JoinColumn(name = "user_id")
    private int user_id;

    @Enumerated(EnumType.STRING)
    private GameStatus status;

    private String notes;

    private LocalDateTime log_date;

    public GameLog() {
    }


    public int getGame_log_id() {
        return game_log_id;
    }

    public void setGame_log_id(int game_log_id) {
        this.game_log_id = game_log_id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getLog_date() {
        return log_date;
    }

    public void setLog_date(LocalDateTime log_date) {
        this.log_date = log_date;
    }
}
