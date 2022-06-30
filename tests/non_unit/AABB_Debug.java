package non_unit;

import ejvr.math.real.VectorDouble;
import ejvr.physics.collision.AABB;
import processing.core.PApplet;

public class AABB_Debug extends AABB {
    public AABB_Debug(VectorDouble position, double width, double height) {
        super(position, width, height);
    }

    public void draw(PApplet context){

        context.rectMode(PApplet.CENTER);
        context.noFill();
        context.stroke(0,255,0);
        context.pushMatrix();
            context.translate(position.xFloat(),position.yFloat());
            context.rotate((float) super.getAngle());
            context.rect(0,0,(float) width,(float) height);
        context.popMatrix();

        context.stroke(255,0,0);
        context.rectMode(PApplet.CORNERS);
        context.rect((float)getXMin(),(float)getYMin(),(float)getXMax(),(float)getYMax());
    }
}
