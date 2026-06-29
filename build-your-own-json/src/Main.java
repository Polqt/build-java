import lexer.Lexer;
import lexer.LexerException;
import parser.Parser;
import parser.ParserException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <input_file>");
            System.exit(1);
        }

        String json;
        try {
            json = Files.readString(Path.of(args[0]));
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
            return;
        }

        try {
            var tokens = new Lexer(json).tokenize();
            new Parser(tokens).parse();
        } catch (LexerException | ParserException e) {
            System.err.println(e.getMessage());
            System.exit(1);
            return;
        }

        System.out.println("Valid JSON");
        System.exit(0);
    }
}
