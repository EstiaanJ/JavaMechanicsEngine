package ejvr.physics.Threads;

import ejvr.physics.WorldState;
import ejvr.physics.collision.CircularCollider;

public class CollisionThread implements Runnable{
    private WorldState worldState;
    private long lasTime;
    private double dt;

    public CollisionThread(WorldState worldState) {
        this.worldState = worldState;
        lasTime = System.nanoTime();
    }

    public WorldState getWorldState() {
        return new WorldState(this.worldState.colliderList());
    }

    public void setWorldState(WorldState worldState) {
        this.worldState = worldState;
    }

    @Override
    public void run() {
        while(true) {
            long frameTimeExpected = (long)((1.0/60.0) * 1000);
            long frameStart = System.currentTimeMillis();
            dt = ((double)frameTimeExpected)/1000.0; //((double) (System.nanoTime() - lasTime)) / 1000000000.0;
            worldState = worldState.stepCollisions(dt);
            long frameEnd = System.currentTimeMillis();
            long actualDelta = frameEnd - frameStart;
            long sleepTime = frameTimeExpected - actualDelta;
            if(sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("CollisionThread: TOO SLOW!");
            }
            //lasTime = System.nanoTime();
            //System.out.println(worldState.colliderList()[0].pos().xFloat());
        }
    }
}
