package tetris;

import tetris.tetrominos.Square;

import java.awt.Color;

public class Settings {
   public final int SCREEN_WIDTH = 460;
   public final int SCREEN_HEIGHT = 590;
   public int delay = 480;

   public final Color BG_COLOR = new Color(63, 59, 108);
   public final Color PURPLE = new Color(159, 115, 171);
   public final Color GRAY = new Color(163, 161, 163);
   public final Color LIGHT_GRAY = new Color(231, 229, 231);
   public final Color BLUE = new Color(163, 199, 214);
   public final Color ORANGE = new Color(253, 132, 31);
   public final Color RED = new Color(225, 77, 42);
   public final Color GREEN = new Color(60, 207, 78);
   public final Color YELLOW = new Color(255, 234, 17);
   public final Color BEIGE = new Color(252, 248, 232);
   public final Color BROWN = new Color(162, 123, 92);

   public final int P = 10;
   public final int BOARD_WIDTH = 300;
   public final int BOARD_HEIGHT = 570;
   public final int squareSide = 30;

   private Square[][] boardSpaces;

   public Settings() {
      boardSpaces = new Square[BOARD_HEIGHT/squareSide][BOARD_WIDTH/squareSide];
   }

   public Square[][] getBoardSpaces() {
      return boardSpaces;
   }

   public void setBoardSpaces(Square[][] boardSpaces) {
      this.boardSpaces = boardSpaces;
   }
}
