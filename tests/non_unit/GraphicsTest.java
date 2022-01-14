package non_unit;

import maths.Point;
import processing.core.PApplet;

public class GraphicsTest extends PApplet {
    private AABB_Debug debug = new AABB_Debug(new Point(400,400),100,60);

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

    }

    public void draw() {
        background(0);
        //debug.setPos(new Point(mouseX,mouseY));
        debug.setAngle((mouseX - 100)/100.0);
        debug.draw(this);
        drawCursor();

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
