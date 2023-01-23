package dev.luucx7.seitabot.core.background;

import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Worker extends TimerTask {
    private final List<DoWorkListener> listeners = new LinkedList<>();
    private final Timer timer = new Timer(true);

    public Worker(int delay, int startTime) {
        timer.schedule(this, delay, startTime);
    }

    public void addWorkerListener(DoWorkListener listener) {
        this.listeners.add(listener);
    }

    @Override
    public void run() {
        listeners.forEach((DoWorkListener::doWork));
    }
}
