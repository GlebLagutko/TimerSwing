import javax.swing.*;
import java.awt.*;

public class FirstPanel extends JPanel {

    private int width, height;
    private int angle;

    public FirstPanel() {
        angle = 0;
        width = 450;
        height = 450;
        setPreferredSize(new Dimension(width + 10, height + 10));
        int delay = 1000;
        repaint();
        Timer timer = new Timer(delay, e -> {
            repaint();
            angle += 6;
            if (angle >= 360) {
                angle = 0;
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.clearRect(0, 0, width + 10, height + 10);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4));

        double radAngle = Math.toRadians(angle);
        int x2 = 5 + width / 2 - (int) ((width / 2) * Math.sin(-radAngle));
        int y2 = 5 + width / 2 - (int) ((width / 2) * Math.cos(-radAngle));
        g2.setColor(Color.RED);
        g2.drawLine(5 + width / 2, 5 + height / 2, x2, y2);
        g2.setColor(Color.GREEN);
        g2.drawOval(5, 5, width, height);
    }

}
