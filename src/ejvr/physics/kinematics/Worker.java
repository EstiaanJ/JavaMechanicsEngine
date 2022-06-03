package ejvr.physics.kinematics;

import ejvr.maths.Vector;

public class Worker extends Thread{
    private KinematicEntityImmutable entityF;
    private boolean done = false;

    public void run(KinematicEntityImmutable inEntity, Double dt) {
        done = false;
        entityF = inEntity.stepPhysics(dt, new Vector(0,100));
        done = true;
    }

    public KinematicEntityImmutable getEntity(){
        return entityF;
    }


}
