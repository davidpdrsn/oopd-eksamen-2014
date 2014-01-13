package app.models;

public class Point {
  private int x, y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public boolean equals(Object obj) {
    if (this == obj) return true;

    if ((obj == null) || (obj.getClass() != this.getClass())) return false;

    Point point = (Point) obj;
    return this.x == point.getX() && this.y == point.getY();
  }

  public int hashCode() {
    int hash = 5;
    hash += this.x;
    hash += this.y;
    return hash;
  }
}
