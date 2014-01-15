package app.models;

import java.util.HashMap;

/**
 * A interface describing an environment in the simulation.
 */
public interface IEnvironment {
  /**
   * Return the squares represented as a hash with points and squares.
   */
  public HashMap<Point, Square> getSquares();

  // TODO: should this really be a method?!
  /**
   * Get the size of the environment.
   */
  public int size();

  // TODO: this should not be a public method
  // since the number of stones shouldn't change
  /**
   * Get the number of stones currently in the environment.
   */
  public int numberOfStones();

  /**
   * Get the current number of mice in the environment.
   */
  public int numberOfMice();

  // TODO: this should not be a public method
  // since the number of owls shouldn't change
  /**
   * Get the current number of owls in the environment.
   */
  public int numberOfOwls();

  /**
   * Get the neighbor squares for a given point in the environment.
   */
  public HashMap<Point, Square> getNeighborSquares(Point point);

  /**
   * Update the environment.
   */
  public void update();
}
