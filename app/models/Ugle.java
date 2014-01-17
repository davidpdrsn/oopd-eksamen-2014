package app.models;

import java.util.ArrayList;
import java.util.HashMap;

import app.services.*;

/**
 * An owl.
 */
public class Ugle extends Entity {
  /**
   * The current location of the owl.
   * Keeping this an instance of the object reduces paramter coupling
   * between the methods. We don't have to pass the location around
   * all the time.
   */
  private Point location;

  /**
   * The current environment the owl is in.
   * Keeping this an instance of the object reduces paramter coupling
   * between the methods. We don't have to pass the environment around
   * all the time.
   */
  private Miljo env;

  /**
   * The distance the owl is able to see.
   */
  private int VISION = 2;

  /**
   * Check if the entity is an owl or not. This is always true.
   * @return whether its an owl or not.
   */
  public boolean isUgle() {
    return true;
  }

  /**
   * Find the point the owl wanna move to.
   * @param location the current location of the owl.
   * @param env the environment the owl is currently in.
   * @return the point the owl wanna move to.
   */
  public Point newLocation(Point location, Miljo env) {
    this.location = location;
    this.env = env;

    if (seesMus()) {
      return randomPointFrom(selectPossibleDestinations(movePointsCloser(pointsWithMus())));
    } else {
      return randomPointFrom(selectPossibleDestinations(neighborPoints()));
    }
  }

  /**
   * Check if the owl sees a mouse.
   * @return whether or not the owl can see a mouse.
   */
  private boolean seesMus() {
    return pointsWithMus().size() > 0;
  }

  /**
   * Move a list of points closer to the owl.
   * The points will be converted to the closest neighbor point.
   * @param points to move closer.
   * @return the points moved closer.
   */
  private ArrayList<Point> movePointsCloser(ArrayList<Point> points) {
    ArrayList<Point> acc = new ArrayList<Point>();

    for (Point pointWithMus : pointsWithMus()) {
      acc.add(pointWithMus.closestPointOutOf(neighborPoints()));
    }

    return acc;
  }

  /**
   * Within the points the owl can see find the ones
   * that contain an edible mouse.
   * @return the points contain an edible mouse.
   */
  private ArrayList<Point> pointsWithMus() {
    ArrayList<Point> acc = new ArrayList<Point>();

    for (Point aPoint : this.env.getNeighborSquares(this.location, VISION).keySet()) {
      Square aSquare = this.env.getNeighborSquares(this.location, VISION).get(aPoint);

      if (aSquare.containsEdibleMus()) {
        acc.add(aPoint);
      }
    }

    return acc;
  }

  /**
   * Get the neighbor points as a list.
   * Normally they come as a Set from the environment.
   * This makes it tricky to get an element at a certain index, so
   * we convert it.
   * @return the neighbor points in an ArrayList.
   */
  private ArrayList<Point> neighborPoints() {
    ArrayList<Point> acc = new ArrayList<Point>();

    for (Point neighborPoint : this.env.getNeighborSquares(this.location).keySet()) {
      acc.add(neighborPoint);
    }

    return acc;
  }

  /**
   * Pick a random point from within a list of points.
   * @param points the points to choose from.
   * @return a random point.
   */
  private Point randomPointFrom(ArrayList<Point> points) {
    int randomIndex = RandomGenerator.intBetween(0, points.size()-1);
    return points.get(randomIndex);
  }

  /**
   * Given a list of points, return a new list containing the
   * ones that the owl can move to.
   * @param points the points to select from.
   * @return a list of possible destinations for the owl.
   */
  private ArrayList<Point> selectPossibleDestinations(ArrayList<Point> points) {
    ArrayList<Point> acc = new ArrayList<Point>();

    for (Point aPoint : points) {
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
