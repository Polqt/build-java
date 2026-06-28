package value;

public record JsonNumber(double value) implements JsonValue {
    @Override
    public String toString() {
        // Print as int when the value has no fractional part
        if (value == Math.floor(value) && !Double.isInfinite(value)) {
            return String.valueOf((long) value);
        }
        return String.valueOf(value);
    }
}
