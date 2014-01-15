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
        count += this.squares.get(new Point(i,j)).containsNumberOfMice();
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

  /**
   * Get neighbor squares.
   * @param point the point to find the neighbors of.
   * @return the neighbors.
   */
  public HashMap<Point, Square> getNeighborSquares(Point point) {
    HashMap<Point, Square> neighbors = new HashMap<Point, Square>();

    for (int i = -1; i <= 1; i++) {
      for (int j = -1; j <= 1; j++) {
        if (i == 0 && j == 0) continue;

        Point currentPoint = new Point(point.getX()+i, point.getY()+j);
        Square currentSquare = this.squares.get(currentPoint);

        if (currentSquare != null) {
          neighbors.put(currentPoint, currentSquare);
        }
      }
    }

    return neighbors;
  }

  // TODO: how to test this?!
  /**
   * Update the environment.
   */
  public void update() {
    // move all mice
    // mice reproduce
    // move all owls
    // make owls eat mice

    removeDeadMice();
    moveMice();
  }

  private void moveMice() {
    ArrayList<Mouse> miceMoved = new ArrayList<Mouse>();

    for (Point pointWithMice : pointsWithMice()) {
      Square squareWithMice = this.squares.get(pointWithMice);

      for (Mouse mouse : squareWithMice.getMice()) {
        if (!miceMoved.contains(mouse)) {
          Point newPoint = mouse.makeMove(pointWithMice, getNeighborSquares(pointWithMice));
          squareWithMice.remove(mouse);
          this.squares.get(newPoint).add(mouse);
          miceMoved.add(mouse);
        }
      }
    }
  }

  private void removeDeadMice() {
    ArrayList<Point> points = pointsWithMice();
    for (int i = 0; i < points.size(); i++) {
      Point aPoint = points.get(i);
      Square aSquare = this.squares.get(aPoint);
      ArrayList<Mouse> mice = aSquare.getMice();

      for (int j = 0; j < mice.size(); j++) {
        Mouse aMouse = mice.get(j);

        if (aMouse.isDead()) {
          aSquare.remove(aMouse);
        }
      }
    }
  }

  private ArrayList<Point> pointsWithMice() {
    ArrayList<Point> acc = new ArrayList<Point>();

    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        Point aPoint = new Point(i,j);
        Square aSquare = this.squares.get(aPoint);

        if (aSquare.containsMouse()) {
          acc.add(aPoint);
        }
      }
    }

    return acc;
  }

  private Square randomSquare() {
    return this.squares.get(randomPoint());
  }

  // TODO: this should be moved to Point class as a separate constructor
  private Point randomPoint() {
    return new Point(randomCoordinate(), randomCoordinate());
  }

  // TODO: this should be moved to Point class as a separate constructor
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
