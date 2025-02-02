//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import pieces.Knight;
import pieces.Piece;

public class Board extends JPanel {
    public int hexSize = 40;
    private int cols = 8;
    private final int rows = 8;
    ArrayList<Piece> pieceList = new ArrayList();

    public Board() {
        int width = (int)(((double)this.cols + (double)0.5F) * Math.sqrt((double)3.0F) * (double)this.hexSize);
        int height = (int)((double)8.5F * (double)1.5F * (double)this.hexSize);
        this.setPreferredSize(new Dimension(width, height));
        this.addPieces();
    }

    public void addPieces() {
        this.pieceList.add(new Knight(this, 2, 0, false));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        double w = Math.sqrt((double)3.0F) * (double)this.hexSize;
        double h = (double)(2 * this.hexSize);
        double dx = w;
        double dy = (double)1.5F * (double)this.hexSize;

        for(int r = 0; r < 8; ++r) {
            for(int c = 0; c < this.cols; ++c) {
                double x = (double)c * dx + (r % 2 == 0 ? (double)0.0F : dx / (double)2.0F);
                double y = (double)r * dy;
                int[] px = new int[]{(int)(x + w / (double)2.0F), (int)(x + w), (int)(x + w), (int)(x + w / (double)2.0F), (int)x, (int)x};
                int[] py = new int[]{(int)y, (int)(y + h / (double)4.0F), (int)(y + (double)3.0F * h / (double)4.0F), (int)(y + h), (int)(y + (double)3.0F * h / (double)4.0F), (int)(y + h / (double)4.0F)};
                Polygon hexagon = new Polygon(px, py, 6);
                g2d.setColor((c + r) % 2 == 0 ? new Color(220, 208, 95) : new Color(154, 122, 100));
                g2d.fillPolygon(hexagon);
                g2d.setColor(Color.BLACK);
                g2d.drawPolygon(hexagon);
            }
        }

        for(Piece piece : this.pieceList) {
            piece.paint(g2d);
        }

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Hexagonal Board");
        frame.setDefaultCloseOperation(3);
        frame.getContentPane().add(new Board());
        frame.pack();
        frame.setLocationRelativeTo((Component)null);
        frame.setVisible(true);
    }
}
