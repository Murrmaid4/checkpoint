package learn.checkpoint.controllers;


import learn.checkpoint.domain.*;
import learn.checkpoint.models.Game;
import learn.checkpoint.models.GameLog;
import learn.checkpoint.models.GameStatus;
import learn.checkpoint.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/log")
public class GameLogController {

    private final GameLogService service;

    private final GameService gameService;

    private final UserService userService;

    private final SecretSigningKey secretSigningKey;

    public GameLogController(GameLogService service , GameService gameService, UserService userService, SecretSigningKey secretSigningKey) {
        this.service = service;
        this.gameService = gameService;
        this.userService = userService;
        this.secretSigningKey = secretSigningKey;
    }



    //find by id
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable int id) {
        Result<GameLog> result = service.findById(id);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result.getPayload());
        } else {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.NOT_FOUND);
        }
    }

//    find by users ID

    @GetMapping("/myLogs")
    public Object findUserLogs(@RequestHeader Map<String, String> headers) {
        Integer userId = getUserIdFromHeaders(headers);
        if (userId == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return service.findByUserId(userId);
    }



    //add
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Map<String, Object> requestBody) {
       int gameId = (int) requestBody.get("game_id");
        int userId = (int) requestBody.get("user_id");

        Game game = gameService.findById(gameId);
        Result<User> user = userService.findById(userId);
//do i need to get id from headers here???

        if (game == null) {
            return new ResponseEntity<>(List.of("Game ID not found"), HttpStatus.NOT_FOUND);
        }

        if (user.getResultType() != ResultType.SUCCESS) {
            return new ResponseEntity<>(List.of("User ID not found"), HttpStatus.NOT_FOUND);
        }

        GameLog gameLog = new GameLog();
        gameLog.setGame(game);
        gameLog.setUser(user.getPayload());
        gameLog.setNotes(requestBody.get("notes").toString());
        // Parse status from string to enum
        if (requestBody.containsKey("status")) {
            try {
                GameStatus status = GameStatus.valueOf(((String) requestBody.get("status")).toUpperCase());
                gameLog.setStatus(status);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body("Invalid status. Allowed values: " + Arrays.toString(GameStatus.values()));
            }
        } else {
            return ResponseEntity.badRequest().body("Status is required.");
        }
        gameLog.setLog_date(LocalDateTime.now());


        // Save GameLog
        Result<GameLog> result = service.create(gameLog);
        return result.isSuccess()
                ? ResponseEntity.status(HttpStatus.CREATED).body(result.getPayload())
                : ErrorResponse.build(result);


    }

    //   update
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody Map<String, Object> requestBody) {
        // Retrieve existing GameLog
        Result<GameLog> existingGameLog = service.findById(id);
        if (existingGameLog.getResultType() != ResultType.SUCCESS) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("GameLog ID not found.");
        }
        GameLog updateLog = existingGameLog.getPayload();

        // Update notes if present
        if (requestBody.containsKey("notes")) {
            updateLog.setNotes(requestBody.get("notes").toString());
        }

        // Update status if present
        if (requestBody.containsKey("status")) {
            try {
                GameStatus status = GameStatus.valueOf(((String) requestBody.get("status")).toUpperCase());
                updateLog.setStatus(status);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body("Invalid status. Allowed values: " + Arrays.toString(GameStatus.values()));
            }
        }

        // Update log date to the current timestamp
        updateLog.setUpdated(LocalDateTime.now());

        // Save updated GameLog
        Result<GameLog> result = service.update(updateLog);
        return result.isSuccess()
                ? ResponseEntity.ok(result.getPayload())
                : ErrorResponse.build(result);
    }


    //    delete by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable int id) {
        Result<Void> result = service.deleteById(id);

        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

private Integer getUserIdFromHeaders(Map<String, String> headers) {
    if (headers.get("authorization") == null) {
        return null;
    }

    try {
        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(secretSigningKey.getKey())
                .build().parseClaimsJws(headers.get("authorization"));
        return (Integer) claims.getBody().get("userId");
    } catch (Exception e) {
        return null;
    }
}
}
