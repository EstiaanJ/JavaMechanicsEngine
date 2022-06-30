package non_unit;

import ejvr.maths.Point;
import ejvr.maths.Vector;
import ejvr.physics.WorldState;
import ejvr.physics.collision.CircularCollider;
import ejvr.physics.kinematics.KinematicBody;
import processing.core.PApplet;

public class PID_Test extends PApplet {
    WorldState worldState = new WorldState(new CircularCollider[0]);
    CircularCollider cc = new CircularCollider(0,10, new KinematicBody(new Point(100, 100), new Vector(0, 0), new Vector(0, 0), 10));
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
