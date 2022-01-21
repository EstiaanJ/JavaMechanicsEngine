package non_unit;

import maths.Point;
import maths.Vector;
import physics.kinematics.KinematicEntity;
import processing.core.PApplet;


public class GraphicsTest extends PApplet {
    private AABB_Debug debug = new AABB_Debug(new Point(400,400),100,60);
    private KinematicEntity ke = new KinematicEntity(new Point(200,200),10);

    public static void main(String[] args) {
        PApplet.main(new String[]{"non_unit.GraphicsTest"});
    }

    public void settings(){
        size(800,800);
    }

    public void setup() {
        debug.setAngle(Math.PI/3);
        println("X-Max: " + debug.getXMax());
        println("X-Min: " + debug.getXMin());
        println("Y-Max: " + debug.getYMax());
        println("X-Min: " + debug.getYMin());
        ke.setNetForce(new Vector(100,0));

    }

    public void draw() {
        background(0);
        //debug.setPos(new Point(mouseX,mouseY));
        debug.setAngle((mouseX - 100)/100.0);
        debug.draw(this);
        drawCursor();
        ke.stepPhysics(1.0/60.0);
        circle(ke.getX(),ke.getY(),10);

        text("SPD: " + ke.getVelocity().magnitude(),20,20);
        text("ACC: " + ke.getAcceleration().magnitude(),20,50);

    }

    public void drawCursor(){
        point(mouseX,mouseY);
        fill(255);
        text("MX: " + mouseX,mouseX,mouseY - 22);
        text("MY: " + mouseY,mouseX,mouseY - 10);
    }

    public void mousePressed(){
        if(debug.isInside(new Point(mouseX,mouseY))){
            println("INSIDE| mx: " + mouseX + " || my: " + mouseY);
        }
    }
}
