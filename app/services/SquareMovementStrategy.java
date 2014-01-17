package app.services;

import app.models.*;
import java.util.*;

public class SquareMovementStrategy extends EntityPositioningStrategy {
  public SquareMovementStrategy(Square square) {
    super(square);
  }

  public boolean allowedCounts(int mouseCount, int stoneCount, int owlCount) {
    return mouseCount < 3 && owlCount < 2;
  }
}
