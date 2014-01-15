package app.models;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.ArrayList;

import app.services.RandomGenerator;

/**
 * The environment of the simulation.
 */
public class Environment implements IEnvironment {
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

    for (Point point : allPoints()) {
      if (this.squares.get(point).containsStone())
        count++;
    }

    return count;
  }

  /**
   * Get the number of mice within the environment.
   * @return the number of mice.
   */
  public int numberOfMice() {
    int count = 0;

    for (Point point : allPoints()) {
      count += this.squares.get(point).containsNumberOfMice();
    }

    return count;
  }

  /**
   * Get the number of owls within the environment.
   * @return the number of owls.
   */
  public int numberOfOwls() {
    int count = 0;

    for (Point point : allPoints()) {
      if (this.squares.get(point).containsOwl())
        count++;
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
        if (i == 0 && j == i) continue;

        Point currentPoint = new Point(point.getX()+i, point.getY()+j);
        Square currentSquare = this.squares.get(currentPoint);

        if (currentSquare != null) {
          neighbors.put(currentPoint, currentSquare);
        }
      }
    }

    return neighbors;
  }

  /**
   * Update the environment.
   */
  public void update() {
    removeDeadMice();
    moveMice();
    mouseReproduction();
    // move all owls
    // make owls eat mice
  }

  private void mouseReproduction() {
    for (Point aPoint : allPoints()) {
      Square aSquare = this.squares.get(aPoint);

      if (aSquare.reproductionCanHappenHere()) {
        Mouse mouse = aSquare.getMice().get(0);

        if (mouse.wantsToReproduce()) {
          Point pointForBaby = mouse.birthPlace(aPoint, this);
          this.squares.get(pointForBaby).add(new Mouse());
        }
      }
    }
  }

  private void moveMice() {
    ArrayList<Mouse> miceMoved = new ArrayList<Mouse>();

    for (Point aPoint : allPoints()) {
      Square aSquare = this.squares.get(aPoint);

      for (Mouse mouse : aSquare.getMice()) {
        if (!miceMoved.contains(mouse)) {
          Point newPoint = mouse.makeMove(aPoint, this);
          aSquare.remove(mouse);
          this.squares.get(newPoint).add(mouse);
          miceMoved.add(mouse);
        }
      }
    }
  }

  private void removeDeadMice() {
    for (Point aPoint : allPoints()) {
      Square aSquare = this.squares.get(aPoint);

      for (Mouse mouse : aSquare.getMice()) {
        if (mouse.isDead()) {
          aSquare.remove(mouse);
        }
      }
    }
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

  // TODO: have this set an instance variable for caching!!
  private ArrayList<Point> allPoints() {
    ArrayList<Point> points = new ArrayList<Point>();

    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        points.add(new Point(i,j));
      }
    }

    return points;
  }

  private void addEmptySquares() {
    for (Point point : allPoints()) {
      this.squares.put(point, new Square());
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
