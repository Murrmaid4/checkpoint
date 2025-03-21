package learn.checkpoint.models;

public enum GameStatus {

    PLAYING("Playing"),
    COMPLETED("Completed"),
    DROPPED("Dropped"),
    ON_HOLD("On Hold"),
    WANT_TO_PLAY("Want to Play");

private final String status;

GameStatus(String status) {
    this.status = status;
}

public String getStatus() {
    return status;
}

}

