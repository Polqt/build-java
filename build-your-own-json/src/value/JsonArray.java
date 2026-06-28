package value;

import java.util.List;

public record JsonArray(List<JsonValue> elements) implements JsonValue {
    @Override
    public String toString() { return elements.toString(); }
}
