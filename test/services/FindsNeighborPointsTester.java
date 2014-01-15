package test.services;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import app.models.Point;
import app.services.FindsNeighborPoints;

@RunWith(JUnit4.class)
public class FindsNeighborPointsTester {
  ArrayList<Point> grid;
  Point middle, edge, corner, farOut;

  @Before
  public void setup() {
    // The grid:
    //
    //  01234
    // 0.....
    // 1.....
    // 2.....
    // 3.....
    // 4.....

    int gridSize = 5;

    grid = new ArrayList<Point>();

    for (int i = 0; i < gridSize; i++) {
      for (int j = 0; j < gridSize; j++) {
        grid.add(new Point(i,j));
      }
    }

    middle = new Point(gridSize/2, gridSize/2);
    edge = new Point(0, gridSize/2);
    corner = new Point(0, 0);
    farOut = new Point(gridSize*2, gridSize*2);
  }

  @Test
  public void directNeighborsInTheMiddle() {
    assertEquals(8, new FindsNeighborPoints(grid).directNeighbors(middle).size());
  }

  @Test
  public void directNeighborsOnEdge() {
    assertEquals(5, new FindsNeighborPoints(grid).directNeighbors(edge).size());
  }

  @Test
  public void directNeighborsInCorner() {
    assertEquals(3, new FindsNeighborPoints(grid).directNeighbors(corner).size());
  }

  @Test
  public void neighborsWithDistance_middle() {
    assertEquals(24, new FindsNeighborPoints(grid).neighborsWithinDistance(2, middle).size());
  }

  @Test
  public void neighborsWithDistance_corner() {
    assertEquals(8, new FindsNeighborPoints(grid).neighborsWithinDistance(2, corner).size());
  }

  @Test
  public void neighborsWithDistance_edge() {
    assertEquals(14, new FindsNeighborPoints(grid).neighborsWithinDistance(2, edge).size());
  }

  @Test
  public void neighborsWithinDistance_outOfBounds() {
    assertEquals(0, new FindsNeighborPoints(grid).neighborsWithinDistance(1, farOut).size());
  }
}
