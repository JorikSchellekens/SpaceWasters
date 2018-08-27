import static game.BarricadeConstants.*;

public class BarricadeCollection {
  Barricade[] barricades = new Barricade[NUMBER_OF_BARRICADES];
  InvadersOfBoredom parent;

  BarricadeCollection(InvadersOfBoredom parent, float y) {
    this.parent = parent;
    for (int barricade = 0; barricade < barricades.length; barricade++) {
      barricades[barricade] = new Barricade(parent, (parent.width / (NUMBER_OF_BARRICADES + 1)) * (barricade + 1), y);
    }
  }

  public void draw() {
    for (int barricade = 0; barricade < barricades.length; barricade++) {
      if (barricades[barricade] != null) {
        barricades[barricade].draw();
      }
    }
  }
}
