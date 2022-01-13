package maths;

public class Point {
    public static Point fromPolar(double angle, double radius){
        double newX = Math.cos(angle) * radius;
        double newY = Math.sin(angle) * radius;
        return new Point(newX,newY);
    }


    public final double x;
    public final double y;


    public Point(Point p){
        this.x = p.x;
        this.y = p.y;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }





    public double diffX(Point p){
        return x - p.x;
    }

    public double diffY(Point p){
        return y - p.y;
    }

    public double radiusSquared(){
        return (x*x) + (y*y);
    }

    public double radius() {
        return Math.sqrt(radiusSquared());
    }

    public double angle() {
        return Math.atan2(y,x);
    }

    public float xFloat(){
        return (float) x;
    }

    public float yFloat(){
        return (float) y;
    }

    public boolean isEqual(Point p) {
        if(this.x == p.x && this.y == p.y){
            return true;
        }
        else{
            return false;
        }
    }

    public Point copy() {
        return new Point(this.x,this.y);
    }
}
