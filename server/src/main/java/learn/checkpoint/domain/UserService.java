package learn.checkpoint.domain;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import learn.checkpoint.data.UserRepository;
import learn.checkpoint.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {


    private final int BCRYPT_COST = 12;
private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Result<User> createUser(User user) {
        Result<User> result = validate(user);

        if (!result.isSuccess()){
            return result;
        }

      ;

        if (user == null) {
            result.addErrorMessage("User could not be created.", ResultType.INVALID);
        } else {
            String hashedPassword = BCrypt.withDefaults().hashToString(BCRYPT_COST, user.getPassword().toCharArray());

            user.setPassword(hashedPassword);
            User createdUser = userRepository.save(user);
            result.setPayload(createdUser);
        }
        return result;
    }

    public Result<User> findById(int userId) {
        Result<User> result = new Result<>();
        User user = userRepository.findById(userId);

        if (user == null) {
            result.addErrorMessage("User not found with id: " + userId, ResultType.NOT_FOUND);
        } else {
            result.setPayload(user);
        }
        return result;
    }

    public Result<User> findByUsername(String username) {
        Result<User> result = new Result<>();
        User user = userRepository.findByUsername(username);

        if (user == null) {
            result.addErrorMessage("User not found", ResultType.NOT_FOUND);
        } else {
            result.setPayload(user);
        }
        return result;
    }

    private Result<User> validate(Object object) {
        Result<User> result = new Result<>();

        if (object == null){
            result.addErrorMessage("User is required.", ResultType.INVALID);
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
