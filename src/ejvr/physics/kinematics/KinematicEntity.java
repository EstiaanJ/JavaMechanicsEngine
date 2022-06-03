package ejvr.physics.kinematics;

import ejvr.maths.Point;
import ejvr.physics.MechanicsCore;
import ejvr.maths.Vector;

public class KinematicEntity extends MechanicsCore {
    private Vector velocity;
    private Vector acceleration;
    private Vector angularVelocity;
    private Vector angularAcceleration;
    private Vector netForce;
    public final double mass;

    public KinematicEntity(Point point, double mass) {
        this.position = point;
        this.mass = mass;
        velocity = new Vector(0,0);
        acceleration = new Vector(0,0);
        netForce = new Vector(0,0);
    }

    public void stepPhysics(double dt){
        stepAcceleration();
        stepVelocity(dt);
        stepPosition(dt);
    }


    private void stepAcceleration(){
        //F = ma -> a = F/m
        acceleration = netForce.scale(1/mass);
    }


    private void stepVelocity(double dt){
        velocity = velocity.add(acceleration.scale(dt));
    }


    private void stepPosition(double dt){
        double nx = position.x + (velocity.x * dt);
        double ny = position.y + (velocity.y * dt);
        position = new Vector(nx,ny);
    }




    public Vector getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    public Vector getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Vector acceleration) {
        this.acceleration = acceleration;
    }

    public Vector getAngularVelocity() {
        return angularVelocity;
    }

    public void setAngularVelocity(Vector angularVelocity) {
        this.angularVelocity = angularVelocity;
    }

    public Vector getAngularAcceleration() {
        return angularAcceleration;
    }

    public void setAngularAcceleration(Vector angularAcceleration) {
        this.angularAcceleration = angularAcceleration;
    }

    public float getX(){
        return this.position.xFloat();
    }

    public float getY(){
        return this.position.yFloat();
    }

    public Vector getNetForce() {
        return netForce;
    }

    public void setNetForce(Vector netForce) {
        this.netForce = netForce;
    }
}
