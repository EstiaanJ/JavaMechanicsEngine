package ejvr.maths;

public record PID (double lastError, double lastIntegral, double K_p, double K_i, double K_D) {
    public PID step(double target, double actual, double dt){
        return new PID(error(target,actual),integral(target,actual,dt),K_p,K_i,K_D);
    }

    private double error (double target, double actual) {
        return target - actual;
    }

    private double derivative(double target, double actual, double dt){
        return (error(target,actual) - lastError)/dt;
    }

    private double integral(double target, double actual, double dt) {
        return lastIntegral + error(target,actual) * dt;
    }

    public double signal(double target, double actual, double dt){
        return K_p * error(target,actual) + K_i * integral(target,actual,dt) + K_D * derivative(target,actual,dt);
    }
}

