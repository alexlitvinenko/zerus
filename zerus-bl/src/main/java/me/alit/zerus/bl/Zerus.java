package me.alit.zerus.bl;

import me.alit.zerus.common.ManagedLoop;
import me.alit.zerus.domain.Beast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Main class to simulate Zerus world.
 * Use {@link #startSimulation()}  to start simulation and {@link #stopSimulation()} to stop simulation respectively.
 *
 * @author Alexander Litvinenko
 */
public class Zerus {
    private static final Logger LOGGER = LoggerFactory.getLogger(Zerus.class);

    private final ManagedLoop simulationLoop = new ManagedLoop() {
        Random random = new Random(1);

        @Override
        protected void onIterate() {
            // TODO: replace stub
            for (Beast beast : getBeasts()) {
                int way = random.nextInt(4);
                switch (way) {
                    case 0:
                        beast.setY(beast.getY() - 1);
                        break;
                    case 1:
                        beast.setY(beast.getY() + 1);
                        break;
                    case 2:
                        beast.setX(beast.getX() - 1);
                        break;
                    case 3:
                        beast.setX(beast.getX() + 1);
                        break;
                    default:
                        beast.setX(beast.getX() + 1);
                }
            }
        }
    };
    private List<Beast> beasts = new LinkedList<Beast>();

    /**
     * Starts simulation of Zerus world.
     */
    public void startSimulation() {
        // TODO: add protection from restarting or add supporting of restarting

        // TODO: replace stub
        beasts.add(new Beast(250, 250));
        beasts.add(new Beast(250, 100));
        Thread simulationThread = new Thread(simulationLoop);
        simulationThread.setName("simulationThread");
        LOGGER.info("Simulation started.");
        simulationThread.start();
    }

    /**
     * Stops simulation of Zerus world.
     */
    public void stopSimulation() {
        LOGGER.info("Stopping simulation.");
        simulationLoop.stop();
    }

    public synchronized List<Beast> getBeasts() {
        return new LinkedList<Beast>(beasts);
    }
}
