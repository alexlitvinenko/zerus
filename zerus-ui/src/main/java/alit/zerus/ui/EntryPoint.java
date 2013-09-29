package alit.zerus.ui;

import me.alit.zerus.bl.Zerus;
import me.alit.zerus.common.ManagedLoop;
import me.alit.zerus.domain.Beast;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Main UI entry point.
 *
 * @author Alexander Litvinenko
 */
public class EntryPoint {

    public static void main(final String[] args) {
        final Zerus zerus = new Zerus();
        JFrame frame = new JFrame("MainForm");
        final JPanel contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawZerus(g, zerus);
            }
        };

        contentPane.setPreferredSize(new Dimension(500, 500));

        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        final ManagedLoop uiLoop = new ManagedLoop() {
            @Override
            protected void onIterate() {
                contentPane.repaint();
            }
        };
        Thread uiThread = new Thread(uiLoop);
        uiThread.setName("uiThread");
        uiThread.start();
        zerus.startSimulation();
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                uiLoop.stop();
                zerus.stopSimulation();
            }
        });
    }

    private static void drawZerus(Graphics g, Zerus zerus) {
        for (Beast beast : zerus.getBeasts()) {
            g.setColor(Color.BLACK);
            g.drawOval(beast.getX(), beast.getY(), 10, 10);
            g.fillOval(beast.getX(), beast.getY(), 10, 10);
        }
    }
}
