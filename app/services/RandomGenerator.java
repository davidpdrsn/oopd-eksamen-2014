package app.services;

import java.util.Random;

/**
 * A class for generating random values.
 */
public class RandomGenerator {
  /**
   * Generate a random int between two numbers. Both inclusive.
   * @param a the lower bound of the range (inclusive).
   * @param b the upper bound of the range (inclusive).
   * @return the randomly generated number.
   */
  public static int intBetween(int a, int b) {
    Random g = new Random();

    return g.nextInt(b - a) + a;
  }

  /**
   * Generate a random boolean value.
   * @return the randomly generated boolean.
   */
  public static boolean bool() {
    return new Random().nextBoolean();
  }
}
