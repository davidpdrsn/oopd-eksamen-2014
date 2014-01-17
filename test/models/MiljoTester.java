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
  public void hasAGrid() {
    assertEquals(env.SIZE * env.SIZE, env.size());
  }

  @Test
  public void containsANumberOfStens() {
    assertEquals(env.NUMBER_OF_STONES, env.numberOfStens());
  }

  @Test
  public void containsANumberOfMice() {
    assertEquals(env.NUMBER_OF_MICE, env.numberOfMice());
  }

  @Test
  public void containsANumberOfUgles() {
    assertEquals(env.NUMBER_OF_OWLS, env.numberOfUgles());
  }

  @Test
  public void theyNeverDisappear() {
    for (int i = 0; i < 100; i++) {
      Miljo anEnv = new Miljo();

      for (int j = 0; j < 100; j++) {
        anEnv.update();
        assertEquals(2, anEnv.numberOfUgles());
      }
    }
  }

  private void render(Miljo env) {
    new MiljoView(env, SquareEmojiView.class).render();
  }
}
