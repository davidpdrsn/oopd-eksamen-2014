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
    Sten stone = new Sten();
    square.add(stone);

    assertTrue(square.contains(stone));
  }

  @Test
  public void state_when_empty() {
    assertEquals(SquareState.EMPTY, square.getState());
  }

  @Test
  public void state_when_containing_stone() {
    square.add(new Sten());

    assertEquals(SquareState.STONE, square.getState());
  }

  @Test
  public void state_when_containing_owl() {
    square.add(new Ugle());

    assertEquals(SquareState.OWL, square.getState());
  }

  @Test
  public void state_when_containing_mouse() {
    square.add(new Mus());

    assertEquals(SquareState.MOUSE, square.getState());
  }

  @Test
  public void state_when_containing_stone_and_owl() {
    square.add(new Ugle());
    square.add(new Sten());

    assertEquals(SquareState.OWL_STONE, square.getState());
  }

  @Test
  public void state_when_containing_stone_and_mouse() {
    square.add(new Mus());
    square.add(new Sten());

    assertEquals(SquareState.STONE_MOUSE, square.getState());
  }

  @Test
  public void state_when_containing_owl_and_mouse() {
    square.add(new Mus());
    square.add(new Ugle());

    assertEquals(SquareState.OWL_MOUSE, square.getState());
  }

  @Test
  public void state_when_containing_two_mice() {
    square.add(new Mus());
    square.add(new Mus());

    assertEquals(SquareState.TWO_MICE, square.getState());
  }

  @Test
  public void state_when_containing_stone_owl_mouse() {
    square.add(new Ugle());
    square.add(new Sten());
    square.add(new Mus());

    assertEquals(SquareState.OWL_STONE_MOUSE, square.getState());
  }

  @Test
  public void state_when_containing_stone_two_mice() {
    square.add(new Mus());
    square.add(new Mus());
    square.add(new Sten());

    assertEquals(SquareState.STONE_TWO_MICE, square.getState());
  }

  @Test
  public void get_mice() {
    square.add(new Mus());
    square.add(new Mus());

    assertEquals(2, square.getMice().size());
  }

  @Test
  public void remove() {
    Mus aMus = new Mus();
    square.add(aMus);
    square.add(new Mus());

    square.remove(aMus);

    assertEquals(1, square.getMice().size());
    assertTrue(square.getMice().get(0) != aMus);
  }

  @Test
  public void containsNumberOfMice_test() {
    square.add(new Sten());
    square.add(new Mus());
    square.add(new Mus());

    assertEquals(2, square.containsNumberOfMice());
  }

  @Test
  public void containsMus_test() {
    square.add(new Mus());
    square.add(new Sten());
    square.add(new Ugle());

    assertTrue(square.containsMus());
  }

  @Test
  public void knowsWhenItCanReproduce() {
    square.add(new Mus());
    square.add(new Mus());

    assertTrue(square.reproductionCanHappenHere());
  }

  @Test
  public void knowsWhenItCannotReproduce() {
    square.add(new Mus());

    assertFalse(square.reproductionCanHappenHere());
  }

  @Test
  public void knowsThatItContainsAnEdibleMusWhenThereIsOneMus() {
    square.add(new Mus());

    assertTrue(square.containsEdibleMus());
  }

  @Test
  public void knowsThatItContainsAnEdibleMusWhenThereIsTwoMice() {
    square.add(new Mus());
    square.add(new Mus());

    assertTrue(square.containsEdibleMus());
  }

  @Test
  public void knowsThatItContainsAnEdibleMusWhenThereIsTwoMiceAndOneSten() {
    square.add(new Mus());
    square.add(new Sten());
    square.add(new Mus());

    assertTrue(square.containsEdibleMus());
  }

  @Test
  public void knowsThatItContainsNoEdibleMusWhenThereAreNoMice() {
    assertFalse(square.containsEdibleMus());
  }

  @Test
  public void knowsThatItContainsNoEdibleMusWhenThereAreOneMiceAndOneSten() {
    square.add(new Mus());
    square.add(new Sten());

    assertFalse(square.containsEdibleMus());
  }
}
