package app.controllers;

import app.models.*;
import app.views.*;

/**
 * The simulator controller.
 */
public class Simulator extends Controller {
  /**
   * Kick off the simulation.
   * @params args command line arguments. Are ignored.
   */
  public static void main(String[] args) throws InterruptedException {
    Simulator sim = new Simulator();
    Environment env = new Environment();
    View view = new EnvironmentView(env);

    while (true) {
      sim.renderView(view);
      System.out.println(env.numberOfMice());
      env.update();
      Thread.sleep(250);
    }
  }

  /**
   * Tell the given view to render itself.
   * @params view the view to render.
   */
  public void renderView(View view) {
    view.render();
  }
}
