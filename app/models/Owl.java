package app.models;

import java.util.ArrayList;
import java.util.HashMap;

import app.services.*;

// TODO: refactor this!!!!!!
/**
 * An owl.
 */
public class Owl extends Entity {
  private Point currentLocation;
  private HashMap<Point, Square> neighbors;
  private HashMap<Point, Square> visibleSquares;
  private Square currentSquare;
  private Environment currentEnvironment;

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
    setLocationState(location, env);

    if (seesMouse())
      return pointTowardsMouse();
    else
      return randomPossibleDestination();
  }

  private boolean seesMouse() {
    return pointsTowardsMouse().size() > 0;
  }

  private Point pointTowardsMouse() {
    Point choice = pointsTowardsMouse().get(RandomGenerator.intBetween(0, pointsTowardsMouse().size()-1));

    return movePointCloserToMouse(choice);
  }

  private Point movePointCloserToMouse(Point aPoint) {
    if (pointDistance(aPoint, this.currentLocation) > 2.0) {
      if (aPoint.getY() < this.currentLocation.getY()) {
        // its above
        aPoint = new Point(aPoint.getX(), aPoint.getY()+1);
      } else {
        // its below
        aPoint = new Point(aPoint.getX(), aPoint.getY()-1);
      }

      if (aPoint.getX() < this.currentLocation.getX()) {
        // its on the right
        aPoint = new Point(aPoint.getX()+1, aPoint.getY());
      } else {
        // its on the left
        aPoint = new Point(aPoint.getX()-1, aPoint.getY());
      }
    }

    return aPoint;
  }

  private double pointDistance(Point a, Point b) {
    return Math.sqrt(Math.pow(b.getX()-a.getX(), 2) + Math.pow(b.getY()-a.getY(), 2));
  }

  private ArrayList<Point> pointsTowardsMouse() {
    ArrayList<Point> pointsWithMice = new ArrayList<Point>();

    for (Point aPoint : this.visibleSquares.keySet()) {
      Square aSquare = this.currentEnvironment.getSquares().get(aPoint);

      if (aSquare.containsEdibleMouse()) {
        pointsWithMice.add(aPoint);
      }
    }

    return pointsWithMice;
  }

  private Point randomPossibleDestination() {
    return possibleDestinations().get(RandomGenerator.intBetween(0, possibleDestinations().size()-1));
  }

  private void setLocationState(Point location, Environment env) {
    this.currentLocation = location;
    this.neighbors = env.getNeighborSquares(location);
    this.visibleSquares = env.getNeighborSquares(location, 2);
    this.currentEnvironment = env;
    this.currentSquare = env.getSquares().get(location);
  }

  private ArrayList<Point> possibleDestinations() {
    ArrayList<Point> acc = new ArrayList<Point>();

    for (Point aPoint : this.neighbors.keySet()) {
      Square aSquare = neighbors.get(aPoint);

      if (isPossibleDestination(aPoint, aSquare)) {
        acc.add(aPoint);
      }
    }

    if (acc.isEmpty()) {
      acc.add(this.currentLocation);
    }

    return acc;
  }

  private boolean isPossibleDestination(Point point, Square square) {
    return !point.equals(this.currentLocation) &&
           square.canHaveAdded(this) &&
           !square.containsOwl();
  }
}
