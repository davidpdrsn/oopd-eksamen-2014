package app.models;

import app.services.RandomGenerator;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A mouse in the simulation.
 */
public class Mouse extends Entity {
  /**
   * The amount of life the mouse has left.
   */
  private int life;
  private HashMap<Point, Square> neighbors;
  private Point currentLocation;
  private Square currentSquare;

  public final double CHANGE_OF_REPRODUCTION = 0.1;

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
  public Point makeMove(Point location, IEnvironment env) {
    setLocationState(location, env);

    if (isDead())
      return this.currentLocation;

    this.life--;

    if (seesOwl() && canFleeFromOwl())
      return pointAwayFromOwl();
    else
      return randomPossibleDestination();
  }

  public Point birthPlace(Point location, IEnvironment env) {
    setLocationState(location, env);
    return randomPossibleDestination();
  }

  public boolean wantsToReproduce() {
    int chance = (int) (CHANGE_OF_REPRODUCTION * 100);
    return RandomGenerator.intBetween(1, 100) <= chance;
  }

  private void setLocationState(Point location, IEnvironment env) {
    // we save the state to prevent parameter coupling between methods
    this.currentLocation = location;
    this.neighbors = env.getNeighborSquares(location);
    this.currentSquare = env.getSquares().get(location);
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

  // TODO: a field with an owl should have be able to have a mouse added
  // but a field with a mouse should be able to have a mouse added
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
    return this.currentSquare.containsStone();
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
