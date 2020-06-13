package com.codegym.games.game2048;

import com.codegym.engine.cell.*;

public class Game2048 extends Game {

  private static final int SIDE = 4;
  private int[][] gameField = new int[SIDE][SIDE];
  private boolean isGameStopped = false;
  private int score = 0;

  @Override
  public void initialize() {
    setScreenSize(SIDE, SIDE);
    createGame();
    drawScene();
  }

  private void createGame() {
    gameField = new int[SIDE][SIDE];
    createNewNumber();
    createNewNumber();

  }

  private void drawScene() {
    for (int row = 0; row < gameField.length; row++) {
      for (int col = 0; col < gameField[row].length; col++) {
        setCellColoredNumber(row, col, gameField[col][row]);
      }
    }
  }

  private void createNewNumber() {
    if (getMaxTileValue() == 2048) {
      win();
    }

    int x = getRandomNumber(SIDE);
    int y = getRandomNumber(SIDE);

    while (gameField[x][y] != 0) {
      x = getRandomNumber(SIDE);
      y = getRandomNumber(SIDE);
    }

    if (getRandomNumber(10) == 9) {
      gameField[x][y] = 4;

    } else {
      gameField[x][y] = 2;
    }

  }

  private Color getColorByValue(int val) {

    switch (val) {
      case 0:
        return Color.ANTIQUEWHITE;
      case 2:
        return Color.LIGHTPINK;
      case 4:
        return Color.PURPLE;
      case 8:
        return Color.BLUE;
      case 16:
        return Color.LIGHTSEAGREEN;
      case 32:
        return Color.GREEN;
      case 64:
        return Color.LIGHTGREEN;
      case 128:
        return Color.ORANGE;
      case 256:
        return Color.CORAL;
      case 512:
        return Color.RED;
      case 1024:
        return Color.PINK;
      case 2048:
        return Color.DEEPPINK;
      default:
        return Color.ANTIQUEWHITE;
    }
  }

  private void setCellColoredNumber(int x, int y, int val) {
    Color valColor = getColorByValue(val);
    String displayString;

    if (val == 0) {
      displayString = "";
    } else {
      displayString = Integer.toString(val);
    }
    setCellValueEx(x, y, valColor, displayString);
  }

  private boolean compressRow(int[] row) {

    boolean moved = false;
    int i;
    int j;

    for (i = 0, j = 0; j < row.length; j++) {
      if (row[j] != 0) {
        if (i < j) {
          int tmp = row[i];
          row[i] = row[j];
          row[j] = tmp;
          moved = true;
        }
        i++;
      }
    }
    return moved;
  }

  private boolean mergeRow(int[] row) {
    boolean merged = false;
    for (int i = 0; i < row.length - 1; i++) {
      if (row[i] == row[i + 1] && row[i] != 0) {
        row[i] += row[i + 1];
        row[i + 1] = 0;
        score += row[i];
        setScore(score);
        merged = true;
      }
    }
    return merged;
  }

  @Override
  public void onKeyPress(Key key) {
    if (!canUserMove()) {
      gameOver();
    } else {
      if (isGameStopped && key == Key.SPACE) {
        isGameStopped = false;
        score = 0;
        setScore(score);
        createGame();
        drawScene();
      } else if (!isGameStopped && key == Key.LEFT) {
        moveLeft();
        drawScene();
      } else if (!isGameStopped && key == Key.RIGHT) {
        moveRight();
        drawScene();
      } else if (!isGameStopped && key == Key.UP) {
        moveUp();
        drawScene();
      } else if (!isGameStopped && key == Key.DOWN) {
        moveDown();
        drawScene();
      }
    }
  }

  private void moveLeft() {

    boolean changed = false;

    for (int[] row : gameField) {

      boolean compressed = compressRow(row);
      boolean merged = mergeRow(row);
      compressRow(row);

      if ((compressed || merged) && !changed) {
        createNewNumber();
        changed = true;
      }

    }
  }

  private void moveRight() {
    rotateClockwise();
    rotateClockwise();
    moveLeft();
    rotateClockwise();
    rotateClockwise();

  }

  private void moveUp() {
    rotateClockwise();
    rotateClockwise();
    rotateClockwise();
    moveLeft();
    rotateClockwise();
  }

  private void moveDown() {
    rotateClockwise();
    moveLeft();
    rotateClockwise();
    rotateClockwise();
    rotateClockwise();
  }

  private void rotateClockwise() {
    // 90 deg
    int[][] temp = new int[SIDE][SIDE];

    for (int i = 0; i < gameField.length; ++i) { // loop through row
      for (int j = 0; j < gameField.length; ++j) { // loop through column
        temp[i][j] = gameField[gameField.length - j - 1][i];
      }
    }
    gameField = temp;

  }

  private int getMaxTileValue() {
    int max = 0;
    for (int i = 0; i < gameField.length; ++i) { // loop through row
      for (int j = 0; j < gameField.length; ++j) { // loop through column
        if (max < gameField[i][j]) {
          max = gameField[i][j];
        }

      }
    }
    return max;
  }

  private void win() {
    isGameStopped = true;
    showMessageDialog(Color.DEEPPINK, "You win!", Color.ANTIQUEWHITE, 20);
  }

  private void gameOver() {
    isGameStopped = true;
    showMessageDialog(Color.DEEPPINK, "You lose!", Color.ANTIQUEWHITE, 20);
  }

  private boolean canUserMove() {

    for (int i = 0; i < gameField.length; ++i) { // loop through row
      for (int j = 0; j < gameField.length; ++j) { // loop through column

        if (gameField[i][j] == 0) {
          return true;
        }

        if ((i + 1) < SIDE && (gameField[i][j] == gameField[i + 1][j])) {
          return true;
        }

        if ((j + 1) < SIDE && (gameField[i][j] == gameField[i][j + 1])) {
          return true;
        }

      }
    }
    return false;
  }

}