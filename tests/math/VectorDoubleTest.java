package math;


import ejvr.math.real.VectorDouble;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VectorDoubleTest {

    private VectorDouble a = new VectorDouble(1,1);
    private VectorDouble b = new VectorDouble(0.00001,-0.00002);
    private VectorDouble c = new VectorDouble(-1,-2);
    private VectorDouble e = new VectorDouble(1,-1);
    private VectorDouble f= new VectorDouble(-0.0000001,1000000);

    @Test
    void dotProduct() {
        assertEquals(-0.00001,a.dotProduct(b));
        assertEquals(2,a.dotProduct(a));
        assertEquals(-20.000000000001,b.dotProduct(f),0.0000000001);
    }

    @Test
    void crossProduct() {
        assertEquals(-1,a.crossProduct2D(c));
    }

    @Test
    void angleTo() {
    }

    @Test
    void magnitude() {
    }

    @Test
    void add() {
    }

    @Test
    void negate() {
    }

    @Test
    void sub() {
    }

    @Test
    void scale() {
    }

    @Test
    void tangent() {
    }
}