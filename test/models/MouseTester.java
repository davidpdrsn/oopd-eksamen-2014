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
import java.util.HashMap;
import app.models.*;

// TODO: should I use inheritance here to reduce duplication?
class TestEnvironment implements IEnvironment {
  private HashMap<Point, Square> squares;

  public TestEnvironment() {
    this.squares = new HashMap<Point, Square>();

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        this.squares.put(new Point(i,j), new Square());
      }
    }
  }

  public HashMap<Point, Square> getSquares() { return this.squares; }
  public int size() { return 3*3; }
  public int numberOfMice() { return 0; }
  public int numberOfStones() { return 0; }
  public int numberOfOwls() { return 0; }

  public HashMap<Point, Square> getNeighborSquares(Point point) {
    HashMap<Point, Square> neighbors = new HashMap<Point, Square>();

    for (int i = -1; i <= 1; i++) {
      for (int j = -1; j <= 1; j++) {
        if (i == 0 && j == i) continue;

        Point currentPoint = new Point(point.getX()+i, point.getY()+j);
        Square currentSquare = this.squares.get(currentPoint);

        if (currentSquare != null) {
          neighbors.put(currentPoint, currentSquare);
        }
      }
    }

    return neighbors;
  }

  public void update() {}

  public void setSquare(Point point, Square square) {
    this.squares.put(point, square);
  }
}

@RunWith(JUnit4.class)
public class MouseTester {
  @Test
  public void canBeAlive() {
    Mouse mouse = new Mouse();

    assertFalse(mouse.isDead());
  }

  @Test
  public void canBeDead() {
    assertTrue(new Mouse(0).isDead());
    assertTrue(new Mouse(-10).isDead());
  }

  private Mouse mouse;
  private Owl owl;
  private Stone stone;
  private Square squareWithMouse;
  private Square squareWithStone;
  private Square squareWithOwl;
  private Square squareWithStoneAndMouse;
  private TestEnvironment env;
  private Point location;

  @Before
  public void setup() {
    location = new Point(1,1);

    mouse = new Mouse();
    owl = new Owl();
    stone = new Stone();

    squareWithMouse = new Square();
    squareWithMouse.add(mouse);

    squareWithStone = new Square();
    squareWithStone.add(stone);

    squareWithOwl = new Square();
    squareWithOwl.add(owl);

    squareWithStoneAndMouse = new Square();
    squareWithStoneAndMouse.add(stone);
    squareWithStoneAndMouse.add(mouse);

    env = new TestEnvironment();
  }

  @Test
  public void if_it_sees_an_owl_and_is_under_a_stone_then_it_doesnt_move() {
    env.setSquare(new Point(0,0), squareWithOwl);
    env.setSquare(location, squareWithStone);

    Point choice = mouse.makeMove(new Point(1,1), env);
    assertTrue(new Point(1,1).equals(choice));
  }

  @Test
  public void if_it_sees_an_owl_and_a_free_stone_it_moves_there() {
    env.setSquare(new Point(0,0), squareWithOwl);
    env.setSquare(new Point(2,1), squareWithStone);

    Point choice = mouse.makeMove(location, env);
    assertTrue(new Point(2,1).equals(choice));
  }

  @Test
  public void if_it_sees_an_owl_and_no_free_stone_it_moves_away_from_the_owl() {
    env.setSquare(new Point(0,0), squareWithOwl);
    env.setSquare(new Point(2,1), squareWithStoneAndMouse);

    Point choice = mouse.makeMove(location, env);
    assertFalse(new Point(0,0).equals(choice));
  }

  @Test
  public void makes_a_random_choice_otherwise() {
    Point choice = mouse.makeMove(location, env);

    assertTrue(env.getSquares().get(choice).canHaveAdded(mouse));
    assertFalse(new Point(1,1).equals(choice));
  }

  @Test
  public void looses_life_when_making_moves() {
    Point choice = mouse.makeMove(location, env);

    assertEquals(19, mouse.getLife());
  }

  @Test
  public void stays_when_cannot_move() {
    Square filledSquare = new Square();
    filledSquare.add(new Mouse());
    filledSquare.add(new Mouse());

    env.setSquare(new Point(0,0), filledSquare);
    env.setSquare(new Point(1,0), filledSquare);
    env.setSquare(new Point(2,0), filledSquare);
    env.setSquare(new Point(0,1), filledSquare);
    env.setSquare(new Point(2,1), filledSquare);
    env.setSquare(new Point(0,2), filledSquare);
    env.setSquare(new Point(1,2), filledSquare);
    env.setSquare(new Point(2,2), filledSquare);

    Point choice = mouse.makeMove(location, env);

    assertTrue(choice.equals(location));
  }

  @Test
  public void dead_mice_dont_move() {
    mouse = new Mouse(0);
    mouse.makeMove(location, env);

    Point choice = mouse.makeMove(location, env);

    assertTrue(choice.equals(location));
  }

  @Test
  public void knowsWhereItCanReproduceTo() {
    Square filledSquare = new Square();
    filledSquare.add(new Mouse());
    filledSquare.add(new Mouse());

    env.setSquare(new Point(0,0), filledSquare);
    env.setSquare(new Point(1,0), filledSquare);
    env.setSquare(new Point(2,0), filledSquare);
    env.setSquare(new Point(0,1), filledSquare);
    env.setSquare(new Point(1,1), filledSquare);
    env.setSquare(new Point(2,1), filledSquare);
    env.setSquare(new Point(0,2), squareWithOwl);
    env.setSquare(new Point(1,2), filledSquare);
    // square 2,2 is empty, so thats where it should move

    Point choice = mouse.birthPlace(location, env);

    assertTrue(choice.equals(new Point(2,2)));
  }
}
