package value;

public record JsonString(String value) implements JsonValue {
    @Override
    public String toString() { return "\"" + value + "\""; }
}
