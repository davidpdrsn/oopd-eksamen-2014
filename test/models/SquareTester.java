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

@RunWith(JUnit4.class)
public class SquareTester {
  @Test
  public void can_be_empty() {
    Square square = new Square();

    assertTrue(square.isEmpty());
  }

  @Test
  public void can_be_occupied() {
    Square square = new Square("some state goes here later");

    assertFalse(square.isEmpty());
  }
}
