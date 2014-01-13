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

import app.models.Square;
import app.models.Environment;
import app.models.Point;

@RunWith(JUnit4.class)
public class EnvironmentTester {
  Environment env;

  @Before
  public void setup() {
    env = new Environment();
  }

  @Test
  public void it_has_a_grid() {
    assertThat(env.size(), is(20*20));
  }

  @Test
  public void it_knows_if_a_square_is_empty() {
    assertTrue(env.isEmpty(new Point(10, 10)));
  }

  @Test
  @Ignore
  public void it_knows_if_a_square_is_not_empty() {
    // TODO: it_knows_if_a_square_is_not_empty
  }

  @Test
  @Ignore
  public void it_contains_ten_stones() {
    assertEquals(10, env.numberOfStones());
  }
}
