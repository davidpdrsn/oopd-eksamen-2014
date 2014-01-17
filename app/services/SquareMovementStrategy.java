package app.services;

import app.models.*;

/**
 * Class the encapsulates the rules for where entities are
 * allowed to move.
 */
public class SquareMovementStrategy extends EntityPositioningStrategy {
  /**
   * Make a new one for the given square.
   * @param square the square to look at.
   */
  public SquareMovementStrategy(Square square) {
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
    return mouseCount < 3 && owlCount < 2;
  }
}
