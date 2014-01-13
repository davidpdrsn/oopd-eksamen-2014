package app.views;

import java.util.HashMap;

import app.models.Environment;
import app.models.Point;
import app.models.Square;

/**
 * An environment view.
 */
public class EnvironmentView implements TerminalView {
  private Environment environment;

  /**
   * Construct a new new for a given square.
   * @param square the square to be rendered.
   */
  public EnvironmentView(Environment environment) {
    this.environment = environment;
  }

  /**
   * Convert the environment to a string.
   * @return the string representation of the environment.
   */
  public String toString() {
    String acc = "";
    HashMap<Point, Square> squares = this.environment.getSquares();

    for (int i = 0; i < this.environment.SIZE; i++) {
      for (int j = 0; j < this.environment.SIZE; j++) {
        Square square = squares.get(new Point(i, j));
        SquareView squareView = new SquareView(square);
        acc += squareView.toString();
      }

      acc += "\n";
    }

    return acc;
  }

  /**
   * Render the environment on standard out.
   */
  public void render() {
    System.out.println(toString());
  }
}