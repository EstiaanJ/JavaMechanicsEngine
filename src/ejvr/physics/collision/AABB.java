package ejvr.physics.collision;

import ejvr.math.real.VectorDouble;


public class AABB extends BoundingBox{
    private double lastAngle;
    private double totalWidth;
    private double totalHeight;

    public AABB(VectorDouble position, double width, double height) {
        super(position, width, height);
        updateTotalWidth();
        updateTotalHeight();
    }

    private void updateTotalHeight(){
        lastAngle = super.getAngle();
        totalHeight = Math.abs(Math.sin(lastAngle)) * super.width + Math.abs(Math.cos(lastAngle)) * super.height; //Math.abs(Math.sin(lastAngle) * super.width + Math.cos(lastAngle) * super.height);
    }

    private void updateTotalWidth(){
        lastAngle = super.getAngle();
        totalWidth = Math.abs(Math.cos(lastAngle)) * super.width + Math.abs(Math.sin(lastAngle)) * super.height;
    }


    public double getXMax() {
        if (super.getAngle() != lastAngle) {
            updateTotalWidth();
        }
        //updateTotalWidth();
        return super.position.x() + (totalWidth/2.0);
    }

    public double getXMin() {
        if (super.getAngle() != lastAngle) {
            updateTotalWidth();
        }
        //updateTotalWidth();
        return super.position.x() - (totalWidth/2.0);
    }

    public double getYMax() {
        if (super.getAngle() != lastAngle) {
            updateTotalHeight();
        }
        updateTotalHeight();
        return super.position.y() + (totalHeight/2.0);
    }

    public double getYMin() {
        if (super.getAngle() != lastAngle) {
            updateTotalHeight();
        }
        //updateTotalHeight();
        return super.position.y() - (totalHeight/2.0);
    }

    public boolean isInside(VectorDouble p){
        if(p.x() > this.getXMin() && p.x() < this.getXMax() && p.y() > this.getYMin() && p.y() < this.getYMax()) {
            return true;
        } else {
            return false;
        }
    }
}
