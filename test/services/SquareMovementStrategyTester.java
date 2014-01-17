package test.services;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.*;
import app.services.*;
import app.models.*;

@RunWith(JUnit4.class)
public class SquareMovementStrategyTester {
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
    strategy = new SquareMovementStrategy(square);
  }

  @Test
  public void allows_one_mouse() {
    assertThat(strategy.allows(mouse), is(true));
  }

  @Test
  public void allows_one_owl() {
    assertThat(strategy.allows(owl), is(true));
  }

  @Test
  public void allows_one_stone_and_one_owl() {
    square.add(stone);
    assertThat(strategy.allows(owl), is(true));
  }

  @Test
  public void allows_one_stone_and_one_mouse() {
    square.add(stone);
    assertThat(strategy.allows(mouse), is(true));
  }

  @Test
  public void allows_one_stone_and_two_mice() {
    square.add(stone);
    square.add(mouse);
    assertThat(strategy.allows(mouse), is(true));
  }

  @Test
  public void allows_one_stone_one_mouse_and_one_owl() {
    square.add(stone);
    square.add(mouse);
    assertThat(strategy.allows(owl), is(true));
  }

  @Test
  public void allows_one_stone_two_mice_and_one_owl() {
    square.add(stone);
    square.add(mouse);
    square.add(mouse);
    assertThat(strategy.allows(owl), is(true));
  }

  @Test
  public void allows_one_mouse_and_one_owl() {
    square.add(mouse);
    assertThat(strategy.allows(owl), is(true));
  }

  @Test
  public void allows_two_mice_and_one_owl() {
    square.add(mouse);
    square.add(mouse);
    assertThat(strategy.allows(owl), is(true));
  }

  @Test
  public void doesnt_allow_three_mice() {
    square.add(mouse);
    square.add(mouse);
    assertThat(strategy.allows(mouse), is(false));
  }

  @Test
  public void doesnt_allow_two_owls() {
    square.add(owl);
    assertThat(strategy.allows(owl), is(false));
  }
}
