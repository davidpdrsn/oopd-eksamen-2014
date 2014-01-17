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
public class Environment {
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
  public Environment() {
    this.squares = new HashMap<Point, Square>();
    addEmptySquares();
    addStones();
    addMice();
    addOwls();
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

    for (Point neighborPoint : new FindsNeighborPoints(allPoints()).directNeighbors(point)) {
      neighbors.put(neighborPoint, this.squares.get(neighborPoint));
    }

    return neighbors;
  }

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
    updateOwls();
  }

  public int getNumberOfMiceEaten() {
    return this.numberOfMiceEaten;
  }

  private void updateOwls() {
    ArrayList<Owl> owlsMoved = new ArrayList<Owl>();

    for (Point aPoint : allPoints()) {
      Square aSquare = this.squares.get(aPoint);

      if (aSquare.containsOwl()) {
        Owl owl = aSquare.getOwl();

        if (!owlsMoved.contains(owl)) {
          Point newPoint = owl.newLocation(aPoint, this);
          Square newSquare = this.squares.get(newPoint);

          if (newSquare.containsMouse()) {
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
        Mouse mouse = aSquare.getMice().get(0);

        if (mouse.wantsToReproduce()) {
          Point pointForBaby = mouse.birthPlace(aPoint, this);
          this.squares.get(pointForBaby).add(new Mouse());
        }
      }
    }
  }

  /**
   * Move the mice.
   */
  private void moveMice() {
    ArrayList<Mouse> miceMoved = new ArrayList<Mouse>();

    for (Point aPoint : allPoints()) {
      Square aSquare = this.squares.get(aPoint);

      for (Mouse mouse : aSquare.getMice()) {
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

      for (Mouse mouse : aSquare.getMice()) {
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
  private void addStones() {
    do {
      Square square = randomSquare();
      SquareSetupStrategy squareRules = new SquareSetupStrategy(square);
      Stone stone = new Stone();
      if (squareRules.allows(stone)) {
        square.add(stone);
      }
    } while (numberOfStones() != NUMBER_OF_STONES);
  }

  /**
   * Add the required number of mice at random places.
   */
  private void addMice() {
    do {
      Square square = randomSquare();
      SquareSetupStrategy squareRules = new SquareSetupStrategy(square);
      Mouse mouse = new Mouse();
      if (squareRules.allows(mouse)) {
        square.add(mouse);
      }
    } while (numberOfMice() != NUMBER_OF_MICE);
  }

  /**
   * Add the required number of owls at random places.
   */
  private void addOwls() {
    do {
      Square square = randomSquare();
      SquareSetupStrategy squareRules = new SquareSetupStrategy(square);
      Owl owl = new Owl();
      if (squareRules.allows(owl)) {
        square.add(owl);
      }
    } while (numberOfOwls() != NUMBER_OF_OWLS);
  }
}
