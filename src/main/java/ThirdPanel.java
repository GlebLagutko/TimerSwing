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
import java.io.FileReader;
import java.io.IOException;

public class ThirdPanel extends JPanel {

    JFreeChart chart;

    public ThirdPanel() {

        try (FileReader reader = new FileReader("src\\main\\java\\input.json")) {
            //Считывание файла
            Gson gson = new Gson();
            Country[] countries = gson.fromJson(reader, Country[].class);
            //cоздание диаграммы
            PieDataset pieDataset = createDataset(countries);
            chart = createChart(pieDataset);
            PiePlot plot = (PiePlot) chart.getPlot();
            PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
                    "{0}: {1} людей, {2}");
            plot.setLabelGenerator(gen);
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize((new Dimension(550, 550)));
            add(chartPanel);

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
}
