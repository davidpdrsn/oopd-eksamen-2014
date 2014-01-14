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

@RunWith(JUnit4.class)
public class MouseTester {
  @Test
  public void canBeAlive() {
    Mouse mouse = new Mouse();

    assertFalse(mouse.isDead());
  }

  @Test
  public void canBeDead() {
    Mouse mouse = new Mouse(0);

    assertTrue(mouse.isDead());
  }

  private HashMap<Point, Square> neighborSquares;
  private Square squareWithStone;
  private Square squareWithStoneAndMouse;
  private Square squareWithOwl;
  private Mouse mouse;

  @Before
  public void setup() {
    neighborSquares = new HashMap<Point, Square>();
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        neighborSquares.put(new Point(i,j), new Square());
      }
    }

    mouse = new Mouse();

    squareWithOwl = new Square();
    squareWithOwl.add(new Owl());

    squareWithStone = new Square();
    squareWithStone.add(new Stone());

    squareWithStoneAndMouse = new Square();
    squareWithStoneAndMouse.add(new Mouse());
    squareWithStoneAndMouse.add(new Stone());
  }

  @Test
  public void if_it_sees_an_owl_and_is_under_a_stone_then_it_doesnt_move() throws DeadMiceDoNotMoveException {
    neighborSquares.put(new Point(0, 0), squareWithOwl);
    neighborSquares.put(new Point(1, 1), squareWithStone);

    Point choice = mouse.makeMove(neighborSquares);
    assertTrue(new Point(1,1).equals(choice));
  }

  @Test
  public void if_it_sees_an_owl_and_a_free_stone_it_moves_there() throws DeadMiceDoNotMoveException {
    neighborSquares.put(new Point(0, 0), squareWithOwl);
    neighborSquares.put(new Point(2, 1), squareWithStone);

    Point choice = mouse.makeMove(neighborSquares);
    assertTrue(new Point(2,1).equals(choice));
  }

  @Test
  public void if_it_sees_an_owl_and_no_free_stone_it_moves_away_from_the_owl() throws DeadMiceDoNotMoveException {

    neighborSquares.put(new Point(0, 0), squareWithOwl);
    neighborSquares.put(new Point(2, 1), squareWithStoneAndMouse);

    Point choice = mouse.makeMove(neighborSquares);
    assertFalse(new Point(0,0).equals(choice));
  }

  @Test
  public void makes_a_random_choice_otherwise() throws DeadMiceDoNotMoveException {
    Point choice = mouse.makeMove(neighborSquares);
    assertTrue(neighborSquares.get(choice).canHaveAdded(mouse));
    assertFalse(new Point(1,1).equals(choice));
  }

  @Test
  public void looses_life_when_making_moves() throws DeadMiceDoNotMoveException {
    mouse.makeMove(neighborSquares);
    assertEquals(19, mouse.getLife());
  }

  @Test
  public void dead_mice_dont_move() throws DeadMiceDoNotMoveException {
    try {
      for (int i = 0; i < 21; i++) {
        mouse.makeMove(neighborSquares);
      }
      fail("No exception raised");
    } catch (DeadMiceDoNotMoveException e) {
      // pass!
    }
  }
}
