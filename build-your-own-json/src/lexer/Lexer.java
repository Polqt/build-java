package lexer;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private final String input;
    private final List<Token> tokens = new ArrayList<>();
    private int current = 0;

    public Lexer(String input) {
        this.input = input;
    }

    public List<Token> tokenize() {
        while (!isAtEnd()) {
            char c = advance();

            switch (c) {
                case '{' -> addToken(TokenType.LEFT_BRACE, "{");
                // TODO: Step 1 - Add case for '}' -> addToken RIGHT_BRACE
                // TODO: Step 2 - Add case for '"' -> readString()
                // TODO: Step 4 - Add case for '[' -> addToken LEFT_BRACKET
                // TODO: Step 4 - Add case for ']' -> addToken RIGHT_BRACKET
                // TODO: Step 2 - Add case for ':' -> addToken COLON
                // TODO: Step 2 - Add case for ',' -> addToken COMMA
                case ' ', '\n', '\r', '\t' -> { /* ignore whitespace */ }
                default -> {
                    if (isDigit(c) || c == '-') {
                        readNumber(c);
                    } else if (isAlpha(c)) {
                        // TODO: Step 3 - Call readKeyword(c) here
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

    private void readString() {
        // TODO: Step 2 - Scan characters until you hit a closing '"'
        //   - Track the start position (current is already past the opening '"')
        //   - Handle escape sequence '\"' so it doesn't end the string early
        //   - If you reach end-of-input without a closing '"', throw LexerException
        //   - Extract substring from start to current (excluding the closing '"')
        //   - Call addToken(TokenType.STRING, value)
    }

    private void readNumber(char firstChar) {
        int start = current - 1;

        // TODO: Step 3 - Handle negative sign: firstChar may be '-', so the digit comes next
        //   If firstChar == '-' and isAtEnd() or !isDigit(peek()), throw LexerException

        while (!isAtEnd() && isDigit(peek())) {
            advance();
        }

        // TODO: Step 3 - Handle decimal point: if peek() == '.', advance and consume more digits
        //   If no digits follow the '.', throw LexerException("Invalid number...")

        // TODO: Step 3 - Handle exponent: if peek() == 'e' or 'E', advance,
        //   optionally consume '+' or '-', then consume digits

        String value = input.substring(start, current);
        addToken(TokenType.NUMBER, value);
    }

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

    private char advance() {
        return input.charAt(current++);
    }

    private char peek() {
        return input.charAt(current);
    }

    private boolean isAtEnd() {
        return current >= input.length();
    }

    private void addToken(TokenType type, String lexeme) {
        tokens.add(new Token(type, lexeme, current - 1));
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isAlpha(char c) {
        return c >= 'a' && c <= 'z';
    }
}
