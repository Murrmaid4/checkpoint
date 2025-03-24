package learn.checkpoint.controllers;

import learn.checkpoint.domain.GameService;
import learn.checkpoint.domain.Result;
import learn.checkpoint.domain.ResultType;
import learn.checkpoint.models.Game;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/game")
public class GameController {

    private final GameService service;

    public GameController(GameService service) {
        this.service = service;
    }


    @GetMapping
    public List<Game> findAll() {
        return service.findAll();
    }

@GetMapping("{id}")
    public ResponseEntity<Object> findById(@PathVariable int id) {
        Game game = service.findById(id);
        if (game == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(game);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Game game) {
        Result<Game> result = service.add(game);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }










}
