import lexer.Lexer;
import lexer.LexerException;
import parser.Parser;
import parser.ParserException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {
        // TODO: Step 1 - Validate args: if args.length != 1, print usage and System.exit(1)
        //   e.g., System.err.println("Usage: json-parser <file>");

        // TODO: Step 1 - Read file content using Files.readString(Path.of(args[0]))
        //   Catch IOException: print the error message to stderr and System.exit(1)

        // TODO: Step 1 - Tokenize: create new Lexer(content) and call .tokenize()
        //   Catch LexerException: print ex.getMessage() to stderr and System.exit(1)

        // TODO: Step 1 - Parse: create new Parser(tokens) and call .parse()
        //   Catch ParserException: print ex.getMessage() to stderr and System.exit(1)

        // TODO: Step 1 - On success: print "Valid JSON" to stdout and System.exit(0)
    }
}
