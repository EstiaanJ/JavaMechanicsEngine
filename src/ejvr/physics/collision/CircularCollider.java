package ejvr.physics.collision;

import ejvr.maths.Point;

public class CircularCollider extends Collider{

    public final double radius;

    public CircularCollider(double radius, Point position){
        super(new AABB(position,radius * 2, radius * 2));
        this.radius = radius;
    }

    public boolean isInside(Point point){
        boolean returnVal = false;
        if(aabb.isInside(point)){
            if(this.aabb.getPos().distanceBetween(point) < radius) returnVal = true;
        }
        return returnVal;
    }
}
