package physics.kinematics;

import maths.Vector;

public class Worker extends Thread{
    private KinematicEntityF entityF;
    private boolean done = false;

    public void run(KinematicEntityF inEntity,Double dt) {
        done = false;
        entityF = inEntity.stepPhysics(dt, new Vector(0,100));
        done = true;
    }

    public KinematicEntityF getEntity(){
        return entityF;
    }


}
