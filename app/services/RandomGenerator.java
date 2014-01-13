package app.services;

import java.util.Random;

public class RandomGenerator {
  public static int intBetween(int a, int b) {
    Random g = new Random();

    return g.nextInt(b - a) + a;
  }

  public static boolean bool() {
    return new Random().nextBoolean();
  }
}
