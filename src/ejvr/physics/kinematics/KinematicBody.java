package ejvr.physics.kinematics;

import ejvr.maths.Point;
import ejvr.maths.Vector;
import ejvr.physics.WorldState;

public class KinematicBody {

    public final Point position;
    public final Vector velocity;
    public final Vector netForce;
    public final double mass;

    public KinematicBody(Point point, Vector velocity, Vector netForce, double mass) {
        this.position = point;
        this.velocity = velocity;
        this.netForce = netForce;
        this.mass = mass;
    }

    public KinematicBody(KinematicBody body) {
        this.mass = body.mass;
        this.position = body.position;
        this.velocity = body.velocity;
        this.netForce = body.netForce;
    }

    public KinematicBody stepPhysics(double dt){
        Vector newVelocity = stepVelocity(dt, stepAcceleration(netForce));
        Vector newPos = stepPosition(dt, this.position, newVelocity);
        KinematicBody newBody = new KinematicBody(newPos, newVelocity, netForce, this.mass);
        if(position.x > WorldState.CLAMP_X_MAX || position.x < WorldState.CLAMP_X_MIN) {
            newVelocity = stepVelocity(dt, stepAcceleration(netForce));
            newVelocity = new Vector(-newVelocity.x,newVelocity.y);
            newPos = stepPosition(dt, this.position, newVelocity);
            newBody = new KinematicBody(newPos, newVelocity, netForce, this.mass);
        }
        if(position.y > WorldState.CLAMP_Y_MAX || position.y < WorldState.CLAMP_Y_MIN) {
            newVelocity = stepVelocity(dt, stepAcceleration(netForce));
            newVelocity = new Vector(newVelocity.x, -newVelocity.y);
            newPos = stepPosition(dt, this.position, newVelocity);
            newBody = new KinematicBody(newPos, newVelocity, netForce, this.mass);
        }
        return newBody;
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

    public KinematicBody byVelocity(Vector velocity){
        return new KinematicBody(this.position,velocity, netForce, this.mass);
    }

    public KinematicBody byPosition(Point position){
        return new KinematicBody(position,this.velocity, netForce, this.mass);
    }

    public KinematicBody byNetForce(Vector newNetForce) {
        return new KinematicBody(this.position,this.velocity,newNetForce,this.mass);
    }

    public KinematicBody addForce(Vector additionalForce){
        return new KinematicBody(this.position,this.velocity,this.netForce.add(additionalForce),this.mass);
    }


    public float getX(){
        return this.position.xFloat();
    }

    public float getY(){
        return this.position.yFloat();
    }
}
