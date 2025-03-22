package learn.checkpoint.domain;


import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import learn.checkpoint.data.UserListItemRepository;
import learn.checkpoint.models.UserListItem;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserListItemService {

    private final UserListItemRepository repository;


    public UserListItemService(UserListItemRepository repository) {
        this.repository = repository;
    }

 public Result<UserListItem> add(UserListItem userListItem) {
        Result<UserListItem> result = validate(userListItem);

        if (!result.isSuccess()) {
            return result;
        }

        if (userListItem.getId() != 0) {
            result.addErrorMessage("User List Item ID cannot be set.", ResultType.INVALID);
            return result;
        }

        userListItem = repository.save(userListItem);
        result.setPayload(userListItem);
        return result;
    }


    @Transactional
    public void deleteById(int id) {
        UserListItem userListItem = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserListItem not found with id: " + id));

        repository.delete(userListItem);
    }


    private Result<UserListItem> validate(Object object) {
        Result<UserListItem> result = new Result<>();

        if (object == null){
            result.addErrorMessage("User List Item invalid", ResultType.INVALID);
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
