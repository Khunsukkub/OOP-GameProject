package model;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class MinionStrategyParser {
    private final List<String> tokens;
    private int index = 0;

    public MinionStrategyParser(String input) {
        this.tokens = tokenize(input);
    }

    private List<String> tokenize(String input) {
        return Arrays.asList(input.replaceAll("\\n", " ").split("\\s+"));
    }

    public void parseStrategy() {
        while (index < tokens.size()) {
            parseStatement();
        }
    }

    private void parseStatement() {
        String token = peek();
        if (token.equals("if")) {
            parseIfStatement();
        } else if (token.equals("while")) {
            parseWhileStatement();
        } else if (token.equals("{")) {
            parseBlockStatement();
        } else if (token.equals("done") || token.equals("move") || token.equals("shoot")) {
            parseActionCommand();
        } else {
            parseAssignmentStatement();
        }
    }

    private void parseAssignmentStatement() {
        expectIdentifier();
        expect("=");
        parseExpression();
    }

    private void parseActionCommand() {
        String command = consume();
        if (command.equals("move")) {
            parseDirection();
        } else if (command.equals("shoot")) {
            parseDirection();
            parseExpression();
        }
    }

    private void parseBlockStatement() {
        expect("{");
        while (!peek().equals("}")) {
            parseStatement();
        }
        expect("}");
    }

    private void parseIfStatement() {
        expect("if");
        expect("(");
        parseExpression();
        expect(")");
        expect("then");
        parseStatement();
        expect("else");
        parseStatement();
    }

    private void parseWhileStatement() {
        expect("while");
        expect("(");
        parseExpression();
        expect(")");
        parseStatement();
    }

    private void parseExpression() {
        parseTerm();
        while (peek().equals("+") || peek().equals("-")) {
            consume();
            parseTerm();
        }
    }

    private void parseTerm() {
        parseFactor();
        while (peek().equals("*") || peek().equals("/") || peek().equals("%")) {
            consume();
            parseFactor();
        }
    }

    private void parseFactor() {
        parsePower();
        if (peek().equals("^")) {
            consume();
            parseFactor();
        }
    }

    private void parsePower() {
        if (isNumber(peek()) || isIdentifier(peek())) {
            consume();
        } else if (peek().equals("(") ) {
            consume();
            parseExpression();
            expect(")");
        } else {
            parseInfoExpression();
        }
    }

    private void parseInfoExpression() {
        if (peek().equals("ally") || peek().equals("opponent")) {
            consume();
        } else if (peek().equals("nearby")) {
            consume();
            parseDirection();
        }
    }

    private void parseDirection() {
        Set<String> directions = Set.of("up", "down", "upleft", "upright", "downleft", "downright");
        if (directions.contains(peek())) {
            consume();
        } else {
            throw new RuntimeException("Expected direction but found: " + peek());
        }
    }

    private String peek() {
        return index < tokens.size() ? tokens.get(index) : "";
    }

    private String consume() {
        return tokens.get(index++);
    }

    private void expect(String expected) {
        if (!consume().equals(expected)) {
            throw new RuntimeException("Expected " + expected);
        }
    }

    private void expectIdentifier() {
        if (!isIdentifier(peek())) {
            throw new RuntimeException("Expected identifier but found: " + peek());
        }
        consume();
    }

    private boolean isIdentifier(String token) {
        return token.matches("[a-zA-Z][a-zA-Z0-9]*") && !Set.of("ally", "done", "down", "downleft", "downright", "else", "if", "move", "nearby", "opponent", "shoot", "then", "up", "upleft", "upright", "while").contains(token);
    }

    private boolean isNumber(String token) {
        return token.matches("\\d+");
    }
}
