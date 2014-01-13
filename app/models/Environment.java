package app.models;

import java.util.HashMap;
import java.util.ArrayList;

import app.services.RandomGenerator;

/**
 * The environment of the simulation.
 */
public class Environment {
  private HashMap<Point, Square> squares;

  /**
   * The number of stones in the environment.
   */
  public final int NUMBER_OF_STONES = 10;

  /**
   * The mice in the environment at the beginning.
   */
  public final int NUMBER_OF_MICE = 150;

  /**
   * The owls in the environment.
   */
  public final int NUMBER_OF_OWLS = 2;

  /**
   * The size of the environment.
   */
  public final int SIZE = 20;


  /**
   * Create a new environment with stones, mice and owls.
   */
  public Environment() {
    this.squares = new HashMap<Point, Square>();
    addEmptySquares();
    addStones();
    addMice();
    addOwls();
  }

  /**
   * Get the squares in the environment.
   * @return the squares in the environment.
   */
  public HashMap<Point, Square> getSquares() {
    return this.squares;
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
        if (this.squares.get(new Point(i,j)).containsStone()) count++;
      }
    }

    return count;
  }

  /**
   * Get the number of mice within the environment.
   * @return the number of mice.
   */
  public int numberOfMice() {
    int count = 0;

    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        if (this.squares.get(new Point(i,j)).containsMouse()) count++;
      }
    }

    return count;
  }

  /**
   * Get the number of owls within the environment.
   * @return the number of owls.
   */
  public int numberOfOwls() {
    int count = 0;

    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        if (this.squares.get(new Point(i,j)).containsOwl()) count++;
      }
    }

    return count;
  }

  private Square randomSquare() {
    return this.squares.get(randomPoint());
  }

  private Point randomPoint() {
    return new Point(randomCoordinate(), randomCoordinate());
  }

  private int randomCoordinate() {
    return RandomGenerator.intBetween(0, SIZE);
  }

  private void addEmptySquares() {
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        this.squares.put(new Point(i, j), new Square());
      }
    }
  }

  private void addStones() {
    while (numberOfStones() != NUMBER_OF_STONES) {
      randomSquare().add(new Stone());
    }
  }

  private void addMice() {
    while (numberOfMice() != NUMBER_OF_MICE) {
      randomSquare().add(new Mouse());
    }
  }

  private void addOwls() {
    while (numberOfOwls() != NUMBER_OF_OWLS) {
      Square square = randomSquare();

      while (square.containsMouse() && !square.containsStone()) {
        square = randomSquare();
      }

      square.add(new Owl());
    }
  }
}
