package learn.checkpoint.domain;

import learn.checkpoint.data.UserRepository;
import learn.checkpoint.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }





}
