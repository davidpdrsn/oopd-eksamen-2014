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
    Miljo env = new Miljo();
    View view = new MiljoView(env, SquareEmojiView.class);
    View stats = new MiljoStatsView(env);

    while (true) {
      view.render();
      stats.render();
      env.update();
      Thread.sleep(250);
    }
  }
}
