package lexer;

import java.util.ArrayList;
import java.util.List;

/**
 * Tokenizes a JSON string into a flat list of {@link Token}s.
 *
 * <p>Single-pass scanner: reads one character at a time and emits the
 * corresponding token. Whitespace is silently skipped. Throws
 * {@link LexerException} on any unrecognized character or malformed literal.
 */
public class Lexer {
    private final String input;
    private final List<Token> tokens = new ArrayList<>();
    private int current = 0;

    /**
     * Creates a new Lexer for the given JSON source text.
     *
     * @param input the raw JSON string to tokenize
     */
    public Lexer(String input) {
        this.input = input;
    }

    /**
     * Scans the entire input and returns all tokens, ending with {@link TokenType#EOF}.
     *
     * @return ordered list of tokens
     * @throws LexerException if the input contains an unrecognised character or malformed literal
     */
    public List<Token> tokenize() {
        while (!isAtEnd()) {
            char c = advance();

            switch (c) {
                case '{' -> addToken(TokenType.LEFT_BRACE, "{");
                case '}' -> addToken(TokenType.RIGHT_BRACE, "}");
                case '"' -> readString();
                case '[' -> addToken(TokenType.LEFT_BRACKET, "[");
                case ']' -> addToken(TokenType.RIGHT_BRACKET, "]");
                case ':' -> addToken(TokenType.COLON, ":");
                case ',' -> addToken(TokenType.COMMA, ",");
                case ' ', '\n', '\r', '\t' -> { /* ignore whitespace */ }
                default -> {
                    if (isDigit(c) || c == '-') {
                        readNumber(c);
                    } else if (isAlpha(c)) {
                        readKeyword(c);
                    } else {
                        throw new LexerException(
                            "Unexpected character '" + c + "' at position " + (current - 1)
                        );
                    }
                }
            }
        }

        tokens.add(new Token(TokenType.EOF, "", current));
        return tokens;
    }

    /**
     * Scans a quoted string literal and emits a {@link TokenType#STRING} token.
     * The opening {@code "} has already been consumed before this method is called.
     * Handles {@code \"} escape sequences so they do not terminate the string early.
     *
     * @throws LexerException if end-of-input is reached before the closing {@code "}
     */
    private void readString() {
        int start = current;
        while (!isAtEnd() && peek() != '"') {
            if (peek() == '\\') advance(); // skip escape char
            advance();
        }
        if (isAtEnd()) throw new LexerException("Unexpected end of string");
        String value = input.substring(start, current);
        advance();
        addToken(TokenType.STRING, value);
    }

    /**
     * Scans a numeric literal (integer, decimal, or exponent form) and emits
     * a {@link TokenType#NUMBER} token.
     *
     * @param firstChar the character already consumed that triggered this call
     *                  (either a digit or {@code -})
     * @throws LexerException if the literal is malformed (e.g., bare {@code -},
     *                        trailing decimal point, or missing exponent digits)
     */
    private void readNumber(char firstChar) {
        int start = current - 1;

        if (firstChar == '-' && (isAtEnd() || !isDigit(peek()))) {
            throw new LexerException("Invalid number at position " + (current - 1));
        }

        while (!isAtEnd() && isDigit(peek())) advance();

        if (!isAtEnd() && peek() == '.') {
            advance();
            if (isAtEnd() || !isDigit(peek()))
                throw new LexerException("Invalid number at position " + start);
            while (!isAtEnd() && isDigit(peek())) advance();
        }

        if (!isAtEnd() && (peek() == 'e' || peek() == 'E')) {
            advance();
            if (!isAtEnd() && (peek() == '+' || peek() == '-')) advance();
            if (isAtEnd() || !isDigit(peek()))
                throw new LexerException("Invalid exponent at position " + start);
            while (!isAtEnd() && isDigit(peek())) advance();
        }

        addToken(TokenType.NUMBER, input.substring(start, current));
    }

    /**
     * Scans an alphabetic keyword and emits the matching {@link TokenType}
     * ({@code true}, {@code false}, or {@code null}).
     *
     * @param firstChar the first letter already consumed
     * @throws LexerException if the scanned word is not a recognised JSON keyword
     */
    private void readKeyword(char firstChar) {
        int start = current - 1;

        while (!isAtEnd() && isAlpha(peek())) {
            advance();
        }

        String word = input.substring(start, current);

        switch (word) {
            case "true"  -> addToken(TokenType.TRUE,  word);
            case "false" -> addToken(TokenType.FALSE, word);
            case "null"  -> addToken(TokenType.NULL,  word);
            default -> throw new LexerException(
                "Unknown keyword '" + word + "' at position " + start
            );
        }
    }

    /** Consumes and returns the current character, advancing the cursor. */
    private char advance() {
        return input.charAt(current++);
    }

    /** Returns the current character without consuming it. */
    private char peek() {
        return input.charAt(current);
    }

    /** Returns {@code true} if all input characters have been consumed. */
    private boolean isAtEnd() {
        return current >= input.length();
    }

    /**
     * Appends a new token to the list.
     *
     * @param type   the token type
     * @param lexeme the matched source text
     */
    private void addToken(TokenType type, String lexeme) {
        tokens.add(new Token(type, lexeme, current - 1));
    }

    /** Returns {@code true} if {@code c} is an ASCII decimal digit. */
    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    /** Returns {@code true} if {@code c} is a lowercase ASCII letter. */
    private boolean isAlpha(char c) {
        return c >= 'a' && c <= 'z';
    }
}
