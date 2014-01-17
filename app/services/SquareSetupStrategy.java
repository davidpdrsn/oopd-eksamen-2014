package app.services;

import app.models.*;
import java.util.*;

public class SquareSetupStrategy extends EntityPositioningStrategy {
  public SquareSetupStrategy(Square square) {
    super(square);
  }

  public boolean allowedCounts(int mouseCount, int stoneCount, int owlCount) {
    if (mouseCount >= 1 && owlCount == 1 ||
        stoneCount >= 2 ||
        owlCount >= 2 ||
        mouseCount > 2) {
      return false;
    } else {
      return true;
    }
  }
}
