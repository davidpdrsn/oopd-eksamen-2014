package app.models;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.ArrayList;

import app.services.*;
import app.views.*;

/**
 * The environment of the simulation.
 */
public class Miljo {
  /**
   * The squares within the environment.
   */
  protected HashMap<Point, Square> squares;

  /**
   * A list of all the points.
   */
  private ArrayList<Point> allPoints;

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
   * The number of mice that have been eaten.
   */
  private int numberOfMiceEaten;

  /**
   * Create a new environment with stones, mice and owls.
   */
  public Miljo() {
    this.squares = new HashMap<Point, Square>();
    addEmptySquares();
    addStens();
    addMice();
    addUgles();
    numberOfMiceEaten = 0;
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
  public int numberOfStens() {
    int count = 0;

    for (Point point : allPoints()) {
      if (this.squares.get(point).containsSten())
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
  public int numberOfUgles() {
    int count = 0;

    for (Point point : allPoints()) {
      if (this.squares.get(point).containsUgle())
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

    for (Point neighborPoint : new FindsNeighborPoints(allPoints()).directNeighbors(point)) {
      neighbors.put(neighborPoint, this.squares.get(neighborPoint));
    }

    return neighbors;
  }

  /**
   * Get neighbor squares within a given distance.
   * @param point the point to find the neighbors of.
   * @param distance the distance of the neighbor points.
   * @return the neighbors.
   */
  public HashMap<Point, Square> getNeighborSquares(Point point, int distance) {
    HashMap<Point, Square> neighbors = new HashMap<Point, Square>();

    for (Point neighborPoint : new FindsNeighborPoints(allPoints()).neighborsWithinDistance(distance, point)) {
      neighbors.put(neighborPoint, this.squares.get(neighborPoint));
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
    updateUgles();
  }

  /**
   * Get the current number of mice eaten.
   * @return the current number of mice eaten.
   */
  public int getNumberOfMiceEaten() {
    return this.numberOfMiceEaten;
  }

  /**
   * Move owls and have them eat mice.
   */
  private void updateUgles() {
    ArrayList<Ugle> owlsMoved = new ArrayList<Ugle>();

    for (Point aPoint : allPoints()) {
      Square aSquare = this.squares.get(aPoint);

      if (aSquare.containsUgle()) {
        Ugle owl = aSquare.getUgle();

        if (!owlsMoved.contains(owl)) {
          Point newPoint = owl.newLocation(aPoint, this);
          Square newSquare = this.squares.get(newPoint);

          if (newSquare.containsEdibleMus()) {
            newSquare.remove(newSquare.getMice().get(0));
            this.numberOfMiceEaten++;
          }

          aSquare.remove(owl);
          this.squares.get(newPoint).add(owl);
          owlsMoved.add(owl);
        }
      }
    }
  }

  /**
   * Make all the mouse that can and will reproduce do it.
   */
  private void mouseReproduction() {
    for (Point aPoint : allPoints()) {
      Square aSquare = this.squares.get(aPoint);

      if (aSquare.reproductionCanHappenHere()) {
        Mus mouse = aSquare.getMice().get(0);

        if (mouse.wantsToReproduce()) {
          Point pointForBaby = mouse.birthPlace(aPoint, this);
          this.squares.get(pointForBaby).add(new Mus());
        }
      }
    }
  }

  /**
   * Move the mice.
   */
  private void moveMice() {
    ArrayList<Mus> miceMoved = new ArrayList<Mus>();

    for (Point aPoint : allPoints()) {
      Square aSquare = this.squares.get(aPoint);

      for (Mus mouse : aSquare.getMice()) {
        if (!miceMoved.contains(mouse)) {
          Point newPoint = mouse.newLocation(aPoint, this);
          aSquare.remove(mouse);
          this.squares.get(newPoint).add(mouse);
          miceMoved.add(mouse);
        }
      }
    }
  }

  /**
   * Remove the dead mice.
   */
  private void removeDeadMice() {
    for (Point aPoint : allPoints()) {
      Square aSquare = this.squares.get(aPoint);

      for (Mus mouse : aSquare.getMice()) {
        if (mouse.isDead()) {
          aSquare.remove(mouse);
        }
      }
    }
  }

  /**
   * Get a random square in the environment.
   * @return a random square.
   */
  private Square randomSquare() {
    return this.squares.get(Point.randomWithin(0, SIZE-1));
  }

  /**
   * Get a list of all points in the environment.
   * @return a list of all points.
   */
  protected ArrayList<Point> allPoints() {
    if (this.allPoints == null) {
      ArrayList<Point> acc = new ArrayList<Point>();

      for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
          acc.add(new Point(i,j));
        }
      }

      this.allPoints = acc;
    }

    return this.allPoints;
  }

  /**
   * Add empty squares to all points.
   */
  protected void addEmptySquares() {
    for (Point point : allPoints()) {
      this.squares.put(point, new Square());
    }
  }

  /**
   * Add the required number of stones at random places.
   */
  private void addStens() {
    do {
      Square square = randomSquare();
      SquareSetupStrategy squareRules = new SquareSetupStrategy(square);
      Sten stone = new Sten();
      if (squareRules.allows(stone)) {
        square.add(stone);
      }
    } while (numberOfStens() != NUMBER_OF_STONES);
  }

  /**
   * Add the required number of mice at random places.
   */
  private void addMice() {
    do {
      Square square = randomSquare();
      SquareSetupStrategy squareRules = new SquareSetupStrategy(square);
      Mus mouse = new Mus();
      if (squareRules.allows(mouse)) {
        square.add(mouse);
      }
    } while (numberOfMice() != NUMBER_OF_MICE);
  }

  /**
   * Add the required number of owls at random places.
   */
  private void addUgles() {
    do {
      Square square = randomSquare();
      SquareSetupStrategy squareRules = new SquareSetupStrategy(square);
      Ugle owl = new Ugle();
      if (squareRules.allows(owl)) {
        square.add(owl);
      }
    } while (numberOfUgles() != NUMBER_OF_OWLS);
  }
}
