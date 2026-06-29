package lexer;

public record Token(
        TokenType type,
        String lexeme,
        int position
) {}
