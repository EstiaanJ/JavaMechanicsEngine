package ejvr.maths;

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

    public Point rotateAboutOrigin(double angle){
        return new Point(rotatePoint(0,0,angle,this));
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

    public double distanceBetween(Point point){
       double xFinal = this.x - point.x;
       double yFinal = this.y - point.y;
       return Math.sqrt((xFinal * xFinal) + (yFinal * yFinal));
    }

    public Point copy() {
        return new Point(this.x,this.y);
    }

    public Vector asVector() {
        return new Vector(this.x,this.y);
    }

    /*
    Adapted from:
    twe4ked, Nils Pipenbrinck
    Stack Overflow 2022
    https://stackoverflow.com/questions/2259476/rotating-a-point-about-another-point-2d
     */
    public static Point rotatePoint(double cx, double cy, double angle, Point p)
    {
        double newX;
        double newY;

        double sine = Math.sin(angle);
        double cosine = Math.cos(angle);

        // translate point to origin:
        newX = p.x - cx;
        newY = p.y - cy;
        //Point returnPoint = new Point(p.x - cx,p.y - cy);

        // rotate point
        newX = newX * cosine - newY * sine;
        newY = newX * sine + newY * cosine;

        return new Point(newX + cx, newY + cy);
    }
}
