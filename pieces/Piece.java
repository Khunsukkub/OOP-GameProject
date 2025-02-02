//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package pieces;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.Board;

public class Piece {
    public int col;
    public int row;
    public int xPos;
    public int yPos;
    public boolean isWhite;
    public String name;
    public int value;
    BufferedImage sheet;
    protected int sheetScale;
    Image sprite;
    Board board;

    public Piece(Board board) {
        try {
            this.sheet = ImageIO.read(new File("C:\\123\\TestGame2\\src\\rec\\pieces.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.sheetScale = this.sheet != null ? this.sheet.getWidth() / 6 : 0;
        this.board = board;
    }

    public void paint(Graphics g) {
        if (this.sprite != null) {
            g.drawImage(this.sprite, this.xPos, this.yPos, (ImageObserver)null);
        }

    }
}
