package physics.collision;

import maths.Point;
import physics.MechanicsCore;

public class AABB extends MechanicsCore {
    public final double width;
    public final double height;


    public AABB(Point position, double width, double height) {
        super.position = position;
        this.width = width;
        this.height = height;
    }


}
