package ejvr.physics.kinematics;

import ejvr.math.real.VectorDouble;

public class Worker extends Thread{
    private KinematicBody entityF;
    private boolean done = false;

    public void run(KinematicBody inEntity, Double dt) {
        done = false;
        entityF = inEntity.stepPhysics(dt);
        done = true;
    }

    public KinematicBody getEntity(){
        return entityF;
    }


}
