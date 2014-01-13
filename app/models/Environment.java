package app.models;

import java.util.HashMap;

public class Environment {
  private HashMap<Point, Square> squares;
  private final int SIZE = 20;

  public Environment() {
    squares = new HashMap<Point, Square>();

    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        squares.put(new Point(i, j), new Square());
      }
    }
  }

  public int size() {
    return this.squares.size();
  }

  public int numberOfStones() {
    return 0;
  }

  public boolean isEmpty(Point point) {
    return this.squares.get(point).isEmpty();
  }
}
