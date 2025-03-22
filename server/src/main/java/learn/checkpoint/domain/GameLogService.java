package learn.checkpoint.domain;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import learn.checkpoint.data.GameLogRepository;
import learn.checkpoint.models.GameLog;
import learn.checkpoint.models.UserList;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class GameLogService {


    private final GameLogRepository gameLogRepository;

    public GameLogService(GameLogRepository gameLogRepository) {
        this.gameLogRepository = gameLogRepository;
    }

    //create
    public Result<GameLog> create(GameLog gameLog) {
        Result<GameLog> result = validate(gameLog);

        if (!result.isSuccess()) {
            return result;
        }

        gameLog = gameLogRepository.save(gameLog);
        result.setPayload(gameLog);
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

        if (!result.isSuccess()) {
            return result;
        }

        if (gameLog.getId() <= 0) {
            result.addErrorMessage("GameLog `id` must be set.", ResultType.INVALID);
            return result;
        }

        if (!gameLogRepository.existsById(gameLog.getId())) {
            String msg = String.format("GameLog ID: %s, not found.", gameLog.getId());
            result.addErrorMessage(msg, ResultType.NOT_FOUND);
        }

        gameLog = gameLogRepository.save(gameLog);
        result.setPayload(gameLog);
        return result;
    }


//    delete
    public Result<GameLog> deleteById(int id) {
        Result<GameLog> result = new Result<>();

        if (!gameLogRepository.existsById(id)) {
            result.addErrorMessage("GameLog ID not found", ResultType.NOT_FOUND);
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
