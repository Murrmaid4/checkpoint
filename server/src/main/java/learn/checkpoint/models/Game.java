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
@Table(name = "game")
public class Game {
  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gameId;

    @NotBlank(message = "Title is required")
    private String title;

    private String platform;

    private int release_year;

    private String genre;

    private String publisher;

    private String thumbnail;

  @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<GameLog> gameLogs = new ArrayList<>();


  public Game(int gameId, String title, String platform, int release_year, String genre, String publisher, String thumbnail) {
    this.gameId = gameId;
    this.title = title;
    this.platform = platform;
    this.release_year = release_year;
    this.genre = genre;
    this.publisher = publisher;
    this.thumbnail = thumbnail;
  }

  public Game(int gameId, String title, String platform, int release_year, String genre, String publisher, String thumbnail, List<GameLog> gameLogs) {
    this.gameId = gameId;
    this.title = title;
    this.platform = platform;
    this.release_year = release_year;
    this.genre = genre;
    this.publisher = publisher;
    this.thumbnail = thumbnail;
    this.gameLogs = gameLogs;
  }

  public Game() {
  }

    @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Game game = (Game) o;
    return gameId == game.gameId && release_year == game.release_year && Objects.equals(title, game.title) && Objects.equals(platform, game.platform) && Objects.equals(genre, game.genre) && Objects.equals(publisher, game.publisher) && Objects.equals(thumbnail, game.thumbnail) && Objects.equals(gameLogs, game.gameLogs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(gameId, title, platform, release_year, genre, publisher, thumbnail, gameLogs);
  }
}

