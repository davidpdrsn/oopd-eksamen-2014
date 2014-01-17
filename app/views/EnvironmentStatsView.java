package app.views;

import java.util.HashMap;
import java.lang.reflect.*;

import app.models.*;

/**
 * An environment view that shows statistics.
 */
public class EnvironmentStatsView extends View implements TerminalView {
  /**
   * The environment this view will render.
   */
  private Environment env;

  /**
   * A counter that keeps track of the round number.
   */
  private int round;

  /**
   * Construct a new view for the given environment.
   * @param env the environment to render.
   */
  public EnvironmentStatsView(Environment env) {
    this.env = env;
    this.round = 1;
  }

  /**
   * Convert the environment to a string and increment the round counter.
   * @return the string representation of the environment.
   */
  public String toString() {
    String acc = "";

    acc += "Current number of mice: "+this.env.numberOfMice()+"\n";
    acc += "Number of mice eaten: "+this.env.getNumberOfMiceEaten()+"\n";
    acc += "Round number: "+this.round+"\n";

    this.round++;

    return acc;
  }

  /**
   * Render the view to standard out.
   */
  public void render() {
    System.out.println(toString());
  }
}
