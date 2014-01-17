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
import test.util.TestMiljo;

@RunWith(JUnit4.class)
public class MusseTester {
  private Mus mouse;
  private Ugle owl;
  private Sten stone;
  private Square squareWithMus;
  private Square squareWithSten;
  private Square squareWithUgle;
  private Square squareWithStenAndMus;
  private TestMiljo env;
  private Point location;

  @Before
  public void setup() {
    location = new Point(1,1);

    mouse = new Mus();
    owl = new Ugle();
    stone = new Sten();

    squareWithMus = new Square();
    squareWithMus.add(mouse);

    squareWithSten = new Square();
    squareWithSten.add(stone);

    squareWithUgle = new Square();
    squareWithUgle.add(owl);

    squareWithStenAndMus = new Square();
    squareWithStenAndMus.add(stone);
    squareWithStenAndMus.add(mouse);

    env = new TestMiljo();
  }

  @Test
  public void canBeAlive() {
    Mus mouse = new Mus();

    assertFalse(mouse.isDead());
  }

  @Test
  public void canBeDead() {
    assertTrue(new Mus(0).isDead());
    assertTrue(new Mus(-10).isDead());
  }

  @Test
  public void if_it_sees_an_owl_and_is_under_a_stone_then_it_doesnt_move() {
    env.setSquare(new Point(0,0), squareWithUgle);
    env.setSquare(location, squareWithSten);

    Point choice = mouse.newLocation(new Point(1,1), env);
    assertTrue(new Point(1,1).equals(choice));
  }

  @Test
  public void if_it_sees_an_owl_and_a_free_stone_it_moves_there() {
    env.setSquare(new Point(0,0), squareWithUgle);
    env.setSquare(new Point(2,1), squareWithSten);

    Point choice = mouse.newLocation(location, env);
    assertTrue(new Point(2,1).equals(choice));
  }

  @Test
  public void if_it_sees_an_owl_and_no_free_stone_it_moves_away_from_the_owl() {
    env.setSquare(new Point(0,0), squareWithUgle);
    env.setSquare(new Point(2,1), squareWithStenAndMus);

    Point choice = mouse.newLocation(location, env);
    assertFalse(new Point(0,0).equals(choice));
  }

  @Test
  public void makes_a_random_choice_otherwise() {
    Point choice = mouse.newLocation(location, env);

    assertTrue(env.getSquares().get(choice).canHaveAdded(mouse));
    assertFalse(new Point(1,1).equals(choice));
  }

  @Test
  public void looses_life_when_making_moves() {
    Point choice = mouse.newLocation(location, env);

    assertEquals(19, mouse.getLife());
  }

  @Test
  public void stays_when_cannot_move() {
    Square filledSquare = new Square();
    filledSquare.add(new Mus());
    filledSquare.add(new Mus());

    env.setSquare(new Point(0,0), filledSquare);
    env.setSquare(new Point(1,0), filledSquare);
    env.setSquare(new Point(2,0), filledSquare);
    env.setSquare(new Point(0,1), filledSquare);
    env.setSquare(new Point(2,1), filledSquare);
    env.setSquare(new Point(0,2), filledSquare);
    env.setSquare(new Point(1,2), filledSquare);
    env.setSquare(new Point(2,2), filledSquare);

    Point choice = mouse.newLocation(location, env);

    assertTrue(choice.equals(location));
  }

  @Test
  public void dead_mice_dont_move() {
    mouse = new Mus(0);
    mouse.newLocation(location, env);

    Point choice = mouse.newLocation(location, env);

    assertTrue(choice.equals(location));
  }

  @Test
  public void knowsWhereItCanReproduceTo() {
    Square filledSquare = new Square();
    filledSquare.add(new Mus());
    filledSquare.add(new Mus());

    env.setSquare(new Point(0,0), filledSquare);
    env.setSquare(new Point(1,0), filledSquare);
    env.setSquare(new Point(2,0), filledSquare);
    env.setSquare(new Point(0,1), filledSquare);
    env.setSquare(new Point(1,1), filledSquare);
    env.setSquare(new Point(2,1), filledSquare);
    env.setSquare(new Point(0,2), squareWithUgle);
    env.setSquare(new Point(1,2), filledSquare);
    // square 2,2 is empty, so thats where it should move

    Point choice = mouse.birthPlace(location, env);

    assertTrue(choice.equals(new Point(2,2)));
  }
}
