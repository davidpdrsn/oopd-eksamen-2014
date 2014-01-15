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
  public void canBeEmpty() {
    assertTrue(square.isEmpty());
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
  public void canContainTwoMiceAndOneOwl() {
    // TODO: write this test, and implement it!
  }

  @Test
  public void state_when_empty() {
    assertEquals(SquareState.EMPTY, square.getState());
  }

  @Test
  public void state_when_containing_stone() {
    square.add(new Stone());

    assertEquals(SquareState.STONE, square.getState());
  }

  @Test
  public void state_when_containing_owl() {
    square.add(new Owl());

    assertEquals(SquareState.OWL, square.getState());
  }

  @Test
  public void state_when_containing_mouse() {
    square.add(new Mouse());

    assertEquals(SquareState.MOUSE, square.getState());
  }

  @Test
  public void state_when_containing_stone_and_owl() {
    square.add(new Owl());
    square.add(new Stone());

    assertEquals(SquareState.OWL_STONE, square.getState());
  }

  @Test
  public void state_when_containing_stone_and_mouse() {
    square.add(new Mouse());
    square.add(new Stone());

    assertEquals(SquareState.STONE_MOUSE, square.getState());
  }

  @Test
  public void state_when_containing_owl_and_mouse() {
    square.add(new Mouse());
    square.add(new Owl());

    assertEquals(SquareState.OWL_MOUSE, square.getState());
  }

  @Test
  public void state_when_containing_two_mice() {
    square.add(new Mouse());
    square.add(new Mouse());

    assertEquals(SquareState.TWO_MICE, square.getState());
  }

  @Test
  public void state_when_containing_stone_owl_mouse() {
    square.add(new Owl());
    square.add(new Stone());
    square.add(new Mouse());

    assertEquals(SquareState.OWL_STONE_MOUSE, square.getState());
  }

  @Test
  public void state_when_containing_stone_two_mice() {
    square.add(new Mouse());
    square.add(new Mouse());
    square.add(new Stone());

    assertEquals(SquareState.STONE_TWO_MICE, square.getState());
  }

  @Test
  public void get_mice() {
    square.add(new Mouse());
    square.add(new Mouse());

    assertEquals(2, square.getMice().size());
  }

  @Test
  public void remove() {
    Mouse aMouse = new Mouse();
    square.add(aMouse);
    square.add(new Mouse());

    square.remove(aMouse);

    assertEquals(1, square.getMice().size());
    assertTrue(square.getMice().get(0) != aMouse);
  }

  @Test
  public void containsNumberOfMice_test() {
    square.add(new Stone());
    square.add(new Mouse());
    square.add(new Mouse());

    assertEquals(2, square.containsNumberOfMice());
  }

  @Test
  public void containsMouse_test() {
    square.add(new Mouse());
    square.add(new Stone());
    square.add(new Owl());

    assertTrue(square.containsMouse());
  }

  @Test
  public void knowsWhenItCanReproduce() {
    square.add(new Mouse());
    square.add(new Mouse());

    assertTrue(square.reproductionCanHappenHere());
  }

  @Test
  public void knowsWhenItCannotReproduce() {
    square.add(new Mouse());

    assertFalse(square.reproductionCanHappenHere());
  }

  @Test
  public void knowsThatItContainsAnEdibleMouseWhenThereIsOneMouse() {
    square.add(new Mouse());

    assertTrue(square.containsEdibleMouse());
  }

  @Test
  public void knowsThatItContainsAnEdibleMouseWhenThereIsTwoMice() {
    square.add(new Mouse());
    square.add(new Mouse());

    assertTrue(square.containsEdibleMouse());
  }

  @Test
  public void knowsThatItContainsAnEdibleMouseWhenThereIsTwoMiceAndOneStone() {
    square.add(new Mouse());
    square.add(new Stone());
    square.add(new Mouse());

    assertTrue(square.containsEdibleMouse());
  }

  @Test
  public void knowsThatItContainsNoEdibleMouseWhenThereAreNoMice() {
    assertFalse(square.containsEdibleMouse());
  }

  @Test
  public void knowsThatItContainsNoEdibleMouseWhenThereAreOneMiceAndOneStone() {
    square.add(new Mouse());
    square.add(new Stone());

    assertFalse(square.containsEdibleMouse());
  }
}
