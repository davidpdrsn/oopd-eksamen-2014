package app.services;

import app.models.*;

/**
 * Class that encapsulates the rules for where entities are
 * allowed to be placed when the simulation starts.
 */
public class SquareSetupStrategy extends EntityPositioningStrategy {
  /**
   * Make a new one for the given square.
   * @param square the square to look at.
   */
  public SquareSetupStrategy(Square square) {
    super(square);
  }

  /**
   * Check if the given number of entities are allowed on
   * the square.
   * @param mouseCount the number of mice.
   * @param stoneCount the number of stone.
   * @param owlCount the number of owls.
   * @return if the combination is allowed.
   */
  public boolean allowedCounts(int mouseCount, int stoneCount, int owlCount) {
    // This conditional could be made shorter, but I think
    // its more readable like this
    if (mouseCount >= 1 && owlCount == 1 ||
        stoneCount >= 2 ||
        owlCount >= 2 ||
        mouseCount > 2) {
      return false;
    } else {
      return true;
    }
  }
}
