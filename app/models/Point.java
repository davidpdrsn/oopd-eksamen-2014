package app.models;

import app.services.RandomGenerator;
import java.util.ArrayList;

/**
 * A point class.
 */
public class Point {
  /**
   * The x coordinate.
   */
  private int x;

  /**
   * The y coordinate.
   */
  private int y;

  /**
   * Construct a new point with an x and a y coordinate.
   * @param x the x coordinate.
   * @param y the y coordinate.
   */
  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Return a new point who's coordinates are within a certain range.
   * @param low the lowest point.
   * @param high the highest point.
   * @return a point who's coordinates are within the given range.
   */
  public static Point randomWithin(int low, int high) {
    return new Point(RandomGenerator.intBetween(low, high), RandomGenerator.intBetween(low, high));
  }

  /**
   * Get the x coordinate of the point.
   * @return the x coordinate.
   */
  public int getX() {
    return this.x;
  }

  /**
   * Get the y coordinate of the point.
   * @return the y coordinate.
   */
  public int getY() {
    return this.y;
  }

  /**
   * Check for equality with another object.
   * @return whether or not the two objects are the same.
   */
  public boolean equals(Object obj) {
    if (this == obj) return true;

    if ((obj == null) || (obj.getClass() != this.getClass())) return false;

    Point point = (Point) obj;
    return this.x == point.getX() && this.y == point.getY();
  }

  /**
   * Generate a hash code for the point.
   * @return the generated hash code.
   */
  public int hashCode() {
    int hash = 5;
    hash += this.x;
    hash += this.y;
    return hash;
  }

  public double distanceTo(Point point) {
    return Math.sqrt(Math.pow(point.getX()-getX(), 2) + Math.pow(point.getY()-getY(), 2));
  }

  public Point closestPointOutOf(ArrayList<Point> points) {
    if (points.isEmpty()) return null;

    Point closest = points.get(0);

    for (Point point : points) {
      if (this.distanceTo(point) < this.distanceTo(closest)) {
        closest = point;
      }
    }

    return closest;
  }
}
