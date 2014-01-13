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

import app.models.Stone;

@RunWith(MockitoJUnitRunner.class)
public class StoneTester {
  @Test
  public void stones_are_always_the_same() {
    Stone stone = new Stone();

    assertTrue(stone.equals(new Stone()));
  }
}
