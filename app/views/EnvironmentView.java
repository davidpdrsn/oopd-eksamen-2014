package app.views;

import java.util.HashMap;
import java.lang.reflect.*;

import app.models.*;

/**
 * An environment view.
 */
public class EnvironmentView extends View implements TerminalView {
  /**
   * The environment this view will render.
   */
  private Environment environment;

  /**
   * The class this view will use to render the partial square views.
   */
  private Class squareViewClass;

  /**
   * Construct a new view for the given environment.
   * It will use `SquareView` to render the squares.
   * @param env the environment to render.
   */
  public EnvironmentView(Environment env) {
    setEnv(env);
    this.squareViewClass = SquareView.class;
  }

  /**
   * Construct a new view for the given environment using the given class
   * for representing the partial square views.
   * @param env the environment to render.
   * @param squareViewClass the view class used for representing the partial square view.
   */
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

  /**
   * Clear the screen.
   * It does this by printing lots of empty lines.
   */
  private void clearScreen() {
    for (int i = 0; i < 100; i++) {
      System.out.println("");
    }
  }

  /**
   * Set the environment instance variable.
   * @param env the environment to be set.
   */
  private void setEnv(Environment env) {
    this.environment = env;
  }
}
