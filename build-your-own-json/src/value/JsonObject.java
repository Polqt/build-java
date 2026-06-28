package value;

import java.util.Map;

public record JsonObject(Map<String, JsonValue> members) implements JsonValue {
    @Override
    public String toString() { return members.toString(); }
}
