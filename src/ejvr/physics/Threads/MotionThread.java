package ejvr.physics.Threads;

import ejvr.physics.WorldState;

public class MotionThread implements Runnable{
    private WorldState worldState;
    private long lasTime;
    private double dt;

    public MotionThread( WorldState worldState) {
        this.worldState = worldState;
        lasTime = System.nanoTime();
    }

    public WorldState getWorldState() {
        return new WorldState(this.worldState);
    }

    public void setWorldState(WorldState worldState) {
        this.worldState = worldState;
    }

    public void step(double dt){
        worldState = worldState.stepCollisions(dt);
        worldState = worldState.stepMotion(dt);
    }

    @Override
    public void run() {
        while(true) {
            long frameTimeExpected = (long)((1.0/60) * 1000);
            long frameStart = System.currentTimeMillis();
            dt = ((double)frameTimeExpected)/1000.0; //((double) (System.nanoTime() - lasTime)) / 1000000000.0;
            worldState = worldState.stepCollisions(dt);
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
