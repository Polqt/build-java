package parser;

import lexer.Token;
import lexer.TokenType;
import value.*;

import java.util.List;

public class Parser {
    private final List<Token> tokens;
    private int current = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    /**
     * Entry point. Parses one JSON value and verifies nothing follows it.
     */
    public JsonValue parse() {
        // TODO: Step 1 - Call parseValue() to get the root value
        // TODO: Step 1 - After parsing, call consume(TokenType.EOF, "Expected end of input")
        //   to make sure there's no trailing garbage
        // TODO: Step 1 - Return the parsed value
        return null;
    }

    private JsonValue parseValue() {
        // TODO: Step 1 - Look at peek().type() and dispatch:
        //   LEFT_BRACE  -> return parseObject()
        //   LEFT_BRACKET -> return parseArray()   (Step 4)
        //   STRING      -> return parseString()   (Step 2)
        //   NUMBER      -> return parseNumber()   (Step 3)
        //   TRUE        -> advance() and return new JsonBoolean(true)   (Step 3)
        //   FALSE       -> advance() and return new JsonBoolean(false)  (Step 3)
        //   NULL        -> advance() and return new JsonNull()          (Step 3)
        //   default     -> throw new ParserException("Unexpected token: " + peek().type())
        return null;
    }

    private JsonObject parseObject() {
        // TODO: Step 1 - consume(LEFT_BRACE, ...)
        // TODO: Step 1 - If next token is RIGHT_BRACE, consume it and return empty JsonObject
        // TODO: Step 2 - Otherwise, loop:
        //   1. consume(STRING, "Expected string key") to get the key lexeme
        //   2. consume(COLON, "Expected ':' after key")
        //   3. Call parseValue() to get the value
        //   4. Put key -> value into a LinkedHashMap
        //   5. If peek() is COMMA, consume it and continue the loop
        //   6. Else break
        // TODO: Step 2 - consume(RIGHT_BRACE, "Expected '}' after object members")
        // TODO: Step 2 - Return new JsonObject(map)
        return null;
    }

    private JsonArray parseArray() {
        // TODO: Step 4 - consume(LEFT_BRACKET, ...)
        // TODO: Step 4 - If next token is RIGHT_BRACKET, consume it and return empty JsonArray
        // TODO: Step 4 - Otherwise, loop:
        //   1. Call parseValue() and add to a list
        //   2. If peek() is COMMA, consume it and continue
        //   3. Else break
        // TODO: Step 4 - consume(RIGHT_BRACKET, "Expected ']' after array elements")
        // TODO: Step 4 - Return new JsonArray(list)
        return null;
    }

    private JsonString parseString() {
        // TODO: Step 2 - consume(STRING, "Expected string") and return new JsonString(token.lexeme())
        return null;
    }

    private JsonNumber parseNumber() {
        // TODO: Step 3 - consume(NUMBER, "Expected number")
        // TODO: Step 3 - Parse the lexeme to a double with Double.parseDouble(token.lexeme())
        //   Wrap NumberFormatException in ParserException
        // TODO: Step 3 - Return new JsonNumber(parsedDouble)
        return null;
    }

    // --- helpers ---

    /** Returns current token without consuming it. */
    private Token peek() {
        return tokens.get(current);
    }

    /** Consumes and returns the current token. */
    private Token advance() {
        Token t = tokens.get(current);
        current++;
        return t;
    }

    /**
     * Consumes the current token if it matches {@code type}, otherwise throws.
     * TODO: Implement this helper — it is used by every parse method above.
     *   Check peek().type() == type; if yes advance() and return the token;
     *   if no throw new ParserException(message + ", got " + peek().type())
     */
    private Token consume(TokenType type, String message) {
        // TODO: Step 1 - See Javadoc above
        return null;
    }
}
