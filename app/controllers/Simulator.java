package app.controllers;

import app.models.*;
import app.views.*;

/**
 * The simulator controller.
 */
public class Simulator {
  /**
   * Kick off the simulation.
   * @params args command line arguments. Are ignored.
   */
  public static void main(String[] args) throws InterruptedException {
    Simulator sim = new Simulator();
    Environment env = new Environment();
    View view = new EnvironmentView(env, SquareEmojiView.class);

    int round = 0;
    while (true) {
      round++;
      view.render();
      System.out.printf("Current number of mice: %d\n", env.numberOfMice());
      System.out.printf("Round number: %d\n", round);
      env.update();
      Thread.sleep(250);
    }
  }
}
