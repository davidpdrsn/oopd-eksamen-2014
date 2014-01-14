package test.models;

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
import app.models.*;

@RunWith(JUnit4.class)
public class EnvironmentTester {
  Environment env;

  @Before
  public void setup() {
    env = new Environment();
  }

  @Test
  public void hasAGrid() {
    assertEquals(env.SIZE * env.SIZE, env.size());
  }

  @Test
  public void containsANumberOfStones() {
    assertEquals(env.NUMBER_OF_STONES, env.numberOfStones());
  }

  @Test
  public void containsANumberOfMice() {
    assertEquals(env.NUMBER_OF_MICE, env.numberOfMice());
  }

  @Test
  public void containsANumberOfOwls() {
    assertEquals(env.NUMBER_OF_OWLS, env.numberOfOwls());
  }

  @Test
  public void getNeighborSquaresForSquareInMiddel() {
    assertEquals(9, env.getNeighborSquares(new Point(env.SIZE/2, env.SIZE/2)).size());
  }

  @Test
  public void getNeighborSquaresForSquareInCorner() {
    assertEquals(4, env.getNeighborSquares(new Point(0, 0)).size());
  }

  @Test
  public void getNeighborSquaresForSquareOnEdge() {
    assertEquals(6, env.getNeighborSquares(new Point(0, env.SIZE/2)).size());
  }

  @Test
  public void getNeighborSquaresForSquareOutOfBounds() {
    assertEquals(0, env.getNeighborSquares(new Point(env.SIZE+10, env.SIZE+10)).size());
  }
}
