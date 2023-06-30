package ir.ac.kntu.gamelogic.services;

import java.util.Timer;
import java.util.TimerTask;

public class TimerWrapper {
    private final static TimerWrapper INSTANCE = new TimerWrapper();

    private final Timer timer;

    private TimerWrapper() {
        timer = new Timer();
    }

    public static TimerWrapper getInstance() {
        return INSTANCE;
    }

    public void schedule(TimerTask task, long delay, long period) {
        timer.scheduleAtFixedRate(task, delay, period);
    }
}
