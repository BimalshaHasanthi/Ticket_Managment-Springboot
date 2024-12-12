package com.scorpion.spring_boot.sim;

public class ThreadManager {
    private volatile boolean paused = false;

    public void pause() {
        paused = true;
    }

    public void resume() {
        paused = false;
    }

    public boolean isPaused() {
        return paused;
    }
}
