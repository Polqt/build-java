package value;

public record JsonBoolean(boolean value) implements JsonValue {
    @Override
    public String toString() { return String.valueOf(value); }
}
