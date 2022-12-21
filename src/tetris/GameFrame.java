package tetris;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

   public GameFrame() {
      this.add(new GameBoard());
      this.setTitle("Tetris 2022");
      this.setDefaultCloseOperation(EXIT_ON_CLOSE);
      this.setResizable(false);
      this.pack();
      this.setVisible(true);
      this.setLocationRelativeTo(null);
   }
}
