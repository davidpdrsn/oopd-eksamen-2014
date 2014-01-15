package app.services;

import app.models.Point;
import java.util.ArrayList;

public class FindsNeighborPoints {
  private ArrayList<Point> grid;

  public FindsNeighborPoints(ArrayList<Point> grid) {
    this.grid = grid;
  }

  public ArrayList<Point> directNeighbors(Point fromPoint) {
    return neighborsWithinDistance(1, fromPoint);
  }

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
