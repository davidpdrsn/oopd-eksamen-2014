package test.services;

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

import app.services.RandomGenerator;

@RunWith(MockitoJUnitRunner.class)
public class RandomGeneratorTester {
  @Test
  public void genearates_random_numbers_between_two_ints() {
    for (int i = 0; i < 100; i++) {
      int n = RandomGenerator.intBetween(5, 10);
      assertTrue(5 <= n && n <= 10);
    }
  }

  @Test
  public void generates_random_bools() {
    boolean bool = RandomGenerator.bool();

    assertTrue(bool || !bool);
  }
}
