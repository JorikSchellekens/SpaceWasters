import java.awt.Color;
import processing.core.PImage;

import static game.CONSTANTS.*;

public class Defender extends PhysicsObject{
  InvadersOfBoredom parent;
  PImage image;
  boolean doubleShoot = false;
  int lives = LIVES;

  Defender(InvadersOfBoredom p) {
    setParent(p);
    image = parent.loadImage(CANNON_IMAGE);
    setPositionY(parent.height - CANNON_MARGIN - image.height / 2);
  }

  //setters
  void setParent(InvadersOfBoredom p){parent = p;}

  void moveToX(float	x){
    if (x < image.width / 2) {
      position.x = image.width / 2;
    } else if(x > parent.width - image.width / 2)	{
      setPositionX(parent.width - image.width / 2);
    }
    else {
      setPositionX(x);
    }
  }

  void draw()
  {
    parent.image(image, getX(), getY());
  }

  void shoot() {
    if (doubleShoot) {
      parent.bulletStream.newBullet(getX() + 5, parent.height - CANNON_MARGIN - 20);
      parent.bulletStream.newBullet(getX() - 5, parent.height - CANNON_MARGIN - 20);
    } else
    parent.bulletStream.newBullet(getX(), parent.height - CANNON_MARGIN - 20);
  }

  void powerUp() {
    doubleShoot = true;
  }

  void blowUp() {
    lives--;
    if (lives == 0) {
      parent.gameOver(false);
    }
  }
}
