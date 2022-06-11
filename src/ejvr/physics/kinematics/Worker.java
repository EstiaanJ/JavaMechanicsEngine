package ejvr.physics.kinematics;

import ejvr.maths.Vector;

public class Worker extends Thread{
    private KinematicBody entityF;
    private boolean done = false;

    public void run(KinematicBody inEntity, Double dt) {
        done = false;
        entityF = inEntity.stepPhysics(dt, new Vector(0,100));
        done = true;
    }

    public KinematicBody getEntity(){
        return entityF;
    }


}
