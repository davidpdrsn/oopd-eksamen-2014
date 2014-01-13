package app.views;

/**
 * Interface describing a view that renders to the terminal.
 */
public interface TerminalView {
  public String toString();
  public void render();
}
