package maths;

import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.

class PointTest {
    private Point testPointA;
    private Point testPointB;
    private Point testPointC;
    private Point testPointD;
    private Point testPointE;
    private Point testPointF;
    private Point testPointG;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        testPointA = new Point(1,1);
        testPointB = new Point(2000,2000);
        testPointC = new Point(-1,-1);
        testPointD = new Point(1,-1);
        testPointE = new Point(0,0);
        testPointF = new Point(0.00000000001,-10000);
        testPointG = new Point(Double.MAX_VALUE,Double.MIN_VALUE);
    }

    @org.junit.jupiter.api.Test
    void isEqual() {
        assertEquals(true,testPointA.isEqual(new Point(1,1)));
        assertNotEquals(false,testPointA.isEqual(testPointF));
    }

    @org.junit.jupiter.api.Test
    void diffX() {
        double actual = testPointA.diffX(testPointB);
        assertEquals(-1999,actual);

        actual = testPointG.diffX(testPointB);
        assertEquals(Double.MAX_VALUE - 2000,actual);

        for(double i = -10; i < 10; i++){
            for (double j = -10.0; j < 10.0; j += (1.0/3.0)){
                Point p = new Point(i,j);
                Point c = new Point(j,i);

                assertEquals(i-j,p.diffX(c));
            }
        }
    }

    @org.junit.jupiter.api.Test
    void diffY() {
        for(double i = -10; i < 10; i++){
            for (double j = -10.0; j < 10.0; j += (1.0/3.0)){
                Point p = new Point(i,j);
                Point c = new Point(j,i);

                assertEquals(j-i,p.diffY(c));
            }
        }

    }

    @org.junit.jupiter.api.Test
    void radiusSquared() {
        assertEquals(1.4142135623731,testPointA.radius(),0.0000000001);
    }

    @org.junit.jupiter.api.Test
    void radius() {
        assertEquals(1.4142135623731,testPointA.radius(),0.0000000001);
        assertEquals(2828.4271247462,testPointB.radius(),0.0000000001);
        assertEquals(1.4142135623731,testPointC.radius(),0.0000000001);
        assertEquals(1.4142135623731,testPointD.radius(),0.0000000001);
        assertEquals(0,testPointE.radius(),0.0000000001);
        assertEquals(10000,testPointF.radius(),0.0000000001);
        assertEquals(Double.POSITIVE_INFINITY,testPointG.radius(),0.0000000001);
    }

    @org.junit.jupiter.api.Test
    void angle() {
        //testPointA = new Point(1,1);
        //testPointB = new Point(2000,2000);
        //testPointC = new Point(-1,-1);
        //testPointD = new Point(1,-1);
        //testPointE = new Point(0,0);
        //testPointF = new Point(0.00000000001,-10000);
        //testPointG = new Point(Double.MAX_VALUE,Double.MIN_VALUE);

        assertEquals(0.7853981633,testPointA.angle(),0.0000000001);
        assertEquals(0.7853981633,testPointB.angle(),0.0000000001);
        assertEquals(-2.3561944901923,testPointC.angle(),0.0000000001);
        assertEquals(-0.7853981633,testPointD.angle(),0.0000000001);
        assertEquals(0,testPointE.angle(),0.0000000001);
        assertEquals(-1.5707963267949,testPointF.angle(),0.0000000001);
        assertEquals(0,testPointG.angle(),0.0000000001);

    }
}