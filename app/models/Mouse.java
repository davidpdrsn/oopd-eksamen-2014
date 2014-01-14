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

  // TODO: are all mice really the same?!
  // TODO: use else if instead?
  public boolean equals(Object obj) {
    if (obj == null) return false;
    if (obj == this) return true;
    if (!(obj instanceof Mouse)) return false;

    return true;
  }

  /**
   * Check if a mouse is dead.
   * @return whether or no the mouse is dead.
   */
  public boolean isDead() {
    return this.life <= 0;
  }

  // TODO: neighbors include the current location!?
  /**
   * Return the point the mouse would and can move to and descrease the life of the mouse.
   * @param neighbors the neighbors squares.
   * @return the point the mouse wanna move to.
   */
  public Point makeMove(HashMap<Point, Square> neighbors) throws DeadMiceDoNotMoveException {
    if (isDead()) throw new DeadMiceDoNotMoveException();

    // save the neighbors to prevent parameter coupling between methods
    this.neighbors = neighbors;

    this.life--;

    if (seesOwl() && canFleeFromOwl()) {
      return pointAwayFromOwl();
    } else {
      return randomPossibleDestination();
    }
  }

  private Point randomPossibleDestination() {
    return possibleDestinations().get(RandomGenerator.intBetween(0, possibleDestinations().size()-1));
  }

  private ArrayList<Point> possibleDestinations() {
    ArrayList<Point> possibleDestinations = new ArrayList<Point>();

    for (Point currentPoint : this.neighbors.keySet()) {
      Square currentSquare = neighbors.get(currentPoint);

      if (isPossibleDestination(currentPoint, currentSquare)) {
        possibleDestinations.add(currentPoint);
      }
    }

    return possibleDestinations;
  }

  private boolean isPossibleDestination(Point point, Square square) {
    return !point.equals(currentLocation()) &&
           square.canHaveAdded(this) &&
           !square.containsOwl();
  }

  private Point pointAwayFromOwl() {
    if (isOnStone()) {
      return currentLocation();
    } else {
      return pointWithFreeStone();
    }
  }

  private boolean isOnStone() {
    return this.neighbors.get(currentLocation()).containsStone();
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

  private Point currentLocation() {
    ArrayList<Point> pointsOnDiagonal = new ArrayList<Point>();

    for (Point aPoint : this.neighbors.keySet()) {
      if (aPoint.getX() == aPoint.getY()) {
        pointsOnDiagonal.add(aPoint);
      }
    }

    return pointsOnDiagonal.get(pointsOnDiagonal.size()/2);
  }
}
