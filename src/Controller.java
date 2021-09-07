import java.util.Random;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.Image;

public class Controller {
  static final int WIDTH = 800;
  static final int HEIGHT = 800;
  static final int UNIT = 20;
  static final int GAME_UNITS = (WIDTH*HEIGHT)/UNIT;
  static final int DELAY = 75;

  final int x[] = new int[GAME_UNITS];
  final int y[] = new int[GAME_UNITS];

  int body = 6; // size of body
  int apple[] = new int[2]; // location of apple [x, y]
  Image appleIcon;
  int eaten = 0; // number of times that the snake eat a apple
  char direction = 'R'; // direction of snake
  boolean running = false;
  Timer timer;
  Random random;
  
  public void start(ActionListener al){
    newApple();
    running = true;
    timer = new Timer(DELAY, al);
    timer.start();
  }

  public void newApple() {
    apple[0] = random.nextInt((int)(WIDTH/UNIT))*UNIT;
    apple[1] = random.nextInt((int)(HEIGHT/UNIT))*UNIT;
  }

  public void move(){
    for(int i = body; i > 0; i--){
      x[i] = x[i-1];
      y[i] = y[i-1];
    }
    
    switch(direction){
      case 'U':
        y[0] = y[0] - UNIT;
        break;
      case 'D':
        y[0] = y[0] + UNIT;
        break;
      case 'L':
        x[0] = x[0] - UNIT;
        break;
      case 'R':
        x[0] = x[0] + UNIT;
        break;
    }   
  }
  
  public void eat(){
    if((x[0] == apple[0]) && (y[0] == apple[1])){
      body++;
      eaten++;
      newApple();
    }
  }
  
  public void collision(){
    //check if head collides with body
    for(int i = body; i > 0; i--){
      if((x[0] == x[i]) && (y[0] == y[i])){
        running = false;
      }
    }

    //checks if head touches border
    /* if((x[0] < 0) || (x[0] > WIDTH) || (y[0] < 0) || (y[0] > HEIGHT)){
      running = false;
    } */

    if(x[0] < 0){
      x[0] = ((int) (WIDTH/UNIT))*UNIT;
    }

    if(x[0] > WIDTH){
      x[0] = 0;
    }

    if(y[0] < 0){
      y[0] = ((int) (HEIGHT/UNIT))*UNIT;
    }

    if(y[0] > HEIGHT){
      y[0] = 0;
    }

    if(!running){
      timer.stop();
    }
  }

}
