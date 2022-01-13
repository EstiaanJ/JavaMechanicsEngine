package maths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VectorTest {

    private Vector a = new Vector(1,1);
    private Vector b = new Vector(0.00001,-0.00002);
    private Vector c = new Vector(-1,-2);
    private Vector e = new Vector(1,-1);
    private Vector f= new Vector(-0.0000001,1000000);

    @Test
    void dotProduct() {
        assertEquals(-0.00001,a.dotProduct(b));
        assertEquals(2,a.dotProduct(a));
        assertEquals(-20.000000000001,b.dotProduct(f),0.0000000001);
    }

    @Test
    void crossProduct() {
        assertEquals(-1,a.crossProduct(c));
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