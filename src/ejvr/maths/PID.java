package ejvr.maths;

public class PID {
    private double lastError = 0;
    private double lastIntegral = 0;

    public final double K_p;
    public final double K_i;
    public final double K_D;
    public final double bias = 0;

    public PID(double k_p, double k_i, double k_d) {
        K_p = k_p;
        K_i = k_i;
        K_D = k_d;
    }

    public double getSignal(double target, double actual, double dt){
        var error = target-actual;
        var integral = lastIntegral + error * dt;
        var derivative = (error - lastError)/dt;
        double output = K_p * error + K_i * integral + K_D * derivative + bias;
        lastError = error;
        lastIntegral = integral;
        return output;
    }
}
