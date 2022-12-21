package tetris.tetrominos;

import java.awt.Color;

public class Square {
   Color bgColor;
   private int velocity;
   private int side;
   private int xPosition;
   private int yPosition;

   public Square(Color bgColor, int velocity, int side, int xPosition, int yPosition) {
      this.bgColor = bgColor;
      this.velocity = velocity;
      this.xPosition = xPosition;
      this.yPosition = yPosition;
      this.side = side;
   }

   public Color getBgColor() {
      return bgColor;
   }

   public void setBgColor(Color bgColor) {
      this.bgColor = bgColor;
   }

   public int getVelocity() {
      return velocity;
   }

   public void setVelocity(int velocity) {
      this.velocity = velocity;
   }

   public int getSide() {
      return side;
   }

   public void setSide(int side) {
      this.side = side;
   }

   public int getXPosition() {
      return xPosition;
   }

   public void setXPosition(int xPosition) {
      this.xPosition = xPosition;
   }

   public int getYPosition() {
      return yPosition;
   }

   public void setYPosition(int yPosition) {
      this.yPosition = yPosition;
   }

   @Override
   public String toString() {
      return "Square{" +
              "bgColor=" + bgColor +
              ", velocity=" + velocity +
              ", side=" + side +
              ", x=" + xPosition +
              ", y=" + yPosition +
              '}';
   }
}
