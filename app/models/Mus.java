package app.models;

import app.services.RandomGenerator;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A mouse in the simulation.
 */
public class Mus extends Entity {
  /**
   * The amount of life the mouse has left.
   */
  private int life;

  /**
   * The current neighbor squares of the mouse.
   */
  private HashMap<Point, Square> neighbors;

  /**
   * The current location of the mouse.
   */
  private Point currentLocation;

  /**
   * The current square the mouse is on.
   */
  private Square currentSquare;

  /**
   * The chance that two mice will reproduce when they are on the same square.
   */
  public final double CHANCE_OF_REPRODUCTION = 0.1;

  /**
   * The amount of life the mouse starts with initially.
   */
  private int INITIAL_LIFE = 20;

  /**
   * How far the mouse can see.
   */
  private int VISION = 1;

  /**
   * Generate a new mouse with 100 life.
   */
  public Mus() {
    this.life = INITIAL_LIFE;
  }

  /**
   * Generate a new mouse with a specified life.
   * @param life the amount of life the mouse starts with.
   */
  public Mus(int life) {
    this.life = life;
  }

  /**
   * Get the amount of life a mouse has left.
   * @return the mouse's remaining life.
   */
  public int getLife() {
    return this.life;
  }

  /**
   * Check if it is a mouse. Is always true.
   * @return is this is a mouse.
   */
  public boolean isMus() {
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
  public Point newLocation(Point location, Miljo env) {
    setLocationState(location, env);

    if (isDead()) {
      return this.currentLocation;
    }

    this.life--;

    if (seesUgle() && canFleeFromUgle()) {
      return pointAwayFromUgle();
    } else {
      return randomPossibleDestination();
    }
  }

  /**
   * Return the new point for the baby mouse to spawn on.
   * @param location the current location of the mouse.
   * @param env the environment the mouse is in.
   * @return the point where a new mouse should spawn.
   */
  public Point birthPlace(Point location, Miljo env) {
    setLocationState(location, env);
    return randomPossibleDestination();
  }

  /**
   * Check if the mouse wants to reproduce.
   * @return whether or no the mouse wants to reproduce.
   */
  public boolean wantsToReproduce() {
    int chance = (int) (CHANCE_OF_REPRODUCTION * 100);
    return RandomGenerator.intBetween(1, 100) <= chance;
  }

  /**
   * Set the location state related instance variables.
   * These variables are used to reduce parameter coupling between methods.
   * @param location the location to be set.
   * @param env the environment the mouse is in.
   */
  private void setLocationState(Point location, Miljo env) {
    // we save the state to prevent parameter coupling between methods
    this.currentLocation = location;
    this.neighbors = env.getNeighborSquares(location, VISION);
    this.currentSquare = env.getSquares().get(location);
  }

  /**
   * Get a random position where it is possible for the mouse to move or
   * reproduce to.
   * @return a random possible position.
   */
  private Point randomPossibleDestination() {
    return possibleDestinations().get(RandomGenerator.intBetween(0, possibleDestinations().size()-1));
  }

  /**
   * Get a list of the possible destinations for the mouse to move to
   * or reproduce to.
   * @return a list of the possible destinations.
   */
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

  /**
   * Check if a square with a certain point is a possible destination.
   * @return whether or not it is a possible destination.
   */
  private boolean isPossibleDestination(Point point, Square square) {
    return !point.equals(this.currentLocation) &&
           square.canHaveAdded(this) &&
           !square.containsUgle();
  }

  /**
   * Return a point that is away from an owl.
   * @return a point that is away from an owl.
   */
  private Point pointAwayFromUgle() {
    if (isOnSten()) {
      return this.currentLocation;
    } else {
      return pointWithFreeSten();
    }
  }

  /**
   * Whether or not the mouse is currently on a stone.
   * @return Whether or not the mouse is currently on a stone.
   */
  private boolean isOnSten() {
    return this.currentSquare.containsSten();
  }

  /**
   * Whether or not the mouse can hide on a stone.
   * @return whether or not the mouse can hide on a stone.
   */
  private boolean canFleeFromUgle() {
    return isOnSten() || seesPointWithFreeSten();
  }

  /**
   * Whether or not the mouse sees a position with a free stone.
   * @return Whether or not the mouse sees a position with a free stone.
   */
  private boolean seesPointWithFreeSten() {
    return pointWithFreeSten() != null;
  }

  /**
   * Return a point with a free stone, or null if there is none.
   * @return A point with a free stone, or null.
   */
  private Point pointWithFreeSten() {
    for (Point aPoint : this.neighbors.keySet()) {
      if (this.neighbors.get(aPoint).containsSten() && !this.neighbors.get(aPoint).containsMus()) {
        return aPoint;
      }
    }

    return null;
  }

  /**
   * Whether or not the mouse sees an owl.
   * @return Whether or not the mouse sees an owl.
   */
  private boolean seesUgle() {
    for (Square aSquare : this.neighbors.values()) {
      if (aSquare.containsUgle()) {
        return true;
      }
    }

    return false;
  }
}
