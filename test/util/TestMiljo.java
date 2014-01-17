package test.util;

import java.util.ArrayList;
import java.util.HashMap;
import app.models.*;

/**
 * A subclass of of environment used specifically
 * for testing the Environment class.
 *
 * The only difference is that it has a method for setting
 * a square to a specific square object and it
 * always starts out with only empty squares.
 * This way you can customize the environment to
 * look exactly like you need it to.
 */
public class TestMiljo extends Miljo {
  /**
   * Construct a new empty environment for testing.
   */
  public TestMiljo() {
    super();
    addEmptySquares();
  }

  /**
   * Set a square at a given point to a given square object.
   * @param point the point of the square.
   * @param square the square to be set.
   */
  public void setSquare(Point point, Square square) {
    this.squares.put(point, square);
  }
}
