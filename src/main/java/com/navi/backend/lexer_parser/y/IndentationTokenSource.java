package com.navi.backend.lexer_parser.y;

import lombok.Setter;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenSource;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.function.BiConsumer;

public class IndentationTokenSource implements TokenSource {

    private final TokenSource source;
    private final Queue<Token> pendingTokens = new LinkedList<>();
    private final Stack<Integer> indentStack = new Stack<>();
    private Token currentToken;
    private boolean atLineStart = true;
    private boolean eofProcessed = false;
    private static final int TAB_WIDTH = 4;

    @Setter
    private BiConsumer<Integer, String> indentationErrorListener;

    public IndentationTokenSource(TokenSource source) {
        this.source = source;
        indentStack.push(0);
        currentToken = source.nextToken();
    }

    @Override
    public Token nextToken() {
        if (!pendingTokens.isEmpty()) {
            return pendingTokens.poll();
        }

        if (eofProcessed) {
            return currentToken;
        }

        if (atLineStart) {
            processLineStart();
            if (!pendingTokens.isEmpty()) {
                return pendingTokens.poll();
            }
            if (currentToken.getType() == Token.EOF) {
                return currentToken;
            }
        }

        Token token = currentToken;
        currentToken = source.nextToken();

        if (token.getType() == YLexer.NEWLINE) {
            atLineStart = true;
            return token;
        }

        if (token.getType() == YLexer.WS) {
            return nextToken();
        }

        return token;
    }

    private void processLineStart() {
        int indentation = 0;

        while (currentToken.getType() == YLexer.WS) {
            indentation += calculateIndentation(currentToken.getText());
            currentToken = source.nextToken();
        }

        if (currentToken.getType() == YLexer.NEWLINE) {
            currentToken = source.nextToken();
            atLineStart = true;
            processLineStart();
            return;
        }

        if (currentToken.getType() == YLexer.LINE_COMMENT) {
            currentToken = source.nextToken();
            if (currentToken.getType() == YLexer.NEWLINE) {
                currentToken = source.nextToken();
            }
            atLineStart = true;
            processLineStart();
            return;
        }

        if (currentToken.getType() == YLexer.BLOCK_COMMENT) {
            currentToken = source.nextToken();
            atLineStart = true;
            processLineStart();
            return;
        }

        if (currentToken.getType() == Token.EOF) {
            closeIndentation();
            eofProcessed = true;
            return;
        }

        processIndentation(indentation);
        atLineStart = false;
    }

    private int calculateIndentation(String text) {
        int result = 0;
        for (char c : text.toCharArray()) {
            if (c == ' ') {
                result++;
            } else if (c == '\t') {
                result += TAB_WIDTH;
            }
        }
        return result;
    }

    private void processIndentation(int currentIndent) {
        int previousIndent = indentStack.peek();
        if (currentIndent == previousIndent) return;

        if (currentIndent > previousIndent) {
            indentStack.push(currentIndent);
            pendingTokens.add(createVirtualToken(YLexer.INDENT));
            return;
        }

        while (indentStack.size() > 1 && currentIndent < indentStack.peek()) {
            indentStack.pop();
            pendingTokens.add(createVirtualToken(YLexer.DEDENT));
        }

        if (currentIndent != indentStack.peek()) {
            reportIndentationError("indent error: " + currentIndent + " spaces.");
        }
    }

    private void closeIndentation() {
        while (indentStack.size() > 1) {
            indentStack.pop();
            pendingTokens.add(createVirtualToken(YLexer.DEDENT));
        }
    }

    private Token createVirtualToken(int type) {
        String text;
        if (type == YLexer.INDENT) text = "<INDENT>";
        else if (type == YLexer.DEDENT) text = "<DEDENT>";
        else text = "<VIRTUAL>";

        org.antlr.v4.runtime.CommonToken token = new org.antlr.v4.runtime.CommonToken(type, text);
        token.setLine(source.getLine());
        token.setCharPositionInLine(source.getCharPositionInLine());
        return token;
    }

    @Override
    public int getLine() {
        return source.getLine();
    }

    @Override
    public int getCharPositionInLine() {
        return source.getCharPositionInLine();
    }

    @Override
    public CharStream getInputStream() {
        return source.getInputStream();
    }

    @Override
    public String getSourceName() {
        return source.getSourceName();
    }

    @Override
    public org.antlr.v4.runtime.TokenFactory<?> getTokenFactory() {
        return source.getTokenFactory();
    }

    @Override
    public void setTokenFactory(org.antlr.v4.runtime.TokenFactory<?> factory) {
        source.setTokenFactory(factory);
    }

    private void reportIndentationError(String message) {
        if (indentationErrorListener != null) {
            indentationErrorListener.accept(currentToken.getLine(), message);
        }
    }
}
