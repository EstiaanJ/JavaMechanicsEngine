package ejvr.physics.kinematics;

import ejvr.maths.Point;
import ejvr.maths.Vector;

import java.util.ArrayList;

public class World {
    private ArrayList<KinematicEntityImmutable> entities = new ArrayList<>();

    public void stepPhysics(double dt){
        ArrayList<KinematicEntityImmutable> nextList = new ArrayList<>();
        for (KinematicEntityImmutable kf: entities) {
            KinematicEntityImmutable f = kf.stepPhysics(1.0/60.0,new Vector(0,100.0));
            nextList.add(f);
        }

        entities = nextList;
    }

    public void add(KinematicEntityImmutable entityF){
        entities.add(entityF);
    }

    public ArrayList<Point> getPositions(){
        ArrayList<Point> ret = new ArrayList<>();
        for (KinematicEntityImmutable kf: entities
             ) {
            ret.add(kf.position);
        }
        return ret;
    }
}
