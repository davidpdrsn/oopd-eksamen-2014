package app.models;

import java.util.HashMap;
import java.util.ArrayList;

import app.services.RandomGenerator;

/**
 * The environment of the simulation.
 */
public class Environment {
  private HashMap<Point, ArrayList<Entity>> squares;
  private final int SIZE = 20;
  private final int NUMBER_OF_STONES = 10;

  /**
   * Create a new environment with stones, mice and owls.
   */
  public Environment() {
    this.squares = new HashMap<Point, ArrayList<Entity>>();
    addEmptySquares();
    addStones();
  }

  /**
   * Get the number of squares in the environment.
   * @return the number of squares.
   */
  public int size() {
    return this.squares.size();
  }

  /**
   * Get the number of stones within the environment.
   * @return the number of stones.
   */
  public int numberOfStones() {
    int count = 0;

    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        ArrayList<Entity> thingsOnSquare = this.squares.get(new Point(i, j));

        for (int k = 0; k < thingsOnSquare.size(); k++) {
          if (thingsOnSquare.get(k).isStone()) count++;
        }
      }
    }

    return count;
  }

  private void addEmptySquares() {
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        ArrayList<Entity> squareState = new ArrayList<Entity>();
        squareState.add(new NullEntity());
        this.squares.put(new Point(i, j), squareState);
      }
    }
  }

  private void addStones() {
    while (numberOfStones() != 10) {
      ArrayList<Entity> squareWithStone = new ArrayList<Entity>();
      squareWithStone.add(new Stone());

      int x = RandomGenerator.intBetween(0, SIZE);
      int y = RandomGenerator.intBetween(0, SIZE);

      this.squares.put(new Point(x, y), squareWithStone);
    }
  }
}
