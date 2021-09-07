import javax.swing.JFrame;
import java.awt.Image;
import java.awt.Toolkit;

public class App extends JFrame{
  public App(){
    super("Snake");
    add(new UI());

    //set icon
    Image icon = Toolkit.getDefaultToolkit().getImage("src/img/title.png");
    setIconImage(icon);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    pack();
    setLocationRelativeTo(null);
  }
}
