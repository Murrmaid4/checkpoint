package learn.checkpoint.domain;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import learn.checkpoint.data.GameRepository;
import learn.checkpoint.models.Game;
import learn.checkpoint.models.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
public class GameService {

    private final GameRepository repository;

    public GameService(GameRepository repository) {
        this.repository = repository;
    }

    public Result<Game> add(Game game) {
        Result<Game> result = validate(game);

        if(game.getRelease_year() > LocalDate.now().getYear() || game.getRelease_year() < 1950) {
            result.addErrorMessage("Please enter year between 1950 and " + LocalDate.now().getYear(), ResultType.INVALID);
        }

        if (!result.isSuccess()) {
            return result;
        }

        if (game.getId() != 0) {
            result.addErrorMessage("Game not valid", ResultType.INVALID);
            return result;
        }


        game = repository.save(game);
        result.setPayload(game);
        return result;
    }

    private Result<Game> validate(Object object) {
        Result<Game> result = new Result<>();

        if (object == null){
            result.addErrorMessage("Check Game requirements.", ResultType.INVALID);
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
