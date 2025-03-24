package learn.checkpoint.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "game_id")
    private int id;

    @NotNull
    @NotBlank(message = "Title is required")
    private String title;

    private String platform;

    private int release_year;

    private String genre;

    private String publisher;

  @Pattern(
          regexp = "^(https?:\\/\\/.*\\.(?:png|jpg|jpeg|gif|svg|webp))$",
          message = "Invalid image URL. It must be a valid link ending with png, jpg, jpeg, gif, svg, or webp."
  )
    private String thumbnail;


  @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnore
  private List<GameLog> gameLogs = new ArrayList<>();

  @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnore
  private List<Review> reviews; // Reviews posted by user


  public Game(int id, String title, String platform, int release_year, String genre, String publisher, String thumbnail) {
    this.id = id;
    this.title = title;
    this.platform = platform;
    this.release_year = release_year;
    this.genre = genre;
    this.publisher = publisher;
    this.thumbnail = thumbnail;
  }

  public Game(int id, String title, String platform, int release_year, String genre, String publisher, String thumbnail, List<GameLog> gameLogs) {
    this.id = id;
    this.title = title;
    this.platform = platform;
    this.release_year = release_year;
    this.genre = genre;
    this.publisher = publisher;
    this.thumbnail = thumbnail;
    this.gameLogs = gameLogs;
  }



  public Game(int id, String title, String platform, int release_year, String genre, String publisher, String thumbnail, List<GameLog> gameLogs, List<Review> reviews) {
    this.id = id;
    this.title = title;
    this.platform = platform;
    this.release_year = release_year;
    this.genre = genre;
    this.publisher = publisher;
    this.thumbnail = thumbnail;
    this.gameLogs = gameLogs;
    this.reviews = reviews;
  }



  public Game() {
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Game game = (Game) o;
    return id == game.id && release_year == game.release_year && Objects.equals(title, game.title) && Objects.equals(platform, game.platform) && Objects.equals(genre, game.genre) && Objects.equals(publisher, game.publisher) && Objects.equals(thumbnail, game.thumbnail) && Objects.equals(gameLogs, game.gameLogs) && Objects.equals(reviews, game.reviews);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, platform, release_year, genre, publisher, thumbnail, gameLogs, reviews);
  }
}

