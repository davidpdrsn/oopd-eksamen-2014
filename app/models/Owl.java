package app.models;

/**
 * An owl.
 */
public class Owl extends Entity {
  /**
   * Check if the entity is a stone or not. This is always true.
   * @return whether its a stone or not.
   */
  public boolean isOwl() {
    return true;
  }

  // TODO: why should all owls be the same?!
  public boolean equals(Object obj) {
    if (obj == null) return false;
    if (obj == this) return true;
    if (!(obj instanceof Owl)) return false;

    return true;
  }
}
