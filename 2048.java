package com.codegym.games.game2048;

import com.codegym.engine.cell.*;

public class Game2048 extends Game {

  private static final int SIDE = 4;
  private int[][] gameField = new int[SIDE][SIDE];

  public void initialize() {
    setScreenSize(SIDE, SIDE);
    createGame();
    drawScene();
  }

  private void createGame() {
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
    int max = row.length;
    int i, j;
    for (i = 0, j = 0; j < max; j++) {
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
        merged = true;
      }
    }
    return merged;
  }

  private void onKeyPress(Key key) {
    if (key == Key.LEFT) {

    } else if (key == Key.RIGHT) {

    } else if (key == Key.UP) {

    } else if (key == Key.DOWN) {

    }
  }

}