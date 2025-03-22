package prasing;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

class ASTNode {
    String type;  // ชนิดของ node เช่น "IfStatement", "Expression", "Assignment"
    List<ASTNode> children;  // ลูกของ node นี้
    String value; // ค่าของ node ถ้ามี เช่น ตัวเลข หรือ ชื่อของตัวแปร

    public ASTNode(String type) {
        this.type = type;
        this.children = new ArrayList<>();
    }

    public ASTNode(String type, String value) {
        this.type = type;
        this.value = value;
        this.children = new ArrayList<>();
    }

    public void addChild(ASTNode child) {
        children.add(child);
    }
}

public class MinionStrategyParser {
    private final List<String> tokens;
    private int index = 0;

    public MinionStrategyParser(String input) {
        this.tokens = tokenize(input);
    }

    private List<String> tokenize(String input) {
        return Arrays.asList(input.replaceAll("\\n", " ").split("\\s+"));
    }

    public ASTNode parseStrategy() {
        ASTNode rootNode = new ASTNode("Strategy");
        while (index < tokens.size()) {
            ASTNode statementNode = parseStatement();
            rootNode.addChild(statementNode);
        }
        return rootNode;
    }

    private ASTNode parseStatement() {
        String token = peek();
        if (token.equals("if")) {
            return parseIfStatement();
        } else if (token.equals("while")) {
            return parseWhileStatement();
        } else if (token.equals("{")) {
            return parseBlockStatement();
        } else if (token.equals("done") || token.equals("move") || token.equals("shoot")) {
            return parseActionCommand();
        } else {
            return parseAssignmentStatement();
        }
    }

    private ASTNode parseAssignmentStatement() {
        ASTNode node = new ASTNode("AssignmentStatement");
        expectIdentifier();
        String variable = consume();
        expect("=");
        ASTNode expressionNode = parseExpression();
        ASTNode assignmentNode = new ASTNode("Assignment", variable);
        assignmentNode.addChild(expressionNode);
        return assignmentNode;
    }

    private ASTNode parseActionCommand() {
        String command = consume();
        ASTNode node = new ASTNode(command + "Command");
        if (command.equals("move")) {
            node.addChild(parseDirection());
        } else if (command.equals("shoot")) {
            node.addChild(parseDirection());
            node.addChild(parseExpression());
        }
        return node;
    }

    private ASTNode parseBlockStatement() {
        ASTNode node = new ASTNode("BlockStatement");
        expect("{");
        while (!peek().equals("}")) {
            ASTNode statementNode = parseStatement();
            node.addChild(statementNode);
        }
        expect("}");
        return node;
    }

    private ASTNode parseIfStatement() {
        ASTNode node = new ASTNode("IfStatement");
        expect("if");
        expect("(");
        node.addChild(parseExpression());
        expect(")");
        expect("then");
        node.addChild(parseStatement());
        expect("else");
        node.addChild(parseStatement());
        return node;
    }

    private ASTNode parseWhileStatement() {
        ASTNode node = new ASTNode("WhileStatement");
        expect("while");
        expect("(");
        node.addChild(parseExpression());
        expect(")");
        node.addChild(parseStatement());
        return node;
    }

    private ASTNode parseExpression() {
        ASTNode node = parseTerm();
        while (peek().equals("+") || peek().equals("-")) {
            String operator = consume();
            ASTNode termNode = parseTerm();
            ASTNode operatorNode = new ASTNode("Operator", operator);
            operatorNode.addChild(node);
            operatorNode.addChild(termNode);
            node = operatorNode;
        }
        return node;
    }

    private ASTNode parseTerm() {
        ASTNode node = parseFactor();
        while (peek().equals("*") || peek().equals("/") || peek().equals("%")) {
            String operator = consume();
            ASTNode factorNode = parseFactor();
            ASTNode operatorNode = new ASTNode("Operator", operator);
            operatorNode.addChild(node);
            operatorNode.addChild(factorNode);
            node = operatorNode;
        }
        return node;
    }

    private ASTNode parseFactor() {
        ASTNode node = parsePower();
        if (peek().equals("^")) {
            String operator = consume();
            ASTNode factorNode = parseFactor();
            ASTNode operatorNode = new ASTNode("Operator", operator);
            operatorNode.addChild(node);
            operatorNode.addChild(factorNode);
            node = operatorNode;
        }
        return node;
    }

    private ASTNode parsePower() {
        ASTNode node = null;
        if (isNumber(peek()) || isIdentifier(peek())) {
            node = new ASTNode("Power", consume());
        } else if (peek().equals("(")) {
            consume();
            node = parseExpression();
            expect(")");
        } else {
            node = parseInfoExpression();
        }
        return node;
    }

    private ASTNode parseInfoExpression() {
        ASTNode node = null;
        if (peek().equals("ally") || peek().equals("opponent")) {
            node = new ASTNode("InfoExpression", consume());
        } else if (peek().equals("nearby")) {
            node = new ASTNode("InfoExpression", "nearby");
            consume();
            node.addChild(parseDirection());
        }
        return node;
    }

    private ASTNode parseDirection() {
        Set<String> directions = Set.of("up", "down", "upleft", "upright", "downleft", "downright");
        if (directions.contains(peek())) {
            String direction = consume();
            return new ASTNode("Direction", direction);
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
