package app.models;

/**
 * A stone.
 */
public class Stone extends Entity {
  /**
   * Check if the entity is a stone or not. This is always true.
   * @return whether its a stone or not.
   */
  public boolean isStone() {
    return true;
  }
}
