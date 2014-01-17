package test.models;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import org.junit.Before;
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
  public void has_one_entity_to_begin_with() {
    assertEquals(0, square.getNumberOfEntities());
  }

  @Test
  public void can_be_empty() {
    assertTrue(square.isEmpty());
  }

  @Test
  public void can_have_entities_added() {
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
  public void can_have_things_removed() {
    Mus aMus = new Mus();
    square.add(aMus);
    square.add(new Mus());

    square.remove(aMus);

    assertEquals(1, square.getMice().size());
    assertTrue(square.getMice().get(0) != aMus);
  }

  @Test
  public void contains_number_of_mice() {
    square.add(new Sten());
    square.add(new Mus());
    square.add(new Mus());

    assertEquals(2, square.containsNumberOfMice());
  }

  @Test
  public void can_contain_mice() {
    square.add(new Mus());
    square.add(new Sten());
    square.add(new Ugle());

    assertTrue(square.containsMus());
  }

  @Test
  public void knows_when_mice_can_reproduce_here() {
    square.add(new Mus());
    square.add(new Mus());

    assertTrue(square.reproductionCanHappenHere());
  }

  @Test
  public void knows_when_mice_cannot_reproduce_here() {
    square.add(new Mus());

    assertFalse(square.reproductionCanHappenHere());
  }

  @Test
  public void knows_that_it_contains_and_edible_mouse_when_there_is_one_mouse() {
    square.add(new Mus());

    assertTrue(square.containsEdibleMus());
  }

  @Test
  public void knows_that_it_contains_an_edible_mouse_when_there_is_two_mice() {
    square.add(new Mus());
    square.add(new Mus());

    assertTrue(square.containsEdibleMus());
  }

  @Test
  public void knows_that_it_contains_an_edible_mouse_when_there_is_two_mice_and_one_stone() {
    square.add(new Mus());
    square.add(new Sten());
    square.add(new Mus());

    assertTrue(square.containsEdibleMus());
  }

  @Test
  public void knows_that_it_contains_no_edible_mouse_when_there_are_no_mice() {
    assertFalse(square.containsEdibleMus());
  }

  @Test
  public void knows_that_it_contains_no_edible_mouse_when_there_is_one_mouse_and_one_stone() {
    square.add(new Mus());
    square.add(new Sten());

    assertFalse(square.containsEdibleMus());
  }
}
