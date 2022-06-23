package non_unit;

import ejvr.math.real.VectorDouble;
import ejvr.physics.Threads.MotionThread;
import ejvr.physics.WorldState;
import ejvr.physics.collision.CircularCollider;
import processing.core.PApplet;

import java.util.concurrent.ThreadLocalRandom;


public class GraphicsTest extends PApplet {
    private AABB_Debug debug = new AABB_Debug(new VectorDouble(400,400),100,60);
    //(Point point, Vector velocity, Vector netForce, double mass)
    //private KinematicBody ke = new KinematicBody(new Point(100,100), new Vector(0,0),new Vector(0,0),10);
   //private ArrayList<KinematicBody> entityList = new ArrayList<>();
    //private World world = new World();
    int frameItter = 0;
    private WorldState worldState = new WorldState(new CircularCollider[0]);


    //private CollisionThread collisionThread = new CollisionThread(worldState);
    private MotionThread motionThread = new MotionThread( worldState);
    //public Thread collisionThread2 = new Thread(collisionThread);
    public Thread motionThread2 = new Thread(motionThread);


    public static void main(String[] args) {
        PApplet.main(new String[]{"non_unit.GraphicsTest"});
    }

    public void settings(){
        size(800,800);
    }

    public void setup() {
        background(0);
        debug.setAngle(Math.PI/3);
        println("X-Max: " + debug.getXMax());
        println("X-Min: " + debug.getXMin());
        println("Y-Max: " + debug.getYMax());
        println("X-Min: " + debug.getYMin());
        //ke.setNetForce(new Vector(100,0));

        for(int i =0; i < 4; i++){
            VectorDouble pos = new VectorDouble(ThreadLocalRandom.current().nextDouble(0,800),ThreadLocalRandom.current().nextDouble(0,800));
            VectorDouble velocity = new VectorDouble(ThreadLocalRandom.current().nextDouble(-100,100),ThreadLocalRandom.current().nextDouble(-20,20));
            worldState = worldState.addCircularCollider(ThreadLocalRandom.current().nextDouble(5,40),pos,velocity, new VectorDouble(0,0));
        }
        motionThread.setWorldState(worldState);
        motionThread2.start();
    }

    public void draw() {
        fill(0,60);
        rect(0,0,2000,2000);
        //background(0);
        frameItter++;
        //debug.setPos(new Point(mouseX,mouseY));
        debug.setAngle((mouseX - 100)/100.0);
        debug.draw(this);
        //drawCursor();

        if(frameItter > 6){
            frameItter = 0;
            System.out.println("FPS: " + frameRate);
            System.out.println("N: " + worldState.colliderList().length);

        }


        this.worldState = motionThread.getWorldState();
        worldState.stepFrame(this);

    }

    public void drawCursor(){
        point(mouseX,mouseY);
        fill(255);
        text("MX: " + mouseX,mouseX,mouseY - 22);
        text("MY: " + mouseY,mouseX,mouseY - 10);
    }

    public void mousePressed(){
        if(debug.isInside(new VectorDouble(mouseX,mouseY))){
            println("INSIDE| mx: " + mouseX + " || my: " + mouseY);
        } else {
            for(int i = 0; i < 100; i++){
                worldState = worldState.addCircularCollider(3,new VectorDouble(mouseX+(i*7)-400,mouseY + ThreadLocalRandom.current().nextDouble(-100,100)),new VectorDouble(ThreadLocalRandom.current().nextDouble(-800,800), ThreadLocalRandom.current().nextDouble(-800,800)), new VectorDouble(0,300));
                motionThread.setWorldState(worldState);
            }

        }
    }
}
