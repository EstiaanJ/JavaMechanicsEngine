package physics.kinematics;

import maths.Point;
import maths.Vector;

import java.util.ArrayList;

public class World {
    private ArrayList<KinematicEntityF> entities = new ArrayList<>();

    public void stepPhysics(double dt){
        ArrayList<KinematicEntityF> nextList = new ArrayList<>();
        for (KinematicEntityF kf: entities) {
            KinematicEntityF f = kf.stepPhysics(1.0/60.0,new Vector(0,100.0));
            nextList.add(f);
        }

        entities = nextList;
    }

    public void add(KinematicEntityF entityF){
        entities.add(entityF);
    }

    public ArrayList<Point> getPositions(){
        ArrayList<Point> ret = new ArrayList<>();
        for (KinematicEntityF kf: entities
             ) {
            ret.add(kf.position);
        }
        return ret;
    }
}
