package non_unit;

import ejvr.maths.Point;
import ejvr.maths.Vector;
import ejvr.physics.kinematics.KinematicEntityImmutable;
import ejvr.physics.kinematics.World;
import processing.core.PApplet;

import java.util.ArrayList;


public class GraphicsTest extends PApplet {
    private AABB_Debug debug = new AABB_Debug(new Point(400,400),100,60);
    //(Point point, Vector velocity, Vector netForce, double mass)
    private KinematicEntityImmutable ke = new KinematicEntityImmutable(new Point(100,100), new Vector(0,0),new Vector(0,0),10);
    private ArrayList<KinematicEntityImmutable> entityList = new ArrayList<>();
    private World world = new World();
    int frameItter = 0;


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
        //ke.setNetForce(new Vector(100,0));

    }

    public void draw() {
        background(0);
        frameItter++;
        //debug.setPos(new Point(mouseX,mouseY));
        debug.setAngle((mouseX - 100)/100.0);
        debug.draw(this);
        drawCursor();

        world.stepPhysics(1/60.0);



        for (Point p :world.getPositions()
             ) {
            circle(p.xFloat(),p.yFloat(),10);
        }





        text("SPD: " + ke.velocity.magnitude(),20,20);
        //text("ACC: " + ke..magnitude(),20,50);

        if(frameItter > 60){
            frameItter = 0;
            System.out.println("FPS: " + frameRate);
        }

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
        } else {
            //world.add(new KinematicEntityF(new Point(mouseX,mouseY), new Vector(0,0),new Vector(0,0),10));
        }
    }
}
