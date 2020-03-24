import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

class MyFrame extends JFrame {

    public MyFrame() {
        SwingUtilities.invokeLater(() -> {
            setVisible(true);
            setSize(600, 600);
            MyFrame.this.setLocationRelativeTo(null);
           // this.setResizable(false);
            addComponentListener(new ComponentAdapter() {
                @Override
                public void componentHidden(ComponentEvent e) {
                    System.exit(0);
                }
            });
        });
    }

    private static final int MIN_SPEED = 1;
    private static final int MAX_SPEED = 20;
    private static final int INITIAL_SPEED = 2;

    private JSlider slider;
    private JComboBox<String> comboBox;
    private Boolean right = true;


    @Override
    public JRootPane createRootPane() {
        JRootPane pane = new JRootPane();
        JPanel panel = new JPanel();
        setSize(600, 600);
        // firstTask(panel);
         secondTask(panel);
       // thirdTask(panel);
        pane.setContentPane(panel);

        return pane;
    }

    private void firstTask(JPanel panel) {
        panel.add(new FirstPanel());
    }

    private void thirdTask(JPanel panel) {
        panel.add(new ThirdPanel());
        //  panel.setPreferredSize(new Dimension(700,700));
    }

    private void secondTask(JPanel panel) {
        String[] items = new String[]{"Right", "Left"};
        comboBox = new JComboBox<>(items);

        slider = new JSlider(JSlider.VERTICAL, MIN_SPEED, MAX_SPEED, INITIAL_SPEED);
        comboBox.addActionListener(e -> {
            String item = (String) comboBox.getSelectedItem();
            assert item != null;
            right = "Right".equals(item);
        });
        panel.add(slider);
        panel.add(comboBox);

        panel.add(new SecondPanel(this));
    }

    public int getSpeed() {
        return slider.getValue();
    }

    public int getDirection() {
        return right ? 1 : -1;
    }
}