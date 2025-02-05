//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package pieces;

import java.awt.image.BufferedImage;
import main.Board;

public class Knight extends Piece {
    public Knight(Board board, int col, int row, boolean isWhite) {
        super(board);
        this.col = col;
        this.row = row;
        this.xPos = col * board.hexSize;
        this.yPos = row * board.hexSize;
        this.isWhite = isWhite;
        this.name = "Knight";
        if (this.sheet != null) {
            BufferedImage subImage = this.sheet.getSubimage(3 * this.sheetScale, isWhite ? 0 : this.sheetScale, this.sheetScale, this.sheetScale);
            this.sprite = subImage.getScaledInstance(board.hexSize, board.hexSize, 4);
        }

    }
}
