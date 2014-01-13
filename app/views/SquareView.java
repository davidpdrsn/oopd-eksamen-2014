package app.views;

import app.models.Square;
import app.models.SquareState;

/**
 * A square view.
 */
public class SquareView implements TerminalView {
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

    // TODO: how would this look as a case statement?
    if (state == SquareState.STONE) {
      return "(🌑  )";
    } else if (state == SquareState.OWL) {
      return "(🐤  )";
    } else if (state == SquareState.MOUSE) {
      return "(🐭  )";
    } else if (state == SquareState.OWL_STONE) {
      return "(🌑🐤 )";
    } else if (state == SquareState.STONE_MOUSE) {
      return "(🐭🌑 )";
    } else if (state == SquareState.OWL_MOUSE) {
      return "(🐭🐤 )";
    } else if (state == SquareState.TWO_MICE) {
      return "(🐭🐭 )";
    } else if (state == SquareState.OWL_STONE_MOUSE) {
      return "(🐭🌑🐤)";
    } else if (state == SquareState.STONE_TWO_MICE) {
      return "(🐭🌑🐭)";
    } else {
      return "(   )";
    }
  }

  /**
   * Render the square on standard out.
   */
  public void render() {
    System.out.println(toString());
  }
}
