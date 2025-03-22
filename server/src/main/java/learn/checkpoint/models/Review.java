package learn.checkpoint.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;


@Getter
@Setter
@Entity
@Table(name = "reviews")
public class Review {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private int id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;


    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Size(min = 1, max = 10)
    @Column(name = "rating", nullable = false)
    private double rating;

    @Size(max = 1000, message = "Review must be less than 1000 characters.")
    @Column(name = "review_body")
    private String review_body;

    @Column(name = "created", nullable = false)
    private LocalDateTime created;

    public Review() {
    }

    public Review(int id, Game game, User user, double rating, String review_body, LocalDateTime created) {
        this.id = id;
        this.game = game;
        this.user = user;
        this.rating = rating;
        this.review_body = review_body;
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return id == review.id && Double.compare(rating, review.rating) == 0 && Objects.equals(game, review.game) && Objects.equals(user, review.user) && Objects.equals(review_body, review.review_body) && Objects.equals(created, review.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, game, user, rating, review_body, created);
    }
}
