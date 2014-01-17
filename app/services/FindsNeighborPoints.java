package app.services;

import app.models.Point;
import java.util.ArrayList;

/**
 * Class that encapsulated the algorithm required for finding neighbors squares
 * within any given distance.
 */
public class FindsNeighborPoints {
  /**
   * A list of the points to search through.
   */
  private ArrayList<Point> grid;

  /**
   * Constuct a new neighbor point finder for the given list of points.
   */
  public FindsNeighborPoints(ArrayList<Point> grid) {
    this.grid = grid;
  }

  /**
   * Find points that are direct neighbors to a given point.
   * @param fromPoint the point to search from.
   * @return the list of neighbors.
   */
  public ArrayList<Point> directNeighbors(Point fromPoint) {
    return neighborsWithinDistance(1, fromPoint);
  }

  /**
   * Find points within any given distance.
   * @param distance the distance to search within.
   * @param fromPoint the point to search from.
   * @return the list of neighbors.
   */
  public ArrayList<Point> neighborsWithinDistance(int distance, Point fromPoint) {
    ArrayList<Point> neighbors = new ArrayList<Point>();

    for (int i = -distance; i <= distance; i++) {
      for (int j = -distance; j <= distance; j++) {
        if (i == 0 && j == i) continue;

        Point neighborPoint = new Point(fromPoint.getX()+i, fromPoint.getY()+j);

        if (this.grid.contains(neighborPoint)) {
          neighbors.add(neighborPoint);
        }
      }
    }

    return neighbors;
  }
}
