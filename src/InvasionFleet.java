import java.util.Random;

import static game.CONSTANTS.*;

/**
 * Created by Jorik on 01/02/2017.
 */

public class InvasionFleet {
  public Invader[][] fleet;
  InvadersOfBoredom parent;

  public int moveType = HORIZONTAL;
  private float fleetSpeed = SPEED;
  private int downCount = 0;
  private int alienHeight;
  private int alienWidth;
  private Invader[] sinusoidRow;
  private Random rand = new Random();
  private SinusoidalMotion motion = new SinusoidalMotion();

  public InvasionFleet(InvadersOfBoredom p, int numberOfRows, int numberOfColumns) {
    parent = p;
    fleet = new Invader[numberOfRows][numberOfColumns];

    alienHeight = (new Invader(parent, -1, -1)).invaderImage.height;
    alienWidth = (new Invader(parent, -1, -1)).invaderImage.width;

    for (int row = 0; row < fleet.length; row++) {
      for (int alien = 0; alien < fleet[row].length; alien++) {
        fleet[row][alien] =
            new Invader(parent, MARGIN + alienWidth / 2 + (alienWidth + SPACING) * alien,
                MARGIN + alienHeight / 2 + (alienHeight + SPACING) * row);
        fleet[row][alien].setVelocity(fleetSpeed, 0);
      }
      powerUp(fleet[row]);
    }
  }

  public void move() {
    switch (moveType) {
      case DOWN:
        downCount++;
        setFleetVelocity(0, Math.abs(fleetSpeed));
        if ((downCount * Math.abs(fleetSpeed)) / alienHeight >= 1) {
          moveType = HORIZONTAL;
          fleetSpeed = (float)(- Math.signum(fleetSpeed)*(Math.abs(fleetSpeed) + 0.25));
          setFleetVelocity(fleetSpeed, 0);
          downCount = 0;
        }
        break;
      case HORIZONTAL:
        break;
      default:
        moveType = HORIZONTAL;
    }

    for (int row = 0; row < fleet.length; row++) {
      for (int alien = 0; alien < fleet[row].length; alien++) {
        if (fleet[row][alien] != null) {
          if (fleet[row][alien].removable) {
            fleet[row][alien] = null;
          } else {
            fleet[row][alien].move(this);
            if (!fleet[row][alien].hasBomb && rand.nextInt(5000) == 0) {
              parent.bombStream.newBomb(fleet[row][alien].getX(), fleet[row][alien].getY(), fleet[row][alien]);
            }
          }
        }
      }
    }
  }

  public void draw() {
    boolean aliensExist = false;
    if (fleet != null) {
      for (int row = 0; row < fleet.length; row++) {
        for (int alien = 0; alien < fleet[row].length; alien++) {
          if (fleet[row][alien] != null) {
            if (!fleet[row][alien].blownUp) aliensExist = true;
            fleet[row][alien].draw();
          }
        }
      }
    } else {
      System.err.println("Fleet not instantiated.");
    }
    if (!aliensExist) {
      parent.gameOver(true);
    }
  }

  private void setFleetVelocity(float x, float y) {
    for (int row = 0; row < fleet.length; row++) {
      for (int alien = 0; alien < fleet[row].length; alien++) {
        if (fleet[row][alien] != null) {
          fleet[row][alien].setVelocity(x, y);
        }
      }
    }
  }

  public void powerUp(Invader[] row) {
    sinusoidRow = row;
    for (int alien = 0; alien < row.length; alien++) {
      if (row[alien] != null) {
        Invader target = row[alien];
        target.invaderImage.loadPixels();
        for (int pixel = 0; pixel < target.invaderImage.pixels.length; pixel++) {
          if (target.invaderImage.pixels[pixel] == BLACK.getRGB()) {
            target.invaderImage.pixels[pixel] = POWERED_UP_COLOR.getRGB();
          }
        }
      }
    }
  }
}