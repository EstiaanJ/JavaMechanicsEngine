package ejvr.physics;

import ejvr.maths.Point;

public class MechanicsCore {
    protected Point position;

    public Point getPos(){
        return this.position;
    }

    public void setPos(Point inPos) {
        position = inPos;
    }
}