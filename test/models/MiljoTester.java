package test.models;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.HashMap;
import app.models.*;
import app.views.*;
import test.util.TestMiljo;

@RunWith(JUnit4.class)
public class MiljoTester {
  Miljo env;

  @Before
  public void setup() {
    env = new Miljo();
  }

  @Test
  public void has_a_grid() {
    assertEquals(env.SIZE * env.SIZE, env.size());
  }

  @Test
  public void has_stones() {
    assertEquals(env.NUMBER_OF_STONES, env.numberOfStens());
  }

  @Test
  public void has_mice() {
    assertEquals(env.NUMBER_OF_MICE, env.numberOfMice());
  }

  @Test
  public void has_owls() {
    assertEquals(env.NUMBER_OF_OWLS, env.numberOfUgles());
  }

  @Test
  public void owls_never_disappear() {
    // It was a bug for a long time that owls for some reason
    // disappeared, so this test is here to make sure the bug doens't come back
    for (int i = 0; i < 100; i++) {
      Miljo anEnv = new Miljo();

      for (int j = 0; j < 100; j++) {
        anEnv.update();
        assertEquals(2, anEnv.numberOfUgles());
      }
    }
  }
}
