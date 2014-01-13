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

  public boolean equals(Object obj) {
    if (obj == null) return false;
    if (obj == this) return true;
    if (!(obj instanceof Stone)) return false;

    return true;
  }
}
