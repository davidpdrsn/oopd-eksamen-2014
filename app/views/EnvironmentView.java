package app.views;

import java.util.HashMap;
import java.lang.reflect.*;

import app.models.*;

/**
 * An environment view.
 */
public class EnvironmentView extends View implements TerminalView {
  private Environment environment;
  private Class squareViewClass;

  /**
   * Construct a new new for a given square.
   * @param square the square to be rendered.
   */
  public EnvironmentView(Environment env) {
    setEnv(env);
    this.squareViewClass = SquareView.class;
  }

  public EnvironmentView(Environment env, Class squareViewClass) {
    setEnv(env);
    this.squareViewClass = squareViewClass;
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

        // TODO: write some comments here!
        try {
          Constructor<TerminalView> ctor = this.squareViewClass.getConstructor(Square.class);
          TerminalView view = ctor.newInstance(square);
          acc += view.toString();
        } catch (Exception _) {}
      }

      acc += "\n";
    }

    return acc;
  }

  /**
   * Render the environment on standard out.
   */
  public void render() {
    clearScreen();
    System.out.println(toString());
  }

  private void clearScreen() {
    for (int i = 0; i < 100; i++) {
      System.out.println("");
    }
  }

  private void setEnv(Environment env) {
    this.environment = env;
  }
}
