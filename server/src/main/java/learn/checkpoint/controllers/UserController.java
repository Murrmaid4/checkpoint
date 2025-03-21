package learn.checkpoint.controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import learn.checkpoint.domain.Result;
import io.jsonwebtoken.Jwts;
import learn.checkpoint.domain.ResultType;
import learn.checkpoint.domain.UserService;
import learn.checkpoint.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService service;

    private SecretSigningKey secretSigningKey;

    public UserController(UserService service, SecretSigningKey secretSigningKey) {
        this.service = service;
        this.secretSigningKey = secretSigningKey;
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody User user) {
        Result<User> result = service.createUser(user);
        if (result.isSuccess()) {
//            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
            Map<String, String> output = createJwtFromUser(result.getPayload());
            return new ResponseEntity<>(output, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User user) {
        Result<User> userResult = service.findByUsername(user.getUsername());

        if (userResult.getResultType() == ResultType.NOT_FOUND) {
            return new ResponseEntity<>(userResult.getErrorMessages(), HttpStatus.NOT_FOUND);
        }

//        if (userResult.getPayload().getPassword().equals(user.getPassword())) {


        char[] proposedPassword = user.getPassword().toCharArray();
        char[] existingPassword = userResult.getPayload().getPassword().toCharArray();

        if (BCrypt.verifyer().verify(proposedPassword, existingPassword).verified) {

//            return new ResponseEntity<>(userResult.getPayload(), HttpStatus.OK);
            Map<String, String> jwtMap = createJwtFromUser(userResult.getPayload());
            return new ResponseEntity<>(jwtMap, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(List.of("Username and password do not match"), HttpStatus.UNAUTHORIZED);
        }
    }

    private Map<String, String> createJwtFromUser(User user) {
        String jwt = Jwts.builder()
                .claim("username", user.getUsername())
                .claim("userId", user.getId())
                .signWith(secretSigningKey.getKey())
                .compact();
        Map<String, String> output = new HashMap<>();
        output.put("jwt", jwt);
        return output;
    }

}
