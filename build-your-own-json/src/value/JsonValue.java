package value;

/**
 * Sealed interface representing any JSON value.
 * The compiler enforces that only the listed subtypes can implement it.
 */
public sealed interface JsonValue
        permits JsonNull, JsonBoolean, JsonNumber, JsonString, JsonArray, JsonObject {
}
