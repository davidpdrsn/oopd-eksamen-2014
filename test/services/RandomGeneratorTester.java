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

import java.util.ArrayList;
import app.services.RandomGenerator;

@RunWith(MockitoJUnitRunner.class)
public class RandomGeneratorTester {
  @Test
  public void genearates_random_numbers_between_two_ints() {
    ArrayList<Integer> ints = new ArrayList<Integer>();

    for (int i = 0; i < 1000000; i++) {
      ints.add(RandomGenerator.intBetween(5, 10));
    }

    for (int i = 5; i <= 10; i++) {
      if (!ints.contains(i)) {
        fail("Didn't generate the number " + i);
      }
    }
  }

  @Test
  public void generates_random_bools() {
    ArrayList<Boolean> bools = new ArrayList<Boolean>();

    for (int i = 0; i < 1000; i++) {
      bools.add(RandomGenerator.bool());
    }

    if (!bools.contains(true)) {
      fail("Didn't generate true");
    }

    if (!bools.contains(false)) {
      fail("Didn't generate false");
    }
  }
}
