package learn.checkpoint.domain;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import learn.checkpoint.data.ReviewRepository;
import learn.checkpoint.models.GameLog;
import learn.checkpoint.models.Review;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ReviewService {

private final ReviewRepository reviewRepository;


    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }


//    create
    public Result<Review> create(Review review) {
        Result<Review> result = validate(review);

        if (!result.isSuccess()) {
            return result;
        }

        review = reviewRepository.save(review);
        result.setPayload(review);
        return result;
    }
//   find by game id
    public List<Review> findByGameId(int game_id) {
        return reviewRepository.findByGameId(game_id);
    }

//    find by user id
    public List<Review> findByUserId(int user_id) {
        return reviewRepository.findByUserId(user_id);
    }
//    find by game title
    public List<Review> findByGameTitle(String title) {
        return reviewRepository.findByGameTitle(title);
    }

//    update
  public Result<Review> update(Review review) {
        Result<Review> result = validate(review);

        if (!result.isSuccess()) {
            return result;
        }

        if (review.getId() <= 0) {
            result.addErrorMessage("Review ID must be set for update", ResultType.INVALID);
            return result;
        }

        if (!reviewRepository.existsById(review.getId())) {
            result.addErrorMessage("Review ID not found", ResultType.NOT_FOUND);
            return result;
        }

        review = reviewRepository.save(review);
        result.setPayload(review);
        return result;
    }

//    delete

    public Result<Review> deleteById(int id) {
        Result<Review> result = new Result<>();

        if (!reviewRepository.existsById(id)) {
            result.addErrorMessage("Review ID not found", ResultType.NOT_FOUND);
            return result;
        }

        reviewRepository.deleteById(id);
        return result;
    }

   //validate

    private Result<Review> validate(Object object) {
        Result<Review> result = new Result<>();

        if (object == null){
            result.addErrorMessage("Review is Invalid", ResultType.INVALID);
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
