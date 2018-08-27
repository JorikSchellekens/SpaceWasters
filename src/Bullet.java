import processing.core.PImage;
import static game.CONSTANTS.*;
import ddf.minim.*;

/**
 * Created by Jorik on 09/02/20 17.
 */
public class Bullet extends PhysicsObject {
  InvadersOfBoredom parent;
  PImage image;
  public boolean removable = false;
  SinusoidalMotion motion = new SinusoidalMotion();
  boolean oscilating = false;

  Bullet() {

  }

  Bullet(InvadersOfBoredom parent, float x, float y, int speed) {
    this.parent = parent;
    this.setPosition(x, y);
    this.setVelocity(0, speed);
    image = parent.loadImage(BULLET_IMAGE);
  }

  public void draw() {
    parent.image(image, getX(), getY());
  }

  public boolean isOffScreen() {
    return (getX() - image.width / 2 > parent.width
        || getX() + image.width / 2 < 0
        || getY() - image.width / 2 > parent.width
        || getY() + image.width / 2 < 0);
  }

  public void checkCollide(Invader[][] fleet) {
    for (int row = 0; row < fleet.length; row++) {
      for (int alien = 0; alien < fleet[row].length; alien++) {
        Invader theAlien = fleet[row][alien];
        if (theAlien != null
            && (getX() + image.width / 2) > theAlien.getX() - theAlien.invaderImage.width/2
            && (getX() - image.width / 2) < theAlien.getX() + theAlien.invaderImage.width/2
            && (getY() - image.width / 2) < theAlien.getY() + theAlien.invaderImage.width/2
            && (getY() + image.width / 2) > theAlien.getY() - theAlien.invaderImage.width/2) {
          theAlien.explode();
          parent.playExplosion();
          removable = true;
        }
      }
    }
  }

  public void checkCollide(Defender player) {

  }

  public void checkCollide(BarricadeCollection barricades) {
    for (int barricade = 0; barricade < barricades.barricades.length; barricade++) {
      Barricade[] bs = barricades.barricades;
      for (int part = 0; part < bs[barricade].parts.length; part++) {
        BarricadePart baricadepart = bs[barricade].parts[part];
        if (baricadepart != null
            && (getX() + image.width / 2) > baricadepart.getX() - baricadepart.image.width/2
            && (getX() - image.width / 2) < baricadepart.getX() + baricadepart.image.width/2
            && (getY() - image.width / 2) < baricadepart.getY() + baricadepart.image.width/2
            && (getY() + image.width / 2) > baricadepart.getY() - baricadepart.image.width/2) {
            bs[barricade].parts[part] = null;
            parent.playBreak();
            removable = true;
        }
      }
    }
  }

  public void applyOsciation() {
    oscilating = true;
  }

  @Override
  public void move() {
    super.move();
    if (oscilating) {
      motion.tick();
      motion.applyToX(this, 5);
    }
  }
}
