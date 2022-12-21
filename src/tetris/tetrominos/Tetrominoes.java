package tetris.tetrominos;

import tetris.Settings;

import java.util.ArrayList;
import java.util.List;

public class Tetrominoes {
   int [][] I = new int[][] {{0, 0, 1, 0}, {0, 0, 1, 0}, {0, 0, 1, 0}, {0, 0, 1, 0}};
   int [][] J = new int[][] {{1, 0, 0}, {1, 1, 1}, {0, 0, 0}};
   int [][] L = new int[][] {{0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
   int [][] O = new int[][] {{1, 1}, {1, 1}};
   int [][] S = new int[][] {{0, 1, 1}, {1, 1, 0}, {0, 0, 0}};
   int [][] T = new int[][] {{0, 1, 0}, {1, 1, 1}, {0, 0, 0}};
   int [][] Z = new int[][] {{1, 1, 0}, {0, 1, 1}, {0, 0, 0}};

   public List<Tetromino> pieces;

   public Tetrominoes(Settings settings) {
      pieces = new ArrayList<>();
      pieces.add(new Tetromino(settings.BLUE, I));
      pieces.add(new Tetromino(settings.BEIGE, J));
      pieces.add(new Tetromino(settings.BROWN, L));
      pieces.add(new Tetromino(settings.YELLOW, O));
      pieces.add(new Tetromino(settings.GREEN, S));
      pieces.add(new Tetromino(settings.ORANGE, T));
      pieces.add(new Tetromino(settings.RED, Z));
   }
}
