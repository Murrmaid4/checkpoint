package learn.checkpoint.models;



import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.HashMap;
import java.util.Map;

@Converter(autoApply = false) // Don't auto-apply; explicitly use in the entity
public class GameStatusConverter implements AttributeConverter<GameStatus, String> {

    private static final Map<String, GameStatus> STATUS_MAP = new HashMap<>();

    static {
        for (GameStatus status : GameStatus.values()) {
            STATUS_MAP.put(status.getStatus(), status);
        }
    }

    @Override
    public String convertToDatabaseColumn(GameStatus gameStatus) {
        return gameStatus != null ? gameStatus.getStatus() : null;
    }

    @Override
    public GameStatus convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        GameStatus status = STATUS_MAP.get(dbData);
        if (status == null) {
            throw new IllegalArgumentException("Unknown database value: " + dbData);
        }
        return status;
    }
}
