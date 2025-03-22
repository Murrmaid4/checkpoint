package learn.checkpoint.domain;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import learn.checkpoint.data.UserListRepository;
import learn.checkpoint.models.User;
import learn.checkpoint.models.UserList;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserListService {

private final UserListRepository userListRepository;


    public UserListService(UserListRepository userListRepository) {
        this.userListRepository = userListRepository;
    }

    public Result<UserList> createList(UserList userList) {
        Result<UserList> result = validate(userList);

        if (!result.isSuccess()) {
            return result;
        }

        userList = userListRepository.save(userList);
        result.setPayload(userList);
        return result;
    }

   public List<UserList> findByUserId(int userId) {
        return userListRepository.findByUserId(userId);
    }

    public Result<UserList> findById(int listId) {
        Result<UserList> result = new Result<>();

        UserList userList = userListRepository.findById(listId).orElse(null);

        if (userList == null) {
            result.addErrorMessage("List ID not found", ResultType.NOT_FOUND);
            return result;
        }

        result.setPayload(userList);
        return result;
    }

    public Result<UserList> updateList(UserList userList) {
        Result<UserList> result = validate(userList);

        if (!result.isSuccess()) {
            return result;
        }

        if (!userListRepository.existsById(userList.getId())) {
            result.addErrorMessage("List ID not found", ResultType.NOT_FOUND);
            return result;
        }

        userList = userListRepository.save(userList);
        result.setPayload(userList);
        return result;
    }

    public Result<UserList> deleteList(int listId) {
        Result<UserList> result = new Result<>();

        if (!userListRepository.existsById(listId)) {
            result.addErrorMessage("List ID not found", ResultType.NOT_FOUND);
            return result;
        }

        userListRepository.deleteById(listId);
        return result;
    }


    private Result<UserList> validate(Object object) {
        Result<UserList> result = new Result<>();

        if (object == null){
            result.addErrorMessage("List is Invalid", ResultType.INVALID);
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
