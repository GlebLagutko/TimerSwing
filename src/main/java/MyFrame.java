import com.google.gson.Gson;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.FileReader;
import java.io.IOException;

class MyFrame extends JFrame {

    public MyFrame() {
        SwingUtilities.invokeLater(() -> {
            setVisible(true);
            setSize(600, 600);
            MyFrame.this.setLocationRelativeTo(null);
            this.setResizable(false);
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

    private JFreeChart chart;

    @Override
    public JRootPane createRootPane() {
        JRootPane pane = new JRootPane();
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // panel.add(new FirstTab());
        // secondTask(panel);

        thirdTask(panel);
        pane.setContentPane(panel);

        return pane;
    }

    private void thirdTask(JPanel panel) {
        try (FileReader reader = new FileReader("src\\main\\java\\input.json")) {
            Gson gson = new Gson();
            Country[] countries = gson.fromJson(reader, Country[].class);
            PieDataset pieDataset = createDataset(countries);
            chart = createChart(pieDataset);
            PiePlot plot = (PiePlot) chart.getPlot();
            PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
                    "{0}: {1} людей, {2}");
            plot.setLabelGenerator(gen);
            panel.add(new ChartPanel(chart));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JFreeChart createChart(final PieDataset dataset) {

        chart = ChartFactory.createPieChart("Больные коронавирусом",
                dataset, true, true, false);
        return chart;
    }

    private PieDataset createDataset(Country[] container) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Country elem : container) {
            dataset.setValue(elem.getName(), elem.getCount());
        }
        return dataset;
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
        panel.add(new SecondTask(this));
    }

    public int getSpeed() {
        return slider.getValue();
    }

    public int getDirection() {
        return right ? 1 : -1;
    }
}