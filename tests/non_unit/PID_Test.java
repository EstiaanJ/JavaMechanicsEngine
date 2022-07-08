package non_unit;

import ejvr.math.real.VectorDouble;
import ejvr.physics.WorldState;
import ejvr.physics.collision.CircularCollider;
import ejvr.physics.kinematics.KinematicBody;
import processing.core.PApplet;

public class PID_Test extends PApplet {
    WorldState worldState = new WorldState(new CircularCollider[0]);
    CircularCollider cc = new CircularCollider(0,10, new KinematicBody(new VectorDouble(100, 100), new VectorDouble(0, 0), new VectorDouble(0, 0), 10));
    public static void main(String[] args){
        PApplet.main(new String[]{"non_unit.PID_Test"});
    }

    public void settings(){
        size(800,800);
    }

    public void setup(){
        worldState.addCircularCollider(cc);
    }

    public void draw() {
        background(0);
        worldState.stepFrame(this);
    }
}
