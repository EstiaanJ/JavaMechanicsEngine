package ejvr.physics.collision;

import ejvr.math.real.PID;
import ejvr.math.real.VectorDouble;
import ejvr.physics.kinematics.KinematicBody;

public class CircularCollider extends Collider{
    public static final double RESTITUTION = 0.99;

    public final double radius;

    public CircularCollider(int id, double radius, KinematicBody body){
        super(id, body,radius * 2, radius * 2);
        this.radius = radius;
    }

    public CircularCollider(CircularCollider cc) {
        super(cc.id, cc, cc.radius * 2, cc.radius * 2);
        this.radius = cc.radius;
    }

    public static CircularColliderPair solveCollision(CircularColliderPair pair){
        var distance = overlapDistance(pair.one(),pair.two());
        var normal = new VectorDouble((pair.two().pos().x() - pair.one().pos().x())/ distance,
                                (pair.two().pos().y() - pair.one().pos().y())/ distance);
        var tangent = normal.tangent();

        var dotProductTanOne = pair.one().velocity.dotProduct(tangent);
        var dotProductTanTwo = pair.two().velocity.dotProduct(tangent);

        var dotProductNormOne = pair.one().velocity.dotProduct(normal);
        var dotProductNormTwo = pair.two().velocity.dotProduct(normal);

        var momentumOne = (dotProductNormOne * (pair.one().mass - pair.two().mass) + 2 * pair.two().mass * dotProductNormTwo) / (pair.one().mass + pair.two().mass);
        var momentumTwo = (dotProductNormTwo * (pair.two().mass - pair.one().mass) + 2 * pair.one().mass * dotProductNormOne) / (pair.one().mass + pair.two().mass);

        return new CircularColliderPair(
                pair.one().byVelocity(new VectorDouble(tangent.x() * dotProductTanOne + normal.x() * momentumOne, tangent.y() * dotProductTanOne + normal.y() * momentumOne).scale(RESTITUTION)),
                pair.two().byVelocity(new VectorDouble(tangent.x() * dotProductTanTwo + normal.x() * momentumTwo, tangent.y() * dotProductTanTwo + normal.y() * momentumTwo).scale(RESTITUTION))
        );
    }

    private static double overlapDistanceSquared(CircularCollider one, CircularCollider two) {
        return ((one.pos().x() - two.pos().x()) * (one.pos().x() - two.pos().x())) + ((one.pos().y() - two.pos().y()) * (one.pos().y() - two.pos().y()));
    }

    public static double overlapDistance(CircularCollider one, CircularCollider two) {
        return Math.sqrt(overlapDistanceSquared(one,two));
    }

    public static boolean overlap(CircularCollider entityOne, CircularCollider entityTwo) {
        var radSqr = (entityOne.radius + entityTwo.radius) * (entityOne.radius + entityTwo.radius);
        return overlapDistanceSquared(entityOne,entityTwo) <= radSqr;
    }

    public boolean isInside(VectorDouble point){
        boolean returnVal = false;
        if(aabb.isInside(point)){
            if(this.aabb.getPos().distanceBetween(point) < radius) returnVal = true;
        }
        return returnVal;
    }

    public CircularCollider byVelocity(VectorDouble velocity){
        return new CircularCollider(this.id, this.radius,super.byVelocity(velocity));
    }

    public CircularCollider byPosition(VectorDouble position){
        return new CircularCollider(this.id, this.radius,super.byPosition(position));
    }

    @Override
    public CircularCollider addForce(VectorDouble forceToAdd){
        return new CircularCollider(this.id,this.radius,super.addForce(forceToAdd));
    }
}
