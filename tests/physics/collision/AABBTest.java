package physics.collision;

import maths.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AABBTest {
    private AABB box = new AABB(new Point(0,0),605.26,280.078);
    private AABB box2 = new AABB(new Point(1522.557,1620.108),605.26,280.078);
    private AABB box3 = new AABB(new Point(15.22557,16.20108),6.0526,2.80078);
    private AABB boxFlat = new AABB(new Point(0,0),0.01,0.02);
    private AABB box90 = new AABB(new Point(0,0),5000,6000);
    private AABB box180 = new AABB(new Point(0,0),5000,6000);
    private AABB boxNeg = new AABB(new Point(-3000,-3000),605.26,280.078);


    @BeforeEach
    void setUp() {
        box.setAngle(0.278170576);
        box2.setAngle(0.278170576);
        box3.setAngle(0.278170576);
        boxFlat.setAngle(0);
        box90.setAngle(Math.PI/2);
        box180.setAngle(Math.PI);
        boxNeg.setAngle(0.278170576);
    }

    @Test
    void getXMax() {
        assertEquals(658.901/2.0,box.getXMax(),0.01);
        assertEquals(1852.008,box2.getXMax(),0.01);
        assertEquals(0.01/2,boxFlat.getXMax(),0.00001);
        assertEquals(3000,box90.getXMax(),0.01);
        assertEquals(2500,box180.getXMax(),0.01);
    }

    @Test
    void getXMin() {
        assertEquals(-658.901/2.0,box.getXMin(),0.01);
        assertEquals(1193.106,box2.getXMin(),0.01);
        assertEquals(-0.01/2,boxFlat.getXMin(),0.00001);
    }

    @Test
    void getYMax() {
        assertEquals(435.511/2.0,box.getYMax(),0.01);
        assertEquals(1837.863,box2.getYMax(),0.01);
    }

    @Test
    void getYMin() {
        assertEquals(-435.511/2.0,box.getYMin(),0.01);
        assertEquals(1402.351,box2.getYMin(),0.01);
    }

    @Test
    void isInside() {
        assertEquals(true, box2.isInside(new Point(1343,1493)));
        assertEquals(false, box2.isInside(new Point(1035,1568)));
        assertEquals(false, box2.isInside(new Point(-1343,1493)));
        assertEquals(false, box2.isInside(new Point(-1343,-1493)));
        assertEquals(false, box2.isInside(new Point(1343,-1493)));
        assertEquals(false, box2.isInside(new Point(1343,0)));
        assertEquals(false, box2.isInside(new Point(0,1493)));
        assertEquals(false, box2.isInside(new Point(0,0)));
        assertEquals(true, box2.isInside(box2.getPos()));


        assertEquals(true, box90.isInside(box90.getPos()));
        assertEquals(true, box180.isInside(box180.getPos()));

        assertEquals(true, box90.isInside(new Point(box90.getPos().x - 10,box90.getPos().y + 10)));
        assertEquals(true, boxNeg.isInside(boxNeg.getPos()));
    }
}