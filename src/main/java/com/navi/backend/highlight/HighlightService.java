package com.navi.backend.highlight;

import com.navi.backend.lexer_parser.lat.PigLatinLexer;
import com.navi.backend.lexer_parser.y.YLexer;
import com.navi.backend.lexer_parser.z.ZLexer;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Token;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.IntFunction;

// saca los rangos a colorear usando el lexer de cada lenguaje
// va a nivel lexico y no del AST: el AST pierde palabras reservadas y el lexer aguanta codigo incompleto
public class HighlightService {

    // extensiones soportadas: .pig, .y, .z
    public boolean isSupported(String extension) {
        if (extension == null) return true;
        String ext = extension.toLowerCase(Locale.ROOT);
        return !ext.equals("pig") && !ext.equals("y") && !ext.equals("z");
    }

    // analiza un archivo y devuelve sus rangos (vacio si no aplica)
    public List<HighlightSpan> highlight(Path sourceFile) throws IOException {
        if (sourceFile == null) return List.of();

        String extension = getExtension(sourceFile.getFileName().toString());

        if (!isSupported(extension)) return List.of();

        String source = Files.readString(sourceFile, StandardCharsets.UTF_8);

        return highlight(source, extension);
    }

    // igual pero desde un string
    public List<HighlightSpan> highlight(String source, String extension) {
        if (source == null || extension == null) return List.of();

        return switch (extension.toLowerCase(Locale.ROOT)) {
            case "pig" -> collect(new PigLatinLexer(CharStreams.fromString(source)), HighlightService::classifyLat);
            case "y" -> collect(new YLexer(CharStreams.fromString(source)), HighlightService::classifyY);
            case "z" -> collect(new ZLexer(CharStreams.fromString(source)), HighlightService::classifyZ);
            default -> List.of();
        };
    }

    private List<HighlightSpan> collect(Lexer lexer, IntFunction<HighlightKind> classifier) {
        lexer.removeErrorListeners();

        List<HighlightSpan> spans = new ArrayList<>();

        for (Token token : lexer.getAllTokens()) {
            if (token.getType() == Token.EOF) continue;

            int start = token.getStartIndex();
            int stop = token.getStopIndex();

            if (start < 0 || stop < start) continue;

            HighlightKind kind = classifier.apply(token.getType());

            if (kind == null) continue;

            spans.add(new HighlightSpan(start, stop - start + 1, kind));
        }

        return spans;
    }

    private static HighlightKind classifyLat(int type) {
        return switch (type) {
            case PigLatinLexer.VARIABLES_SECTION, PigLatinLexer.FUNCTIONS_SECTION, PigLatinLexer.MAIN_SECTION,
                 PigLatinLexer.FINIS_PROGRAM, PigLatinLexer.FINIS, PigLatinLexer.ESTO, PigLatinLexer.SERIES,
                 PigLatinLexer.ACTIO, PigLatinLexer.RATIO, PigLatinLexer.SI, PigLatinLexer.ALITER,
                 PigLatinLexer.DUM, PigLatinLexer.FACERE, PigLatinLexer.PER, PigLatinLexer.REDDERE,
                 PigLatinLexer.PERGE, PigLatinLexer.INTERRUMPE, PigLatinLexer.VARIABILES,
                 PigLatinLexer.IMPORT, PigLatinLexer.NOVUS -> HighlightKind.KEYWORD;
            case PigLatinLexer.NUMERUS, PigLatinLexer.DECIMALIS, PigLatinLexer.TEXTUM,
                 PigLatinLexer.LITTERA, PigLatinLexer.BOOL -> HighlightKind.TYPE;
            case PigLatinLexer.VERUM, PigLatinLexer.FALSUS -> HighlightKind.BOOLEAN;
            case PigLatinLexer.NUMBER, PigLatinLexer.DECIMAL -> HighlightKind.NUMBER;
            case PigLatinLexer.STRING -> HighlightKind.STRING;
            case PigLatinLexer.CHAR -> HighlightKind.CHAR;
            case PigLatinLexer.ID -> HighlightKind.IDENTIFIER;
            case PigLatinLexer.LINE_COMMENT, PigLatinLexer.BLOCK_COMMENT -> HighlightKind.COMMENT;
            case PigLatinLexer.PLUS, PigLatinLexer.MINUS, PigLatinLexer.MULT, PigLatinLexer.DIV,
                 PigLatinLexer.PLUSPLUS, PigLatinLexer.MINUSMINUS, PigLatinLexer.AND, PigLatinLexer.OR,
                 PigLatinLexer.NON, PigLatinLexer.EQUAL, PigLatinLexer.NOT_EQUAL, PigLatinLexer.LESS,
                 PigLatinLexer.GREATER, PigLatinLexer.LESS_EQUAL, PigLatinLexer.GREATER_EQUAL,
                 PigLatinLexer.READ, PigLatinLexer.PRINT, PigLatinLexer.T__10 -> HighlightKind.OPERATOR;
            case PigLatinLexer.T__0, PigLatinLexer.T__1, PigLatinLexer.T__2, PigLatinLexer.T__3,
                 PigLatinLexer.T__4, PigLatinLexer.T__5, PigLatinLexer.T__6, PigLatinLexer.T__7,
                 PigLatinLexer.T__8, PigLatinLexer.T__9 -> HighlightKind.PUNCTUATION;
            default -> null;
        };
    }

    private static HighlightKind classifyY(int type) {
        return switch (type) {
            case YLexer.STRUCTURES, YLexer.FUNCTIONS, YLexer.STRUCTURE, YLexer.DEFINE, YLexer.IF,
                 YLexer.THEN, YLexer.ELSE, YLexer.OTHERWISE, YLexer.SWITCH, YLexer.CASE,
                 YLexer.ALWAYS, YLexer.FOR, YLexer.WHILE, YLexer.DO, YLexer.BREAK,
                 YLexer.CONTINUE, YLexer.RETURN, YLexer.PRINT, YLexer.READ -> HighlightKind.KEYWORD;
            case YLexer.INTEGER, YLexer.FLOAT, YLexer.CHARACTER, YLexer.BOOLEAN,
                 YLexer.STRING -> HighlightKind.TYPE;
            case YLexer.TRUE, YLexer.FALSE -> HighlightKind.BOOLEAN;
            case YLexer.INCREMENT, YLexer.DECREMENT, YLexer.PLUS, YLexer.MINUS, YLexer.MULT,
                 YLexer.DIV, YLexer.MOD, YLexer.AND, YLexer.OR, YLexer.NOT, YLexer.EQUAL,
                 YLexer.NOT_EQUAL, YLexer.LESS_EQUAL, YLexer.GREATER_EQUAL, YLexer.LESS,
                 YLexer.GREATER, YLexer.PLUS_ASSIGN, YLexer.MINUS_ASSIGN, YLexer.MULT_ASSIGN,
                 YLexer.ASSIGN, YLexer.ARROW -> HighlightKind.OPERATOR;
            case YLexer.LPAREN, YLexer.RPAREN, YLexer.LBRACK, YLexer.RBRACK, YLexer.LBRACE,
                 YLexer.RBRACE, YLexer.COMMA, YLexer.COLON, YLexer.SEMI, YLexer.DOT -> HighlightKind.PUNCTUATION;
            case YLexer.FLOAT_LITERAL, YLexer.INTEGER_LITERAL -> HighlightKind.NUMBER;
            case YLexer.CHAR_LITERAL -> HighlightKind.CHAR;
            case YLexer.STRING_LITERAL -> HighlightKind.STRING;
            case YLexer.ID -> HighlightKind.IDENTIFIER;
            case YLexer.LINE_COMMENT, YLexer.BLOCK_COMMENT -> HighlightKind.COMMENT;
            default -> null;
        };
    }

    private static HighlightKind classifyZ(int type) {
        return switch (type) {
            case ZLexer.PUBLIC, ZLexer.CLASS, ZLexer.VOID, ZLexer.RETURN, ZLexer.IF, ZLexer.ELSE,
                 ZLexer.SWITCH, ZLexer.CASE, ZLexer.DEFAULT, ZLexer.FOR, ZLexer.WHILE, ZLexer.DO,
                 ZLexer.BREAK, ZLexer.CONTINUE, ZLexer.PRINT, ZLexer.PRINTLN, ZLexer.READLN,
                 ZLexer.NEW, ZLexer.NULL -> HighlightKind.KEYWORD;
            case ZLexer.INT, ZLexer.DOUBLE, ZLexer.CHAR, ZLexer.BOOLEAN, ZLexer.STRING -> HighlightKind.TYPE;
            case ZLexer.TRUE, ZLexer.FALSE -> HighlightKind.BOOLEAN;
            case ZLexer.PLUS, ZLexer.MINUS, ZLexer.MULT, ZLexer.DIV, ZLexer.MOD, ZLexer.INCREMENT,
                 ZLexer.DECREMENT, ZLexer.AND, ZLexer.OR, ZLexer.NOT, ZLexer.EQUAL, ZLexer.NOT_EQUAL,
                 ZLexer.LESS, ZLexer.GREATER, ZLexer.LESS_EQUAL, ZLexer.GREATER_EQUAL, ZLexer.ASSIGN,
                 ZLexer.PLUS_ASSIGN, ZLexer.MINUS_ASSIGN, ZLexer.MULT_ASSIGN,
                 ZLexer.QUESTION -> HighlightKind.OPERATOR;
            case ZLexer.LPAREN, ZLexer.RPAREN, ZLexer.LBRACE, ZLexer.RBRACE, ZLexer.LBRACK,
                 ZLexer.RBRACK, ZLexer.DOT, ZLexer.COMMA, ZLexer.COLON, ZLexer.SEMI -> HighlightKind.PUNCTUATION;
            case ZLexer.FLOAT_LITERAL, ZLexer.INTEGER_LITERAL -> HighlightKind.NUMBER;
            case ZLexer.CHAR_LITERAL -> HighlightKind.CHAR;
            case ZLexer.STRING_LITERAL -> HighlightKind.STRING;
            case ZLexer.ID -> HighlightKind.IDENTIFIER;
            case ZLexer.LINE_COMMENT, ZLexer.BLOCK_COMMENT -> HighlightKind.COMMENT;
            default -> null;
        };
    }

    private String getExtension(String fileName) {
        int dot = fileName.lastIndexOf('.');
        if (dot < 0) return "";
        return fileName.substring(dot + 1);
    }
}
