package lexer;

public record Token(
        TokenType type,   // fixed: was "tpye"
        String lexeme,
        int position
) {}
