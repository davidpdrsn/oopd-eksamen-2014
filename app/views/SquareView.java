package app.views;

import app.models.Square;
import app.models.SquareState;

/**
 * A square view.
 */
public class SquareView extends View implements TerminalView {
  /**
   * The square this view will render.
   */
  private Square square;

  /**
   * Construct a new new for a given square.
   * @param square the square to be rendered.
   */
  public SquareView(Square square) {
    this.square = square;
  }

  /**
   * Convert the square to a string.
   * @return the string representation of the square.
   */
  public String toString() {
    SquareState state = square.getState();

    if (state == SquareState.STONE) {
      return "1";
    } else if (state == SquareState.OWL) {
      return "2";
    } else if (state == SquareState.MOUSE) {
      return "3";
    } else if (state == SquareState.OWL_STONE) {
      return "4";
    } else if (state == SquareState.STONE_MOUSE) {
      return "5";
    } else if (state == SquareState.OWL_MOUSE) {
      return "6";
    } else if (state == SquareState.TWO_MICE) {
      return "7";
    } else if (state == SquareState.OWL_STONE_MOUSE) {
      return "8";
    } else if (state == SquareState.STONE_TWO_MICE) {
      return "9";
    } else {
      return "0";
    }
  }

  /**
   * Render the square on standard out.
   */
  public void render() {
    System.out.println(toString());
  }
}
