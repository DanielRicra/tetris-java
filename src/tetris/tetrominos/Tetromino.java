package tetris.tetrominos;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Tetromino {

   private int[][] coordinates;
   private Color bgColor;
   private List<Square> squares;

   public Tetromino(Color bgColor, int[][] coordinates) {
      this.coordinates = coordinates;
      this.bgColor = bgColor;
      this.squares = new ArrayList<>();
   }

   public int[][] getCoordinates() {
      return coordinates;
   }

   public void setCoordinates(int[][] coordinates) {
      this.coordinates = coordinates;
   }

   public Color getBgColor() {
      return bgColor;
   }

   public void setBgColor(Color bgColor) {
      this.bgColor = bgColor;
   }

   public List<Square> getSquares() {
      return squares;
   }

   public void setSquares(List<Square> squares) {
      this.squares = squares;
   }

   public void createSquares(int xPosition, int yPosition, int side) {
      squares.clear();
      for (int i = 0; i < this.coordinates.length; i++){
         for (int j = 0; j < this.coordinates[i].length; j++) {
            if (this.coordinates[i][j] == 1) {
               Square square = new Square(
                       this.bgColor,
                       30,
                       side,
                       10 + xPosition + (side * j) - (side * (this.coordinates[i].length / 2)),
                       (side * i) + (10 - side) + yPosition
               );
               squares.add(square);
            }
         }
      }
   }
}
