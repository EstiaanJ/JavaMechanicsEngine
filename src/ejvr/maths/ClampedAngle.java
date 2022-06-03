package ejvr.maths;

public record ClampedAngle(double angle) {
    public static final double TAU = 2 * Math.PI;

    public static ClampedAngle fromDegrees(double inDegrees) {
        return new ClampedAngle(inDegrees * (Math.PI / 180));
    }

    public ClampedAngle(double angle) {
        if (angle < 0) {
            double sub = Math.abs(angle) % TAU;
            this.angle = TAU - sub;
        } else {
            this.angle = angle % TAU;
        }
    }

    public double angleDegrees() {
        return angle * (180 / (Math.PI));
    }

    public boolean equals(ClampedAngle check) {
        return (this.angle == check.angle);
    }
}
