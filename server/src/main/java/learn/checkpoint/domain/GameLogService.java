package learn.checkpoint.domain;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import learn.checkpoint.data.GameLogRepository;
import learn.checkpoint.data.GameRepository;
import learn.checkpoint.models.GameLog;
import learn.checkpoint.models.UserList;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class GameLogService {


    private final GameLogRepository gameLogRepository;

    private final GameRepository gameRepository;

    public GameLogService(GameLogRepository gameLogRepository, GameRepository gameRepository) {
        this.gameLogRepository = gameLogRepository;
        this.gameRepository = gameRepository;
    }

    //create
    public Result<GameLog> create(GameLog gameLog) {
        Result<GameLog> result = validate(gameLog);



        if(result.isSuccess()){
            gameLog = gameLogRepository.save(gameLog);
            result.setPayload(gameLog);
            return result;
        }

    return result;
    }



//find by user id
    public List<GameLog> findByUserId(int userId) {
        return gameLogRepository.findByUserId(userId);
    }

//find by id
    public Result<GameLog> findById(int id) {
        Result<GameLog> result = new Result<>();

        GameLog gameLog = gameLogRepository.findById(id).orElse(null);

        if (gameLog == null) {
            result.addErrorMessage("GameLog ID not found", ResultType.NOT_FOUND);
            return result;
        }

        result.setPayload(gameLog);
        return result;
    }

//    update
    public Result<GameLog> update(GameLog gameLog) {
        Result<GameLog> result = validate(gameLog);

        if (gameLog.getId() <= 0) {
            result.addErrorMessage("GameLog `id` must be set.", ResultType.INVALID);
            return result;
        }

       // Fetch the existing GameLog by ID
        Optional<GameLog> existingGameLogOpt = gameLogRepository.findById(gameLog.getId());

        if (existingGameLogOpt.isEmpty()) {
            result.addErrorMessage("GameLog not found.", ResultType.NOT_FOUND);
            return result;
        }

        GameLog existingGameLog = existingGameLogOpt.get();
        int gameId = existingGameLog.getGame().getId();

        // Ensure the game ID has not changed
        if ( gameLog.getGame().getId() != gameId) {
            result.addErrorMessage("Game ID cannot be changed.", ResultType.INVALID);
            return result;
        }

        if (!result.isSuccess()) {
            return result;
        }

        gameLog = gameLogRepository.save(gameLog);
        result.setPayload(gameLog);
        return result;
    }


//    delete
    public Result<GameLog> deleteById(int id) {
        Result<GameLog> result = new Result<>();

        Optional<GameLog> existingGameLog = gameLogRepository.findById(id);
        if (existingGameLog.isEmpty()) {
            result.addErrorMessage("GameLog not found.", ResultType.NOT_FOUND);
            return result;
        }

        gameLogRepository.deleteById(id);
        return result;
    }



//    validate

    private Result<GameLog> validate(Object object) {
        Result<GameLog> result = new Result<>();

        if (object == null){
            result.addErrorMessage("GameLog is Invalid", ResultType.INVALID);
            return result;
        }


        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Object>> violations = validator.validate(object);

        if (!violations.isEmpty()) {
            for (ConstraintViolation<Object> violation : violations) {
                result.addErrorMessage(violation.getMessage(), ResultType.INVALID);
            }
        }
        return result;
    }


}
