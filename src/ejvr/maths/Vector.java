package ejvr.maths;

public class Vector extends Point{
    public Vector(double x, double y) {
        super(x, y);
    }

    public Vector(Vector v){
        super(v.x,v.y);
    }

    public Vector(Point p) {
        super(p.x, p.y);
    }

    public double dotProduct(Vector vec) {
        return x * vec.x + y * vec.y;
    }

    public double crossProduct(Vector vec) {
        return (x * vec.y) - (y * vec.x);
    }

    public Vector getPerpendicular(){
        return new Vector(y,-x);
    }

    public double angleTo(Vector vec) {
        double cosAngle = this.dotProduct(vec)/(this.magnitude() * vec.magnitude());
        if(cosAngle > 1 && cosAngle < 1.1){
            cosAngle = 1.0;
        } else if (cosAngle < -1 && cosAngle > -1.1) {
            cosAngle = -1.0;
        }
        return Math.acos(cosAngle);
    }


    public double magnitude() {
        return Math.sqrt(super.radius());
    }

    public Vector add(Vector vec) {
        return new Vector(x + vec.x, y + vec.y);
    }

    public Vector negate(){
        return new Vector(-x,-y);
    }

    public Vector sub(Vector vec) {
        return this.add(vec.negate());
    }

    public Vector scale(double scalar) {
        return new Vector(x * scalar,y * scalar);
    }

    public Vector tangent() {
        return new Vector(-y,x);
    }

    public String toString(){
        return ("r: " + super.radius() + " x: " + x + " y: " + y);
    }

}
