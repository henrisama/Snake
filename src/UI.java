import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.Random;
import java.awt.Toolkit;

public class UI extends JPanel implements ActionListener{
  Controller controller = new Controller();

  public UI(){
    super();
    controller.random = new Random();
    this.setPreferredSize(new Dimension(Controller.WIDTH, Controller.HEIGHT));
    this.setBackground(Color.GRAY);
    this.setFocusable(true);
    this.addKeyListener(new MykeyAdapter());
    controller.appleIcon = Toolkit.getDefaultToolkit().getImage("src/img/apple.png");
    System.out.println(controller.appleIcon);
    controller.start(this);
  }

  public void paintComponent(Graphics g){
    super.paintComponent(g);
    draw(g);
  }

  public void draw(Graphics g){
    if(controller.running){
      /* for(int i = 0; i < Controller.HEIGHT/Controller.UNIT; i++){
        g.drawLine(i*Controller.UNIT, 0, i*Controller.UNIT, Controller.HEIGHT);
        g.drawLine(0, i*Controller.UNIT, Controller.WIDTH, i*Controller.UNIT);
      } */
  
      // apple
      g.drawImage(controller.appleIcon, controller.apple[0], controller.apple[1], Controller.UNIT, Controller.UNIT, null);
  
  
      // snake
      for(int i = 0; i < controller.body; i++){
        if(i == 0){
          g.setColor(Color.GREEN);
        }else{
          g.setColor(new Color(controller.random.nextInt(255),controller.random.nextInt(255),controller.random.nextInt(255)));
        }
        g.fillRect(controller.x[i], controller.y[i], Controller.UNIT, Controller.UNIT);
      }

      g.setColor(Color.decode("#F2F2F2"));
      g.setFont(new Font("Monospaced", Font.BOLD, 40));
      FontMetrics metrics = getFontMetrics(g.getFont());
      g.drawString(
        "Score: "+controller.eaten, 
        (Controller.WIDTH - metrics.stringWidth("Score: "+controller.eaten))/2,
        g.getFont().getSize()
      );
    }else{
      gameOver(g);
    }
  }

  public void gameOver(Graphics g){
    //score
    g.setColor(Color.decode("#F2F2F2"));
    g.setFont(new Font("Monospaced", Font.BOLD, 40));
    FontMetrics metricsScore = getFontMetrics(g.getFont());
    g.drawString(
      "Score: "+controller.eaten, 
      (Controller.WIDTH - metricsScore.stringWidth("Score: "+controller.eaten))/2,
      g.getFont().getSize()
    );

    //game over
    g.setColor(Color.decode("#48CCCE"));
    g.setFont(new Font("Monospaced", Font.BOLD, 75));
    FontMetrics metrics = getFontMetrics(g.getFont());
    g.drawString(
      "Game Over", 
      (Controller.WIDTH - metrics.stringWidth("Game Over"))/2,
      Controller.HEIGHT/2
    );
  }
        
  @Override
  public void actionPerformed(ActionEvent e){
    if(controller.running){
      controller.move();
      controller.eat();
      controller.collision();
    }
    repaint();
  }

  public class MykeyAdapter extends KeyAdapter{
    @Override
    public void keyPressed(KeyEvent e){
      switch (e.getKeyCode()) {
        case KeyEvent.VK_LEFT:
          if(controller.direction != 'R'){
            controller.direction = 'L';
          }
          break;
        case KeyEvent.VK_RIGHT:
          if(controller.direction != 'L'){
            controller.direction = 'R';
          }
          break;
        case KeyEvent.VK_UP:
          if(controller.direction != 'D'){
            controller.direction = 'U';
          }
          break;
        case KeyEvent.VK_DOWN:
          if(controller.direction != 'U'){
            controller.direction = 'D';
          }
          break;
        default:
          break;
      }
    }
  }

}
