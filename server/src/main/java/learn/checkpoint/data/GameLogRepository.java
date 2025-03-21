package learn.checkpoint.data;

import learn.checkpoint.models.GameLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface GameLogRepository extends JpaRepository<GameLog, Integer> {

  Optional<GameLog> findById(int id);

  List<GameLog> findByUserId(int id);  //


}
