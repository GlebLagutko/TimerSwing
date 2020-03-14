import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class SecondTask extends JPanel {
    private int width, height;
    private int angle;
    private int direction;
    private int speed;
    private MyFrame parent;
    private BufferedImage image ;

    public SecondTask(MyFrame parent) {
        super();
        angle = 0;
        this.parent = parent;
        try {
            image = ImageIO.read(new File("src\\main\\java\\cat.png"));
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        width = 450;
        height = 450;
        setPreferredSize(new Dimension(width + 10, height + 10));
        speed = 1;
        int delay = 100;
        direction = 1;
        ActionListener taskPerformer = evt -> {
            speed = this.parent.getSpeed();
            direction = this.parent.getDirection();
            angle += direction * speed ;
            repaint();
        };
        Timer timer = new Timer(delay, taskPerformer);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.clearRect(0, 0, width * 2, height * 2);

        double radAngle = Math.toRadians(angle);
        if (image != null) {
            int size = Math.min(width, height);
            int xImage = size / 2 - image.getWidth() / 2 - (int) ((size / 2 - image.getWidth() / 2) * Math.sin(-radAngle));
            int yImage = size / 2 - image.getHeight() / 2 - (int) ((size / 2 - image.getHeight() / 2) * Math.cos(-radAngle));

            g.drawImage(image, xImage, yImage, null);
        }
    }
}
