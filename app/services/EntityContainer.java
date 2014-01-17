package app.services;

import java.util.*;
import app.models.*;

public interface EntityContainer {
  public void add(Entity entity);
  public int numberOfEntities();
  public boolean containsMouse();
  public boolean containsStone();
  public boolean containsOwl();
  public int numberOfMice();
  public Iterator<Entity> iterator();
}
