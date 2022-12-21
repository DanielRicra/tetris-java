package tetris.models;

import tetris.tetrominos.Square;

public class Cell {
   private Square square;
   private boolean isFilled;

   public Cell() {
      this.square = null;
      this.isFilled = false;
   }

   public Cell(Square square, boolean isFilled) {
      this.square = square;
      this.isFilled = isFilled;
   }

   public Square getSquare() {
      return square;
   }

   public void setSquare(Square square) {
      this.square = square;
   }

   public boolean isFilled() {
      return isFilled;
   }

   public void setFilled(boolean filled) {
      isFilled = filled;
   }
}
