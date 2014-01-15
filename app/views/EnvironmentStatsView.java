package app.views;

import java.util.HashMap;
import java.lang.reflect.*;

import app.models.*;

/**
 * An environment view.
 */
public class EnvironmentStatsView extends View implements TerminalView {
  private Environment env;
  private int round;

  public EnvironmentStatsView(Environment env) {
    this.env = env;
    this.round = 1;
  }

  public String toString() {
    String acc = "";

    acc += "Current number of mice: "+this.env.numberOfMice()+"\n";
    acc += "Round number: "+this.round+"\n";

    return acc;
  }

  public void render() {
    System.out.println(toString());
    this.round++;
  }
}
