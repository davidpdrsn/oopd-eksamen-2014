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
import java.util.Set;
import java.util.HashMap;
import app.models.*;
import app.views.*;
import test.util.TestEnvironment;

@RunWith(JUnit4.class)
public class OwlTester {
  private Mouse mouse;
  private Owl owl;
  private Stone stone;
  private Square square;
  private Square squareWithMouse;
  private Square squareWithTwoMice;
  private Square squareWithStone;
  private Square squareWithOwl;
  private Square squareWithStoneAndMouse;
  private Square squareWithStoneAndTwoMice;
  private TestEnvironment env;
  private Point location;

  @Before
  public void setup() {
    location = new Point(1,1);

    mouse = new Mouse();
    owl = new Owl();
    stone = new Stone();

    square = new Square();

    squareWithMouse = new Square();
    squareWithMouse.add(mouse);

    squareWithTwoMice = new Square();
    squareWithTwoMice.add(mouse);
    squareWithTwoMice.add(mouse);

    squareWithStone = new Square();
    squareWithStone.add(stone);

    squareWithOwl = new Square();
    squareWithOwl.add(owl);

    squareWithStoneAndMouse = new Square();
    squareWithStoneAndMouse.add(stone);
    squareWithStoneAndMouse.add(mouse);

    squareWithStoneAndTwoMice = new Square();
    squareWithStoneAndTwoMice.add(stone);
    squareWithStoneAndTwoMice.add(mouse);
    squareWithStoneAndTwoMice.add(mouse);

    env = new TestEnvironment();
  }

  @Test
  public void it_moves_towards_mice() {
    // TODO: write this test!
  }

  @Test
  public void it_moves_to_where_there_is_one_mouse() {
    for (Point aPoint : env.getNeighborSquares(location).keySet()) {
      env.setSquare(aPoint, squareWithOwl);
    }
    env.setSquare(new Point(1,2), squareWithMouse);

    Point choice = owl.newLocation(location, env);

    assertTrue(choice.equals(new Point(1,2)));
  }

  @Test
  public void it_moves_to_where_there_are_two_mice() {
    for (Point aPoint : env.getNeighborSquares(location).keySet()) {
      env.setSquare(aPoint, squareWithOwl);
    }
    env.setSquare(new Point(0,0), squareWithTwoMice);

    Point choice = owl.newLocation(location, env);

    assertTrue(choice.equals(new Point(0,0)));
  }

  @Test
  public void it_moves_to_where_there_is_one_stone_and_one_mouse() {
    for (Point aPoint : env.getNeighborSquares(location).keySet()) {
      env.setSquare(aPoint, squareWithOwl);
    }
    env.setSquare(new Point(0,0), squareWithStoneAndMouse);

    Point choice = owl.newLocation(location, env);

    assertTrue(choice.equals(new Point(0,0)));
  }

  @Test
  public void it_moves_to_where_there_is_one_stone_and_two_mice() {
    for (Point aPoint : env.getNeighborSquares(location).keySet()) {
      env.setSquare(aPoint, squareWithOwl);
    }
    env.setSquare(new Point(0,0), squareWithStoneAndTwoMice);

    Point choice = owl.newLocation(location, env);

    assertTrue(choice.equals(new Point(0,0)));
  }

  @Test
  public void it_doesnt_move_to_where_there_are_owls() {
    for (Point aPoint : env.getNeighborSquares(location).keySet()) {
      env.setSquare(aPoint, squareWithOwl);
    }
    env.setSquare(new Point(0,0), square);

    Point choice = owl.newLocation(location, env);

    assertTrue(choice.equals(new Point(0,0)));
  }

  @Test
  public void it_stays_if_it_cant_move() {
    for (Point aPoint : env.getNeighborSquares(location).keySet()) {
      env.setSquare(aPoint, squareWithOwl);
    }

    Point choice = owl.newLocation(location, env);

    assertTrue(choice.equals(location));
  }

  @Test
  public void it_doesnt_go_off_the_grid() {
    // TODO: write this test!
  }

  @Test
  public void it_doesnt_move_further_than_its_neighbors() {
    // TODO: write this test!
  }

  private void render(Environment env) {
    new EnvironmentView(env, SquareEmojiView.class).render();
  }
}
