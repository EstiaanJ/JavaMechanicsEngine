package physics;

import maths.Point;
import maths.Vector;

public class MechanicsCore {
    protected Point position;

    public Point getPos(){
        return this.position;
    }

    public void setPos(Point inPos) {
        position = inPos;
    }
}
