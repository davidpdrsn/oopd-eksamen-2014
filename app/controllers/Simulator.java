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
  public static void main(String[] args) {
    Simulator sim = new Simulator();
    Environment env = new Environment();
    TerminalView view = new EnvironmentView(env);

    view.render();
  }
}
