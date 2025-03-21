package learn.checkpoint.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

  public int getGameId() {
    return gameId;
  }

  public void setGameId(int gameId) {
    this.gameId = gameId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getRelease_year() {
    return release_year;
  }

  public void setRelease_year(int release_year) {
    this.release_year = release_year;
  }

  public String getPlatform() {
    return platform;
  }

  public void setPlatform(String platform) {
    this.platform = platform;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public String getThumbnail() {
    return thumbnail;
  }

  public void setThumbnail(String thumbnail) {
    this.thumbnail = thumbnail;
  }

  public List<GameLog> getGameLogs() {
    return gameLogs;
  }

  public void setGameLogs(List<GameLog> gameLogs) {
    this.gameLogs = gameLogs;
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

