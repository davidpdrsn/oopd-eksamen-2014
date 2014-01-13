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
public class MouseTester {
  @Test
  public void canBeAlive() {
    Mouse mouse = new Mouse();

    assertFalse(mouse.isDead());
  }

  @Test
  public void canBeDead() {
    Mouse mouse = new Mouse(0);

    assertTrue(mouse.isDead());
  }
}
