package app.models;

import app.services.RandomGenerator;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A null or empty entity.
 */
public class Mouse extends Entity {
  private int life;
  private HashMap<Point, Square> neighbors;
  private Point currentLocation;

  /**
   * Generate a new mouse with 100 life.
   */
  public Mouse() {
    this.life = 20;
  }

  /**
   * Generate a new mouse with a specified life.
   * @param life the amount of life the mouse starts with.
   */
  public Mouse(int life) {
    this.life = life;
  }

  /**
   * Get the amount of life a mouse has left.
   * @return the mouse's remaining life.
   */
  public int getLife() {
    return this.life;
  }

  public boolean isMouse() {
    return true;
  }

  /**
   * Check if a mouse is dead.
   * @return whether or no the mouse is dead.
   */
  public boolean isDead() {
    return this.life <= 0;
  }

  /**
   * Return the point the mouse would and can move to and descrease the life of the mouse.
   * @param neighbors the neighbors squares.
   * @return the point the mouse wanna move to.
   */
  public Point makeMove(Point currentLocation, HashMap<Point, Square> neighbors) {
    // save the neighbors to prevent parameter coupling between methods
    this.neighbors = neighbors;
    this.currentLocation = currentLocation;

    if (isDead())
      return this.currentLocation;

    this.life--;

    if (seesOwl() && canFleeFromOwl())
      return pointAwayFromOwl();
    else
      return randomPossibleDestination();
  }

  private Point randomPossibleDestination() {
    return possibleDestinations().get(RandomGenerator.intBetween(0, possibleDestinations().size()));
  }

  private ArrayList<Point> possibleDestinations() {
    ArrayList<Point> possibleDestinations = new ArrayList<Point>();

    for (Point currentPoint : this.neighbors.keySet()) {
      Square currentSquare = neighbors.get(currentPoint);

      if (isPossibleDestination(currentPoint, currentSquare)) {
        possibleDestinations.add(currentPoint);
      }
    }

    if (possibleDestinations.isEmpty()) {
      possibleDestinations.add(this.currentLocation);
    }
    return possibleDestinations;
  }

  private boolean isPossibleDestination(Point point, Square square) {
    return !point.equals(this.currentLocation) &&
           square.canHaveAdded(this) &&
           !square.containsOwl();
  }

  private Point pointAwayFromOwl() {
    if (isOnStone()) {
      return this.currentLocation;
    } else {
      return pointWithFreeStone();
    }
  }

  private boolean isOnStone() {
    return false;
    // return this.neighbors.get(this.currentLocation).containsStone();
  }

  private boolean canFleeFromOwl() {
    return isOnStone() || seesPointWithFreeStone();
  }

  private boolean seesPointWithFreeStone() {
    return pointWithFreeStone() != null;
  }

  private Point pointWithFreeStone() {
    for (Point aPoint : this.neighbors.keySet()) {
      if (this.neighbors.get(aPoint).containsStone() && !this.neighbors.get(aPoint).containsMouse()) {
        return aPoint;
      }
    }

    return null;
  }

  private boolean seesOwl() {
    for (Square aSquare : this.neighbors.values()) {
      if (aSquare.containsOwl()) {
        return true;
      }
    }

    return false;
  }
}
