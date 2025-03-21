package learn.checkpoint.data;

import learn.checkpoint.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

     List<Review> findByGameId(int game_id);
     List<Review> findByUserId(int user_id);
     List<Review> findByGameTitle(String title);


}
