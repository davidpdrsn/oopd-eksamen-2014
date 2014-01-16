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
import app.views.*;
import test.util.TestEnvironment;

@RunWith(JUnit4.class)
public class OwlTester {
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
  public void theyDontMoveToSquareWhereThereAreOwls() {
    env.setSquare(new Point(0,0), squareWithOwl);
    env.setSquare(new Point(1,0), squareWithOwl);
    env.setSquare(new Point(2,0), squareWithOwl);
    env.setSquare(new Point(0,1), squareWithOwl);
    // env.setSquare(new Point(1,1), squareWithOwl);
    // env.setSquare(new Point(2,1), squareWithOwl);
    env.setSquare(new Point(0,2), squareWithOwl);
    env.setSquare(new Point(1,2), squareWithOwl);
    env.setSquare(new Point(2,2), squareWithOwl);

    Point choice = owl.newLocation(location, env);

    assertTrue(new Point(2,1).equals(choice));
  }

  @Test
  public void theyStayIfTheyCannotMove() {
    env.setSquare(new Point(0,0), squareWithOwl);
    env.setSquare(new Point(1,0), squareWithOwl);
    env.setSquare(new Point(2,0), squareWithOwl);
    env.setSquare(new Point(0,1), squareWithOwl);
    // env.setSquare(new Point(1,1), squareWithOwl);
    env.setSquare(new Point(2,1), squareWithOwl);
    env.setSquare(new Point(0,2), squareWithOwl);
    env.setSquare(new Point(1,2), squareWithOwl);
    env.setSquare(new Point(2,2), squareWithOwl);

    Point choice = owl.newLocation(location, env);

    assertTrue(new Point(1,1).equals(choice));
  }

  @Test
  public void ifTheySeeAMouseCloseThenTheyMoveTowardsIt() {
    env.setSquare(new Point(0,0), squareWithMouse);

    Point choice = owl.newLocation(location, env);

    assertTrue(new Point(0,0).equals(choice));
  }

  @Test
  public void ifTheySeeAMouseFarAwayThenTheyMoveTowardsIt_1() {
    env.setSquare(new Point(3,2), squareWithMouse);

    Point choice = owl.newLocation(location, env);

    // env.setSquare(location, squareWithStone);
    // env.setSquare(choice, squareWithOwl);
    // new EnvironmentView(env, SquareEmojiView.class).render();
    // System.out.printf(" %d - %d", choice.getX(), choice.getY());

    assertEquals(2, choice.getX());
    assertEquals(1, choice.getY());
  }

  @Test
  public void ifTheySeeAMouseFarAwayThenTheyMoveTowardsIt_2() {
    location = new Point(2,2);
    env.setSquare(new Point(0,0), squareWithMouse);

    Point choice = owl.newLocation(location, env);

    assertEquals(1, choice.getX());
    assertEquals(1, choice.getY());
  }

  @Test
  public void ifTheyDonotSeeAMouseThenTheyMoveAtRandom() {
    Point choice = owl.newLocation(location, env);

    assertTrue(env.getSquares().get(choice).canHaveAdded(owl));
  }
}
