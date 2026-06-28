package value;

public record JsonNull() implements JsonValue {
    @Override
    public String toString() { return "null"; }
}
