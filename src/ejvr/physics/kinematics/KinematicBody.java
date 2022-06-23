package ejvr.physics.kinematics;

import ejvr.math.real.VectorDouble;
import ejvr.physics.WorldState;

public class KinematicBody {

    public final VectorDouble position;
    public final VectorDouble velocity;
    public final VectorDouble netForce;
    public final double mass;

    public KinematicBody(VectorDouble point, VectorDouble velocity, VectorDouble netForce, double mass) {
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
        VectorDouble newVelocity = stepVelocity(dt, stepAcceleration(netForce));
        VectorDouble newPos = stepPosition(dt, this.position, newVelocity);
        KinematicBody newBody = new KinematicBody(newPos, newVelocity, netForce, this.mass);
        if(position.x() > WorldState.CLAMP_X_MAX || position.x() < WorldState.CLAMP_X_MIN) {
            newVelocity = stepVelocity(dt, stepAcceleration(netForce));
            newVelocity = new VectorDouble(-newVelocity.x(),newVelocity.y());
            if(position.x() > WorldState.CLAMP_X_MAX) {
                newPos = stepPosition(dt,new VectorDouble(WorldState.CLAMP_X_MAX - 0.1,position.y()),newVelocity);
            } else {
                newPos = stepPosition(dt,new VectorDouble(WorldState.CLAMP_X_MIN + 0.1,position.y()),newVelocity);
            }
            newBody = new KinematicBody(newPos, newVelocity, netForce, this.mass);
        }
        if(position.y() > WorldState.CLAMP_Y_MAX || position.y() < WorldState.CLAMP_Y_MIN) {
            newVelocity = stepVelocity(dt, stepAcceleration(netForce));
            newVelocity = new VectorDouble(newVelocity.x(), -newVelocity.y());
            if(position.y() > WorldState.CLAMP_Y_MAX) {
                newPos = stepPosition(dt,new VectorDouble(position.x(),WorldState.CLAMP_Y_MAX - 1),newVelocity);
            } else {
                newPos = stepPosition(dt,new VectorDouble(position.x(),WorldState.CLAMP_Y_MIN + 0.1),newVelocity);
            }
            newBody = new KinematicBody(newPos, newVelocity, netForce, this.mass);
        }
        return newBody;
    }


    private VectorDouble stepAcceleration(VectorDouble netForce){
        //F = ma -> a = F/m
        return new VectorDouble(netForce.scale(1.0/mass));
    }

    private VectorDouble stepVelocity(double dt, VectorDouble accelIn){
        return velocity.add(accelIn.scale(dt));
    }

    private VectorDouble stepPosition(double dt, VectorDouble positionIn, VectorDouble velIn){
        return positionIn.add(velIn.scale(dt));
    }

    public KinematicBody byVelocity(VectorDouble velocity){
        return new KinematicBody(this.position,velocity, netForce, this.mass);
    }

    public KinematicBody byPosition(VectorDouble position){
        return new KinematicBody(position,this.velocity, netForce, this.mass);
    }

    public KinematicBody byNetForce(VectorDouble newNetForce) {
        return new KinematicBody(this.position,this.velocity,newNetForce,this.mass);
    }

    public KinematicBody addForce(VectorDouble additionalForce){
        return new KinematicBody(this.position,this.velocity,this.netForce.add(additionalForce),this.mass);
    }


    public float getX(){
        return this.position.xFloat();
    }

    public float getY(){
        return this.position.yFloat();
    }
}
