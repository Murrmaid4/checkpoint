package learn.checkpoint.data;

import learn.checkpoint.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
    public Game findById(int gameId);
    public Game findByTitle(String title);

}
