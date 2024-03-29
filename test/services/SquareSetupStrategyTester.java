package test.services;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import app.services.*;
import app.models.*;

@RunWith(JUnit4.class)
public class SquareSetupStrategyTester {
  private Square square;
  private EntityPositioningStrategy strategy;
  private Sten stone;
  private Mus mouse;
  private Ugle owl;

  @Before
  public void setup() {
    square = new Square();
    stone = new Sten();
    mouse = new Mus();
    owl = new Ugle();
    strategy = new SquareSetupStrategy(square);
  }

  @Test
  public void can_contain_one_mouse() {
    assertThat(strategy.allows(mouse), is(true));
  }

  @Test
  public void can_contain_two_mice() {
    square.add(mouse);
    assertThat(strategy.allows(mouse), is(true));
  }

  @Test
  public void can_contain_one_owl() {
    assertThat(strategy.allows(owl), is(true));
  }

  @Test
  public void can_contain_one_owl_and_one_stone() {
    square.add(stone);
    assertThat(strategy.allows(owl), is(true));
  }

  @Test
  public void can_contain_one_stone() {
    assertThat(strategy.allows(stone), is(true));
  }

  @Test
  public void can_contain_one_stone_and_one_mouse() {
    square.add(mouse);
    assertThat(strategy.allows(stone), is(true));
  }

  @Test
  public void can_contain_one_stone_and_two_mice() {
    square.add(mouse);
    square.add(mouse);
    assertThat(strategy.allows(stone), is(true));
  }

  @Test
  public void cannot_contain_one_mouse_and_one_owl() {
    square.add(owl);
    assertThat(strategy.allows(mouse), is(false));
  }

  @Test
  public void cannot_contain_two_mice_and_one_owl() {
    square.add(mouse);
    square.add(mouse);
    assertThat(strategy.allows(owl), is(false));
  }

  @Test
  public void cannot_contain_two_stones() {
    square.add(stone);
    assertThat(strategy.allows(stone), is(false));
  }

  @Test
  public void cannot_contain_two_owls() {
    square.add(owl);
    assertThat(strategy.allows(owl), is(false));
  }

  @Test
  public void cannot_contain_more_than_two_mice() {
    square.add(mouse);
    square.add(mouse);
    assertThat(strategy.allows(mouse), is(false));
  }

  @Test
  public void cannot_contain_more_than_three_entities() {
    square.add(mouse);
    square.add(mouse);
    assertThat(strategy.allows(owl), is(false));
  }
}
