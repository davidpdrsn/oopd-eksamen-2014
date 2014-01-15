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

@RunWith(JUnit4.class)
public class EnvironmentTester {
  Environment env;

  @Before
  public void setup() {
    env = new Environment();
  }

  @Test
  public void hasAGrid() {
    assertEquals(env.SIZE * env.SIZE, env.size());
  }

  @Test
  public void containsANumberOfStones() {
    assertEquals(env.NUMBER_OF_STONES, env.numberOfStones());
  }

  @Test
  public void containsANumberOfMice() {
    assertEquals(env.NUMBER_OF_MICE, env.numberOfMice());
  }

  @Test
  public void containsANumberOfOwls() {
    assertEquals(env.NUMBER_OF_OWLS, env.numberOfOwls());
  }
}
