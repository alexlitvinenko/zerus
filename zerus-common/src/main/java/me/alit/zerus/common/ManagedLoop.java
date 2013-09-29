package me.alit.zerus.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Makes wrap over loop to manage speed.
 * You need to implement {@link #onIterate()}.
 * Please use this class in threads.
 * Loop speed is measured in iteration per second.
 *
 * @author Alexander Litvinenko
 */
public abstract class ManagedLoop implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(ManagedLoop.class);

    private int ticksPerSecond = 25;
    private int skipTicks = 1000 / ticksPerSecond;
    private boolean running = true;

    /**
     * Constructs.
     */
    public ManagedLoop() {
    }

    @Override
    public void run() {
        running = true;
        LOGGER.info("Iteration started.");
        long nextGameTick = System.currentTimeMillis();
        while (running) {
            long currentTime = System.currentTimeMillis();
            if (currentTime < nextGameTick) {
                continue;
            }
            nextGameTick += skipTicks;
            onIterate();
        }
        LOGGER.info("Stopped.");
    }

    /**
     * Stops loop iteration.
     */
    public void stop() {
        if (!running) {
            LOGGER.info("Loop already stopped.");
            return;
        }
        LOGGER.info("Stopping.");
        running = false;
    }

    /**
     * Whether the loop is running.
     *
     * @return {@code true} if loop is running, {@code false} otherwise
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Increases speed by one.
     */
    public void increaseSpeed() {
        setSpeed(ticksPerSecond + 1);
    }

    /**
     * Decreases speed by one.
     */
    public void decreaseSpeed() {
        setSpeed(ticksPerSecond - 1);
    }

    /**
     * Get current loop speed. Is measured in iteration per second.
     *
     * @return speed
     */
    public int getSpeed() {
        return ticksPerSecond;
    }

    /**
     * Sets loop speed.
     *
     * @param speed speed to set. Is measured in iteration per second.
     */
    public void setSpeed(int speed) {
        ticksPerSecond = speed;
        skipTicks = 1000 / ticksPerSecond;
        LOGGER.info("Speed set: " + ticksPerSecond);
    }

    /**
     * What to do on loop iteration.
     */
    protected abstract void onIterate();
}
