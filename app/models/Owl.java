package app.models;

import java.util.ArrayList;
import java.util.HashMap;

import app.services.*;

/**
 * An owl.
 */
public class Owl extends Entity {
  private Point location;
  private Environment env;

  private int VISION = 2;

  /**
   * Check if the entity is an owl or not. This is always true.
   * @return whether its an owl or not.
   */
  public boolean isOwl() {
    return true;
  }

  /**
   * Find the point the owl wanna move to.
   * @param location the current location of the owl.
   * @param env the environment the owl is currently in.
   * @return the point the owl wanna move to.
   */
  public Point newLocation(Point location, Environment env) {
    this.location = location;
    this.env = env;

    if (seesMouse()) {
      return pointTowardsMouse();
    } else {
      return randomPossibleDestination();
    }
  }

  private boolean seesMouse() {
    for (Square aSquare : this.env.getNeighborSquares(this.location, VISION).values()) {
      if (aSquare.containsEdibleMouse()) {
        return true;
      }
    }

    return false;
  }

  private Point pointTowardsMouse() {
    // find all points at which there mice
    ArrayList<Point> pointsWithMouse = new ArrayList<Point>();
    for (Point aPoint : this.env.getNeighborSquares(this.location, VISION).keySet()) {
      Square aSquare = this.env.getNeighborSquares(this.location, VISION).get(aPoint);

      if (aSquare.containsEdibleMouse()) {
        pointsWithMouse.add(aPoint);
      }
    }

    ArrayList<Point> neighborPointsList = new ArrayList<Point>();
    for (Point neighborPoint : this.env.getNeighborSquares(this.location).keySet()) {
      neighborPointsList.add(neighborPoint);
    }

    // move each of those closer to the owl
    ArrayList<Point> pointsMovedCloser = new ArrayList<Point>();
    for (Point pointWithMouse : pointsWithMouse) {
      pointsMovedCloser.add(pointWithMouse.closestPointOutOf(neighborPointsList));
    }

    // filter by the ones that are actually possible destinations
    ArrayList<Point> possiblePoints = new ArrayList<Point>();
    for (Point aPoint : pointsMovedCloser) {
      Square aSquare = env.getSquares().get(aPoint);

      if (new SquareMovementStrategy(aSquare).allows(this)) {
        possiblePoints.add(aPoint);
      }
    }
    if (possiblePoints.isEmpty()) {
      possiblePoints.add(location);
    }

    // pick a random one
    int randomIndex = RandomGenerator.intBetween(0, possiblePoints.size()-1);
    return possiblePoints.get(randomIndex);
  }

  private Point randomPossibleDestination() {
    int randomIndex = RandomGenerator.intBetween(0, possibleDestinations().size()-1);
    return possibleDestinations().get(randomIndex);
  }

  private ArrayList<Point> possibleDestinations() {
    ArrayList<Point> acc = new ArrayList<Point>();

    for (Point aPoint : env.getNeighborSquares(location).keySet()) {
      Square aSquare = env.getSquares().get(aPoint);

      if (new SquareMovementStrategy(aSquare).allows(this)) {
        acc.add(aPoint);
      }
    }

    if (acc.isEmpty()) {
      acc.add(location);
    }

    return acc;
  }
}
