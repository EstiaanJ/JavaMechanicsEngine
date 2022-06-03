package ejvr.physics.kinematics;

import ejvr.maths.Point;
import ejvr.maths.Vector;

public class KinematicEntityImmutable {
    public final Point position;
    public final Vector velocity;
    //public final Vector acceleration;
    //public final Vector angularVelocity;
    //public final Vector angularAcceleration;
    //public final Vector netForce;
    public final double mass;

    public KinematicEntityImmutable(Point point, Vector velocity, Vector netForce, double mass) {
        this.position = point;
        this.velocity = velocity;
        //this.acceleration = acceleration;
        //this.netForce = netForce;
        this.mass = mass;


    }

    public KinematicEntityImmutable stepPhysics(double dt, Vector netForce){
        Vector newVelocity = stepVelocity(dt,stepAcceleration(netForce));
        Vector newPos = stepPosition(dt,this.position,newVelocity);
        return new KinematicEntityImmutable(newPos,newVelocity,netForce,this.mass);
    }


    private Vector stepAcceleration(Vector netForce){
        //F = ma -> a = F/m
        return new Vector(netForce.scale(1.0/mass));
    }


    private Vector stepVelocity(double dt, Vector accelIn){
        return velocity.add(accelIn.scale(dt));
    }


    private Vector stepPosition(double dt, Point positionIn, Vector velIn){
        return positionIn.asVector().add(velIn.scale(dt));
    }






    public float getX(){
        return this.position.xFloat();
    }

    public float getY(){
        return this.position.yFloat();
    }


    public KinematicEntityImmutable byNetForce(Vector newForce) {
        //return new KinematicEntityF(this.position,this.velocity,this.acceleration,this.netForce,this.mass)
        return new KinematicEntityImmutable(this.position,this.velocity,newForce,this.mass);
    }
}
