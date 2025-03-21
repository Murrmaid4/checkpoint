package learn.checkpoint.models;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "game_log")
public class GameLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_log_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "status", nullable = false)
    @Convert(converter = GameStatusConverter.class)
    private GameStatus status;

    @Column(name = "notes")
    private String notes;

    @Column(name = "log_date", nullable = false)
    private LocalDateTime log_date;

    public GameLog() {
    }

    public GameLog(int id, Game game, User user, GameStatus status, String notes, LocalDateTime log_date) {
        this.id = id;
        this.game = game;
        this.user = user;
        this.status = status;
        this.notes = notes;
        this.log_date = log_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GameLog gameLog = (GameLog) o;
        return id == gameLog.id && Objects.equals(game, gameLog.game) && Objects.equals(user, gameLog.user) && status == gameLog.status && Objects.equals(notes, gameLog.notes) && Objects.equals(log_date, gameLog.log_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, game, user, status, notes, log_date);
    }
}
