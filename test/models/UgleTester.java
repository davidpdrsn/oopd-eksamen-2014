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
import test.util.TestMiljo;

@RunWith(JUnit4.class)
public class UgleTester {
  private Mus mouse;
  private Ugle owl;
  private Sten stone;
  private Square square;
  private Square squareWithMus;
  private Square squareWithTwoMice;
  private Square squareWithSten;
  private Square squareWithUgle;
  private Square squareWithStenAndMus;
  private Square squareWithStenAndTwoMice;
  private Square squareWithUgleAndMus;
  private TestMiljo env;
  private Point location;

  @Before
  public void setup() {
    location = new Point(1,1);

    mouse = new Mus();
    owl = new Ugle();
    stone = new Sten();

    square = new Square();

    squareWithMus = new Square();
    squareWithMus.add(mouse);

    squareWithTwoMice = new Square();
    squareWithTwoMice.add(mouse);
    squareWithTwoMice.add(mouse);

    squareWithSten = new Square();
    squareWithSten.add(stone);

    squareWithUgle = new Square();
    squareWithUgle.add(owl);

    squareWithUgleAndMus = new Square();
    squareWithUgleAndMus.add(owl);
    squareWithUgleAndMus.add(mouse);

    squareWithStenAndMus = new Square();
    squareWithStenAndMus.add(stone);
    squareWithStenAndMus.add(mouse);

    squareWithStenAndTwoMice = new Square();
    squareWithStenAndTwoMice.add(stone);
    squareWithStenAndTwoMice.add(mouse);
    squareWithStenAndTwoMice.add(mouse);

    env = new TestMiljo();
  }

  @Test
  public void it_moves_towards_mice() {
    location = new Point(10, 10);
    env.setSquare(location, squareWithUgle);
    env.setSquare(new Point(8, 8), squareWithMus);

    Point choice = owl.newLocation(location, env);

    assertTrue(choice.equals(new Point(9,9)));
  }

  @Test
  public void it_moves_to_where_there_is_one_mouse() {
    for (Point aPoint : env.getNeighborSquares(location).keySet()) {
      env.setSquare(aPoint, squareWithUgle);
    }
    env.setSquare(new Point(1,2), squareWithMus);

    Point choice = owl.newLocation(location, env);

    assertTrue(choice.equals(new Point(1,2)));
  }

  @Test
  public void it_moves_to_where_there_are_two_mice() {
    for (Point aPoint : env.getNeighborSquares(location).keySet()) {
      env.setSquare(aPoint, squareWithUgle);
    }
    env.setSquare(new Point(0,0), squareWithTwoMice);

    Point choice = owl.newLocation(location, env);

    assertTrue(choice.equals(new Point(0,0)));
  }

  @Test
  public void it_moves_to_where_there_is_one_stone_and_one_mouse() {
    for (Point aPoint : env.getNeighborSquares(location).keySet()) {
      env.setSquare(aPoint, squareWithUgle);
    }
    env.setSquare(new Point(0,0), squareWithStenAndMus);

    Point choice = owl.newLocation(location, env);

    assertTrue(choice.equals(new Point(0,0)));
  }

  @Test
  public void it_moves_to_where_there_is_one_stone_and_two_mice() {
    for (Point aPoint : env.getNeighborSquares(location).keySet()) {
      env.setSquare(aPoint, squareWithUgle);
    }
    env.setSquare(new Point(0,0), squareWithStenAndTwoMice);

    Point choice = owl.newLocation(location, env);

    assertTrue(choice.equals(new Point(0,0)));
  }

  @Test
  public void it_doesnt_move_to_where_there_are_owls() {
    for (Point aPoint : env.getNeighborSquares(location).keySet()) {
      env.setSquare(aPoint, squareWithUgle);
    }
    env.setSquare(new Point(0,0), square);

    Point choice = owl.newLocation(location, env);

    assertTrue(choice.equals(new Point(0,0)));
  }

  @Test
  public void it_doesnt_move_to_where_there_are_owls_and_mice() {
    for (Point aPoint : env.getNeighborSquares(location).keySet()) {
      env.setSquare(aPoint, squareWithUgle);
    }
    env.setSquare(new Point(0,0), squareWithUgleAndMus);

    Point choice = owl.newLocation(location, env);

    assertTrue(choice.equals(location));
  }

  @Test
  public void it_stays_if_it_cant_move() {
    for (Point aPoint : env.getNeighborSquares(location).keySet()) {
      env.setSquare(aPoint, squareWithUgle);
    }

    Point choice = owl.newLocation(location, env);

    assertTrue(choice.equals(location));
  }

  @Test
  public void it_doesnt_move_further_than_its_neighbors() {
    location = new Point(10, 10);
    env.setSquare(location, squareWithUgle);

    for (int i = 0; i < 10; i++) {
      Point choice = owl.newLocation(location, env);
      assertTrue(env.getNeighborSquares(location).keySet().contains(choice));
    }
  }

  private void render(Miljo env) {
    new MiljoView(env, SquareEmojiView.class).render();
  }
}
