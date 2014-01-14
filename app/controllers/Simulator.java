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
    View view = new EnvironmentView(env);

    sim.render(view);
  }

  /**
   * Tell the given view to render itself.
   * @params view the view to render.
   */
  public void render(View view) {
    view.render();
  }
}
