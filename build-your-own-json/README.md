# json-from-scratch-java

Java practice project — building a JSON parser from scratch, no third-party libraries. Part of my effort to sharpen Java skills through hands-on implementation. Based on the [Coding Challenges](https://codingchallenges.fyi/challenges/challenge-json-parser) series.

Two-phase pipeline: **lexer** (tokenization) → **parser** (recursive descent).

## What I built

| Step | What it parses |
|------|----------------|
| 1 | `{}` (empty object) |
| 2 | String keys and string values |
| 3 | String, number, boolean, null values |
| 4 | Nested objects and arrays |

## Project structure

```
src/
├── Main.java                  # Entry point — reads file, orchestrates lexer + parser
├── lexer/
│   ├── Lexer.java             # Tokenizer: string → List<Token>
│   ├── Token.java             # Token record (type, lexeme, position)
│   ├── TokenType.java         # Enum of all token types
│   └── LexerException.java    # Thrown on unrecognised characters
├── parser/
│   ├── Parser.java            # Recursive descent parser: List<Token> → JsonValue
│   └── ParserException.java   # Thrown on grammar violations
└── value/
    ├── JsonValue.java         # Sealed interface — root of the value hierarchy
    ├── JsonNull.java
    ├── JsonBoolean.java
    ├── JsonNumber.java
    ├── JsonString.java
    ├── JsonArray.java
    └── JsonObject.java
```

## Requirements

- Java 21+

## Usage

```bash
# Compile
javac -d out $(find src -name "*.java")

# Parse a file
java -cp out Main path/to/file.json

# Exit codes: 0 = valid JSON, 1 = invalid
echo $?
```

On Windows (PowerShell):
```powershell
$sources = Get-ChildItem -Recurse -Filter "*.java" src | ForEach-Object { $_.FullName }
javac -d out $sources
java -cp out Main tests\step1\valid.json
```

## Running tests

```powershell
.\run_tests.ps1
```

Tests live in `tests/stepN/` — each step has a `valid.json` and `invalid.json`.

## How it works

### Lexer
Scans input character by character and emits tokens:
- `{` `}` `[` `]` `:` `,` → structural tokens
- `"..."` → `STRING`
- `123`, `-1.5` → `NUMBER`
- `true`, `false`, `null` → keyword tokens

### Parser
Recursive descent — each JSON value type has its own `parseX()` method:

```
parse()
└── parseValue()
    ├── parseObject()  →  { "key": parseValue(), ... }
    ├── parseArray()   →  [ parseValue(), ... ]
    ├── parseString()
    ├── parseNumber()
    └── literal (true / false / null)
```

## License

MIT
