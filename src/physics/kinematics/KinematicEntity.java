package physics.kinematics;

import maths.Vector;

public class KinematicEntity {
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

    public KinematicEntity(Vector velocity) {
        this.velocity = velocity;
    }

    private Vector velocity;
    private Vector acceleration;
    private Vector angularVelocity;
    private Vector angularAcceleration;


}
