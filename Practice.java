
public class Practice {
  public static void main(String[] args) {
    int[][] row = new int[4][4];

    row[0] = new int[] { 4, 0, 4, 2 };
    row[1] = new int[] { 0, 0, 4, 2 };
    row[2] = new int[] { 4, 0, 0, 4 };
    row[3] = new int[] { 0, 0, 4, 2 };

    canUserMove(row);

    boolean moved = compressRow(row[0]);
    boolean merged = mergeRow(row[0]);
    boolean moved2 = compressRow(row[0]);

    System.out.println(moved);
    System.out.println(moved2);
    System.out.println(merged);

    for (int i : row[0]) {
      System.out.println(i);
    }
    for (int i : row[1]) {
      System.out.println(i);
    }
  }

  private static boolean compressRow(int[] row) {

    boolean moved = false;
    int max = row.length;
    int i, j;
    for (i = 0, j = 0; j < max; j++) {
      System.out.println(row[j]);
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

  private static boolean mergeRow(int[] row) {
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

  private static boolean canUserMove(int[][] row) {

    int zeros = 0;
    boolean sameValue = false;

    for (int i = 0; i < row.length; ++i) { // loop through row
      for (int j = 0; j < row.length; ++j) { // loop through column
        if (row[i][j] == 0) {
          zeros++;
        }
      }
    }

    for (int i = 0; i < row.length - 1; ++i) { // loop through row
      for (int j = 0; j < row.length - 1; ++j) { // loop through column
        if (row[i][j] == row[i + 1][j] || row[i][j] == row[i][j + 1]) {
          sameValue = true;
        }
      }
    }
    System.out.println(zeros);
    System.out.println(sameValue);
    if (zeros > 0) {
      return true;
    } else if (sameValue) {
      return true;
    } else {
      return false;
    }
  }

}
