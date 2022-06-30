package ejvr.physics.Threads;

import ejvr.physics.WorldState;
import ejvr.stopwatch.Stopwatch;

public class MotionThread implements Runnable{
    private WorldState worldState;
    private long lasTime;
    private double dt;

    public MotionThread( WorldState worldState) {
        this.worldState = worldState;
        lasTime = System.nanoTime();
    }

    synchronized public WorldState getWorldState() {
        return new WorldState(this.worldState.colliderList());
    }

    synchronized public void setWorldState(WorldState worldState) {
        this.worldState = worldState;
    }

    @Override
    public void run() {
        while(true) {
            long frameTimeExpected = (long)((1.0/60) * 1000);
            long frameStart = System.currentTimeMillis();
            dt = ((double)frameTimeExpected)/1000.0; //((double) (System.nanoTime() - lasTime)) / 1000000000.0;
            Stopwatch stopwatch = new Stopwatch(true);
            worldState = worldState.stepCollisions(dt);
            System.out.println(stopwatch.elapsedSeconds());
            worldState = worldState.stepMotion(dt);
            long frameEnd = System.currentTimeMillis();
            long actualDelta = frameEnd - frameStart;
            long sleepTime = (frameTimeExpected - actualDelta);
            if(sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("MotionThread: TOO SLOW!");
            }
        }
    }
}
