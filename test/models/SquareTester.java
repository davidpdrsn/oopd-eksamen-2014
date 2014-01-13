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

import app.models.*;

@RunWith(JUnit4.class)
public class SquareTester {
  Square square;

  @Before
  public void setup() {
    square = new Square();
  }

  @Test
  public void hasOneEntityToBeginWith() {
    assertEquals(0, square.getNumberOfEntities());
  }

  @Test
  public void canHaveEntitiesAdded() {
    Stone stone = new Stone();
    square.add(stone);

    assertTrue(square.contains(stone));
  }

  @Test
  public void cannotContainTwoStones() {
    Stone stone = new Stone();
    square.add(stone);

    assertFalse(square.canHaveAdded(stone));
  }

  @Test
  public void cannotContainTwoOwls() {
    Owl owl = new Owl();
    square.add(owl);

    assertFalse(square.canHaveAdded(owl));
  }

  @Test
  public void canContainTwoMice() {
    Mouse mouse = new Mouse();
    square.add(mouse);

    assertTrue(square.canHaveAdded(mouse));
  }

  @Test
  public void cannotContainMoreThanTwoMice() {
    Mouse mouse = new Mouse();
    square.add(mouse);
    square.add(mouse);

    assertFalse(square.canHaveAdded(mouse));
  }

  @Test
  public void cannotMoreThanThreeEntities() {
    Stone stone = new Stone();
    Mouse mouse = new Mouse();
    Mouse anotherMouse = new Mouse();
    Owl owl = new Owl();

    square.add(stone);
    square.add(mouse);
    square.add(owl);

    assertFalse(square.canHaveAdded(anotherMouse));
  }

  @Test
  public void canHaveEntitiesRemoved() {
    // TODO: find out how to do this.
  }
}
