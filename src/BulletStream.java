/**
 * Created by Jorik on 09/02/2017.
 */
import static game.CONSTANTS.*;
public class BulletStream {
  InvadersOfBoredom parent;
  String type;
  Bullet[] bullets;

  BulletStream(InvadersOfBoredom parent, String STREAM_TYPE) {
    this.parent = parent;
    type = STREAM_TYPE;
    bullets = new Bullet[1];
  }

  public void newBullet(float x, float y) {
    Bullet newBullet;
    switch (type) {
      case BULLET_TYPE:
        newBullet = new Bullet(parent, x, y, BULLET_SPEED);
        if (parent.player.doubleShoot) {
          newBullet.setPositionY(y - 20);
          newBullet.applyOsciation();
        }
        break;
      case POWER_TYPE:
        newBullet = new PowerUp(parent, x, y, POWERUP_SPEED);
        break;
      default:
        newBullet = new Bullet(parent, x, y, BULLET_SPEED);
    }
    place(newBullet);
  }

  public void place(Bullet bullet) {
    boolean placed = false;
    for (int space = 0; space < bullets.length && !placed; space++) {
      if (bullets[space] == null) {
        bullets[space] = bullet;
        placed = true;
      }
    }
    if (!placed) {
      Bullet[] temp = new Bullet[bullets.length + 1];
      System.arraycopy(bullets, 0, temp, 0, bullets.length);
      temp[temp.length - 1] = bullet;
      bullets = temp;
    }
  }

  public void newBomb(float x, float y, Invader alien) {
    Bullet newBomb = new Bomb(parent, x, y, POWERUP_SPEED, alien);
    place(newBomb);
  }

  public void move() {
    for (int bullet = 0; bullet < bullets.length; bullet++) {
      if (bullets[bullet] != null) {
        bullets[bullet].move();
        if (bullets[bullet].isOffScreen()) {
          bullets[bullet] = null;
        }
        else if(bullets[bullet].removable) {
          bullets[bullet] = null;
        }
      }
    }
  }

  public void checkCollisions(Invader[][] fleet) {
    for (int bullet = 0; bullet < bullets.length; bullet++) {
      Bullet theBullet = bullets[bullet];
      if (theBullet != null) {
        theBullet.checkCollide(fleet);
      }
    }
  }

  public void checkCollisions(Defender player) {
    for (int bullet = 0; bullet < bullets.length; bullet++) {
      Bullet theBullet = bullets[bullet];
      if (theBullet != null) {
        theBullet.checkCollide(player);
      }
    }
  }

  public void checkCollisions(BarricadeCollection barricades) {
    for (int bullet = 0; bullet < bullets.length; bullet++) {
      Bullet theBullet = bullets[bullet];
      if (theBullet != null) {
        theBullet.checkCollide(barricades);
      }
    }
  }

    public void draw() {
    for (int bullet = 0; bullet < bullets.length; bullet++) {
      if (bullets[bullet] != null) {
        bullets[bullet].draw();
      }
    }
  }
}
