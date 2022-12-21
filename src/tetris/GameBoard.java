package tetris;

import tetris.tetrominos.Square;
import tetris.tetrominos.Tetromino;
import tetris.tetrominos.Tetrominoes;

import javax.swing.Timer;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;

public class GameBoard extends JPanel implements ActionListener {

   private Timer timer;
   private final Settings stgs;
   private int score;
   private boolean running;
   private final Tetrominoes tetrominoes;
   private Tetromino currentTetromino;
   private int currentTetrominoIndex = 0;
   private Keys direction;
   private final int[] tetrominoesIndexes;

   public GameBoard() {
      stgs = new Settings();

      this.setPreferredSize(new Dimension(stgs.SCREEN_WIDTH, stgs.SCREEN_HEIGHT));
      this.setBackground(stgs.BG_COLOR);
      this.setFocusable(true);
      this.addKeyListener(new MyKeyAdapter());

      tetrominoes = new Tetrominoes(stgs);
      tetrominoesIndexes = new int[]{0, 1, 2, 3, 4, 5};
      startGame();
   }

   private void startGame() {
      score = 0;
      running = true;
      timer = new Timer(stgs.delay, this);
      currentTetrominoIndex = tetrominoesIndexes[0];
      currentTetromino = new Tetromino(tetrominoes.pieces.get(currentTetrominoIndex).getBgColor(),
              tetrominoes.pieces.get(currentTetrominoIndex).getCoordinates());
      currentTetromino.createSquares(stgs.BOARD_WIDTH / 2, 0, stgs.squareSide);
      timer.start();
   }

   private void mixTetrominoesIndexesList() {
      for (int i = 0; i < tetrominoesIndexes.length; i++) {
         int random = new Random().nextInt(tetrominoesIndexes.length);
         int aux = tetrominoesIndexes[random];
         tetrominoesIndexes[random] = tetrominoesIndexes[i];
         tetrominoesIndexes[i] = aux;
      }
   }

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      drawBoard(g);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      moveTetrominoDown();
      repaint();
   }

   private void moveTetrominoHorizontally() {
      if (direction == Keys.LEFT && isSaveMoveLeft()) {
         currentTetromino.getSquares().forEach((square) -> {
            square.setXPosition(square.getXPosition() - stgs.squareSide);
         });
      } else if (direction == Keys.RIGHT && isSaveMoveRight()) {
         currentTetromino.getSquares().forEach((square) -> {
            square.setXPosition(square.getXPosition() + stgs.squareSide);
         });
      }
   }

   private boolean isSaveMoveRight() {
      for (Square square: currentTetromino.getSquares()) {
         int yPos = (square.getYPosition() - 10) / 30;
         int xPos = (square.getXPosition() - 10) / 30;

         if (yPos < 0 || xPos >= stgs.getBoardSpaces()[0].length - 1 || stgs.getBoardSpaces()[yPos][xPos + 1] != null) {
            return false;
         }
      }
      return true;
   }

   private boolean isSaveMoveLeft() {
      for (Square square: currentTetromino.getSquares()) {
         int yPos = (square.getYPosition() - 10) / 30;
         int xPos = (square.getXPosition() - 10) / 30;

         if (yPos < 0 || xPos <= 0 || stgs.getBoardSpaces()[yPos][xPos - 1] != null) {
            return false;
         }
      }
      return true;
   }

   private void drawBoard(Graphics g) {

      drawTetromino(g, currentTetromino.getSquares());
      drawBoardTetrominoes(g);

      // <editor-fold default-state="collapsed" desc="Board borders and lines">//GEN-BEGIN:boardLines
      g.setColor(stgs.PURPLE);
      g.drawRect(stgs.P, stgs.P, stgs.BOARD_WIDTH, stgs.BOARD_HEIGHT);
      g.drawRect(stgs.P - 1, stgs.P - 1, stgs.BOARD_WIDTH + 2, stgs.BOARD_HEIGHT + 2);
      // Score and next move boxes
      g.drawRect(320, stgs.P, 130, 360);
      g.drawRect(320, 380, 130, 200);

      g.setColor(stgs.GRAY);
      for(int i = stgs.P + 30; i < (stgs.BOARD_WIDTH + stgs.P); i += 30) {
         g.drawLine(i, stgs.P, i, stgs.P + stgs.BOARD_HEIGHT);
      }
      for(int i = stgs.P + 30; i < (stgs.BOARD_HEIGHT + stgs.P); i += 30) {
         g.drawLine(stgs.P, i, stgs.P + stgs.BOARD_WIDTH, i);
      }
      // </editor-fold>//GEN-END:boardLines

      drawScore(g);
      drawNextTetrominoes(g);

      if (!running) {
         g.setColor(new Color(250, 230, 240));
         g.setFont(new Font("Candara", Font.BOLD, 40));
         g.drawString("Game Over!", stgs.BOARD_WIDTH / 2 - 64, stgs.BOARD_HEIGHT / 2 + 10);
      }
   }

   private void drawNextTetrominoes(Graphics g) {
      // g.drawRect(320, stgs.P, 130, 360);
      if (currentTetrominoIndex < tetrominoesIndexes.length - 1) {
         Tetromino tetromino = tetrominoes.pieces.get(tetrominoesIndexes[currentTetrominoIndex + 1]);
         tetromino.createSquares(380, 50, 20);

         g.setColor(tetromino.getBgColor());

         tetromino.getSquares().forEach((square) -> {
            g.fillRect(square.getXPosition(), square.getYPosition(), square.getSide(), square.getSide());
         });
      }
   }

   private void drawScore(Graphics g) {
      g.setColor(stgs.LIGHT_GRAY);
      g.setFont(new Font("Candara", Font.PLAIN, 30));
      g.drawRect(320, 380, 130, 200);
      g.drawString("Score: ", 320 + stgs.P, stgs.P + 450 + 15);
      g.setFont(new Font("Candara", Font.PLAIN, 26));
      g.drawString(String.valueOf(score), 325 + stgs.P, stgs.P + 490 + 13);
   }

   private void drawTetromino(Graphics g, List<Square> tetrominoSquares) {
      g.setColor(tetrominoSquares.get(0).getBgColor());
      for (Square square : tetrominoSquares) {
         if (square.getYPosition() > 0) {
            g.fillRect(square.getXPosition(), square.getYPosition(), square.getSide(), square.getSide());
         }
      }
   }

   private void drawBoardTetrominoes(Graphics g) {
      for (Square[] row: stgs.getBoardSpaces()) {
         for (Square square: row) {
            if (square != null) {
               g.setColor(square.getBgColor());
               g.fillRect(square.getXPosition(), square.getYPosition(), square.getSide(), square.getSide());
            }
         }
      }
   }

   private void moveTetrominoDown() {
      boolean isSafeMove = isSafeMoveSquaresDown(currentTetromino.getSquares());

      if (isSafeMove) {
         currentTetromino.getSquares().forEach((square -> {
            square.setYPosition(square.getYPosition() + square.getVelocity());
         }));
      } else {
         if (currentTetromino.getSquares().get(0).getYPosition() <= 10) {
            running = false;
            timer.stop();
         } else {
            for (Square square : currentTetromino.getSquares()) {
               int yPos = (square.getYPosition() - 10) / 30;
               int xPos = (square.getXPosition() - 10) / 30;
               if (yPos > 0) {
                  stgs.getBoardSpaces()[yPos][xPos] = square;
               }
            }

            checkCompleteRows();

            currentTetrominoIndex += 1;
            if (currentTetrominoIndex > tetrominoesIndexes.length - 1) {
               mixTetrominoesIndexesList();
               currentTetrominoIndex = 0;
            }
            currentTetromino.setBgColor(tetrominoes.pieces.get(tetrominoesIndexes[currentTetrominoIndex]).getBgColor());
            currentTetromino.setCoordinates(tetrominoes.pieces.get(tetrominoesIndexes[currentTetrominoIndex]).getCoordinates());
            currentTetromino.createSquares(stgs.BOARD_WIDTH / 2, 0, stgs.squareSide);
         }
      }
   }

   public boolean isSafeMoveSquaresDown(List<Square> squares) {
      if (squares.size() == 0) return false;

      for (Square square: squares){
         int yPos = (square.getYPosition() - 10) / 30;
         int xPos = (square.getXPosition() - 10) / 30;

         if (yPos >= stgs.getBoardSpaces().length - 1 || stgs.getBoardSpaces()[yPos + 1][xPos] != null){
            return false;
         }
      }
      return true;
   }

   private void checkCompleteRows() {
      int completedRows = 0;
      List<Integer> completedRowsPosition = new ArrayList<>();

      for (int i = 0; i < stgs.getBoardSpaces().length; i++) {
         boolean isComplete = true;
         for (int j = 0; j < stgs.getBoardSpaces()[i].length; j++) {
            if (stgs.getBoardSpaces()[i][j] == null) {
               isComplete = false;
               break;
            }
         }

         if (isComplete) {
            completedRows += 1;
            completedRowsPosition.add(i);
            score += 100;
         }
      }

      completedRowsPosition.forEach((yPosition) -> {
         Arrays.fill(stgs.getBoardSpaces()[yPosition], null);
      });

      if (completedRows > 0) {
         for (int i = stgs.getBoardSpaces().length - 1; i >= 0; i--) {
            int tempI = i;
            List<Square> squares = new ArrayList<>();

            for (int j = 0; j < stgs.getBoardSpaces()[i].length; j++) {
               if (stgs.getBoardSpaces()[i][j] != null) {
                  squares.add(stgs.getBoardSpaces()[i][j]);
               }
            }

            boolean isSafeMove = isSafeMoveSquaresDown(squares);
            while (isSafeMove && tempI < stgs.getBoardSpaces().length - 1) {
               for (int j = 0; j < stgs.getBoardSpaces()[tempI + 1].length; j++) {
                  stgs.getBoardSpaces()[tempI + 1][j] = stgs.getBoardSpaces()[tempI][j];
                  if (stgs.getBoardSpaces()[tempI + 1][j] != null) {
                     stgs.getBoardSpaces()[tempI + 1][j].setYPosition(
                             stgs.getBoardSpaces()[tempI + 1][j].getYPosition()
                                     + stgs.getBoardSpaces()[tempI + 1][j].getVelocity()
                     );
                  }
               }
               Arrays.fill(stgs.getBoardSpaces()[tempI], null);

               tempI += 1;
               squares.clear();
               for (int j = 0; j < stgs.getBoardSpaces()[tempI].length; j++) {
                  if (stgs.getBoardSpaces()[tempI][j] != null) {
                     squares.add(stgs.getBoardSpaces()[tempI][j]);
                  }
               }
               isSafeMove = isSafeMoveSquaresDown(squares);
            }
         }
      }

      if (completedRows >= 4) {
         score += 400;
      }
   }

   private void rotateTetrominoRight() {
      int middleXPos = currentTetromino.getSquares()
              .get(currentTetromino.getSquares().size() / 2).getXPosition() - 10;
      int topYPos = currentTetromino.getSquares()
              .get(currentTetromino.getSquares().size() / 2).getYPosition() - 10;

      if (isSafeRotateTetromino(middleXPos, topYPos, currentTetromino.getCoordinates().length)) {
         int l = currentTetromino.getCoordinates().length;
         int[][] coordinates = new int[l][l];

         for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
               coordinates[i][j] = currentTetromino.getCoordinates()[i][j];
            }
         }

         for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
               currentTetromino.getCoordinates()[i][j] = coordinates[l - 1 - j][i];
            }
         }

         currentTetromino.createSquares(middleXPos, topYPos, stgs.squareSide);
      }
   }

   private boolean isSafeRotateTetromino(int xPos, int yPos, int length) {
      int xIndex = xPos / 30;
      int yIndex = yPos / 30;

      for (int i = yIndex; i < length + yIndex; i++) {
         for (int j = xIndex - 1; j < length + xIndex; j++) {
            if (
                    i > 0 && i < stgs.getBoardSpaces().length && j > 0  && j < stgs.getBoardSpaces()[0].length &&
                    stgs.getBoardSpaces()[i][j] != null
            ) {
               return false;
            }

            if (length + yIndex > stgs.getBoardSpaces().length && length + xIndex > stgs.getBoardSpaces()[0].length) {
               return false;
            }
         }
      }

      return true;
   }

   private class MyKeyAdapter extends KeyAdapter {
      @Override
      public void keyPressed(KeyEvent e) {
         switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> {
               direction = Keys.LEFT;
               moveTetrominoHorizontally();
            }
            case KeyEvent.VK_RIGHT -> {
               direction = Keys.RIGHT;
               moveTetrominoHorizontally();
            }
            case KeyEvent.VK_UP -> rotateTetrominoRight();
            case KeyEvent.VK_DOWN -> {
               moveTetrominoDown();
            }
            case KeyEvent.VK_SPACE -> direction = Keys.SPACE;
            case KeyEvent.VK_Z -> direction = Keys.Z;
            case KeyEvent.VK_C -> direction = Keys.C;
         }
         repaint();
      }

      @Override
      public void keyReleased(KeyEvent e) {
         direction = Keys.NONE;
      }
   }
}
