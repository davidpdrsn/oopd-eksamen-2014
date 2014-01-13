package app.models;

/**
 * A null or empty entity.
 */
public class Mouse extends Entity {
  private int life;

  public Mouse() {
    this.life = 100;
  }

  public Mouse(int life) {
    this.life = life;
  }

  public boolean isMouse() {
    return true;
  }

  // TODO: are all mice really the same?!
  public boolean equals(Object obj) {
    if (obj == null) return false;
    if (obj == this) return true;
    if (!(obj instanceof Mouse)) return false;

    return true;
  }

  public boolean isDead() {
    return this.life <= 0;
  }
}
