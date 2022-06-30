package ejvr.physics;

import ejvr.math.real.VectorDouble;

public class MechanicsCore {
    protected VectorDouble position;

    public VectorDouble getPos(){
        return this.position;
    }

    public void setPos(VectorDouble inPos) {
        position = inPos;
    }
}
