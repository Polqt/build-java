package parser;

import lexer.Token;
import lexer.TokenType;
import value.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Recursive-descent parser that converts a flat token list into a
 * {@link JsonValue} tree.
 *
 * <p>Grammar supported:
 * <pre>{@code
 * value  → object | array | string | number | "true" | "false" | "null"
 * object → "{" ( string ":" value ( "," string ":" value )* )? "}"
 * array  → "[" ( value ( "," value )* )? "]"
 * }</pre>
 */
public class Parser {
    private final List<Token> tokens;
    private int current = 0;

    /**
     * Creates a new Parser for the given token list.
     *
     * @param tokens ordered token list produced by {@link lexer.Lexer}
     */
    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    /**
     * Parses the token list as a single JSON value.
     *
     * @return the root {@link JsonValue}
     * @throws ParserException if the input is not valid JSON
     */
    public JsonValue parse() {
        JsonValue value = parseValue();
        consume(TokenType.EOF, "Expected end of input");
        return value;
    }

    /**
     * Dispatches to the appropriate parse method based on the current token type.
     *
     * @return the parsed {@link JsonValue}
     * @throws ParserException if the current token does not start a valid JSON value
     */
    private JsonValue parseValue() {
        return switch (peek().type()) {
            case LEFT_BRACE ->  parseObject();
            case LEFT_BRACKET ->  parseArray();
            case STRING -> parseString();
            case NUMBER -> parseNumber();
            case TRUE -> { advance(); yield new JsonBoolean(true); }
            case FALSE -> { advance(); yield new JsonBoolean(false); }
            case NULL -> { advance(); yield new JsonNull(); }
            default -> throw new ParserException("Unexpected token: " + peek().type());
        };
    }

    /**
     * Parses a JSON object ({@code { "key": value, ... }}).
     *
     * @return a {@link JsonObject} with insertion-ordered members
     * @throws ParserException if the object is syntactically invalid
     */
    private JsonObject parseObject() {
        consume(TokenType.LEFT_BRACE, "Expected '{'");
        Map<String, JsonValue> map = new LinkedHashMap<>();

        if (peek().type() == TokenType.RIGHT_BRACE) {
            advance();
            return new JsonObject(map);
        }

        do {
            Token key = consume(TokenType.STRING, "Expected string key");
            consume(TokenType.COLON, "Expected ':' after key");
            map.put(key.lexeme(), parseValue());
        } while (peek().type() == TokenType.COMMA && advance() != null);

        consume(TokenType.RIGHT_BRACE, "Expected '}' after object members");
        return new JsonObject(map);
    }

    /**
     * Parses a JSON array ({@code [ value, ... ]}).
     *
     * @return a {@link JsonArray} containing the parsed elements
     * @throws ParserException if the array is syntactically invalid
     */
    private JsonArray parseArray() {
        consume(TokenType.LEFT_BRACKET, "Expected '['");
        List<JsonValue> values = new ArrayList<>();

        if (peek().type() == TokenType.RIGHT_BRACKET) {
            advance();
            return new JsonArray(values);
        }

        do {
            values.add(parseValue());
        } while (peek().type() == TokenType.COMMA && advance() != null);

        consume(TokenType.RIGHT_BRACKET, "Expected ']' after array members");
        return new JsonArray(values);
    }

    /**
     * Parses a JSON string value.
     *
     * @return a {@link JsonString} wrapping the token's lexeme
     * @throws ParserException if the current token is not a {@link TokenType#STRING}
     */
    private JsonString parseString() {
        Token token = consume(TokenType.STRING, "Expected string key");
        return new JsonString(token.lexeme());
    }

    /**
     * Parses a JSON number value.
     *
     * @return a {@link JsonNumber} wrapping the parsed {@code double}
     * @throws ParserException if the current token is not a {@link TokenType#NUMBER}
     *                         or if the lexeme cannot be parsed as a double
     */
    private JsonNumber parseNumber() {
        Token token = consume(TokenType.NUMBER, "Expected number");
        try {
            return new JsonNumber(Double.parseDouble(token.lexeme()));
        } catch (NumberFormatException e) {
            throw new ParserException("Expected number: " + token.lexeme());
        }
    }

    /** Returns the current token without consuming it. */
    private Token peek() {
        return tokens.get(current);
    }

    /** Consumes and returns the current token, advancing the cursor. */
    private Token advance() {
        Token t = tokens.get(current);
        current++;
        return t;
    }

    /**
     * Consumes the current token if it matches {@code type}, otherwise throws.
     *
     * @param type    the expected {@link TokenType}
     * @param message error prefix included in the exception message
     * @return the consumed token
     * @throws ParserException if the current token type does not match {@code type}
     */
    private Token consume(TokenType type, String message) {
        if (peek().type() == type) return advance();
        throw new ParserException(message + ", got " + peek().type());
    }
}
