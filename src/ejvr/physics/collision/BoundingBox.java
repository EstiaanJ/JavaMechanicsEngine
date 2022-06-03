package ejvr.physics.collision;

import ejvr.maths.Point;
import ejvr.physics.MechanicsCore;

public class BoundingBox extends MechanicsCore {
    public final double width;
    public final double height;

    private double angle;

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }




    public BoundingBox(Point position, double width, double height) {
        super.position = position;
        this.width = width;
        this.height = height;
        double angle = 0;
    }


}
