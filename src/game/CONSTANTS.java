package game;

import java.awt.Color;

/**
 * Created by Jorik on 31/01/2017.
 */
public final class CONSTANTS {
  public static final boolean FULLSCREEN = true;
  public static final int WIDTH = 1000;
  public static final int HEIGHT = 700;
  public static final String INVADER_IMAGE_LOCATION = "spacer.gif";
  public static final String EXPLODED_INVADER_IMAGE_LOCATION = "exploding.gif";
  public static final String CANNON_IMAGE = "cannon.png";
  public static final String BULLET_IMAGE = "bullet.png";
  public static final String BOMB_IMAGE = "bomb.png";
  public static final String POWERUP_IMAGE = "powerup.png";
  public static final String BULLET_TYPE = "b";
  public static final String POWER_TYPE = "p";
  public static final String BOMB_TYPE = "bomb";
  public static final String WIN_MESSAGE = "YOU WIN";
  public static final String LOSS_MESSAGE = "'BOMBER' BRO";
  public static final String BULLET_SOUND = "pew.wav";
  public static final String BOMB_SOUND = "kaboom.wav";
  public static final String BREAK_SOUND = "break.wav";
  public static final String WOBBLE = "wobble.wav";
  public static final int CANNON_MARGIN = 50;
  public static final int LIVES = 1;
  public static final float SPEED = (float) 1;
  public static final int MARGIN = 40;
  public static final int SPACING = 20;
  public static final int FRAME_RATE = 60;
  public static final int RADIUS = 10;
  public static final int EXPLOSION_RATE = FRAME_RATE * 3;
  public static final double BLOWUP_TIME = FRAME_RATE * 0.1;
  public static final int BULLET_SPEED = -3;
  public static final int POWERUP_SPEED = 2;
  public static final int BOMB_SPEED = 2;
  public static final Color POWERED_UP_COLOR = new Color(0, 255, 0);
  public static final Color BLACK = new Color(0, 0, 0);

  public static final int DOWN = 0;
  public static final int HORIZONTAL = 1;


  public static final int PADDLE_WIDTH = 20;
}

