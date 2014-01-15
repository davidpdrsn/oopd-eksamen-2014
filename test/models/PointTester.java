package test.models;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.models.Point;

@RunWith(MockitoJUnitRunner.class)
public class PointTester {
  Point point;

  @Before public void setup() {
    point = new Point(10, 20);
  }

  @Test public void get_x() {
    assertThat(point.getX(), is(10));
  }

  @Test public void get_y() {
    assertThat(point.getY(), is(20));
  }

  @Test public void are_compared_by_coordinates() {
    assertTrue(new Point(10, 20).equals(point));
    assertFalse(point.equals("strings are not points"));
    assertFalse(point.equals(null));
  }

  @Test
  public void hashCode_test() {
    assertEquals(new Point(10, 20).hashCode(), new Point(10, 20).hashCode());
  }

  @Test
  public void within_a_range() {
    Point point = Point.randomWithin(0, 10);

    assertTrue(0 <= point.getX() && point.getY() <= 10);
    assertTrue(0 <= point.getY() && point.getY() <= 10);
  }
}
