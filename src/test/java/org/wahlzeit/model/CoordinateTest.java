package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class CoordinateTest {

    @Test
    public void testTransform() {
        // prepare
        Coordinate cartesian = new CartesianCoordinate(3, 4, 5);
        Coordinate spheric = new SphericCoordinate(3, 4, 5);
        // validate
        assertSame(cartesian, cartesian.asCartesianCoordinate());
        assertSame(spheric, spheric.asSphericCoordinate());
        assertNotSame(cartesian, cartesian.asSphericCoordinate());
        assertNotSame(spheric, spheric.asCartesianCoordinate());
        assertEquals(cartesian, cartesian.asSphericCoordinate());
        assertEquals(spheric, spheric.asSphericCoordinate());
    }

    @Test
    public void testCartesianToSphericAndBack() {
        // Prepare
        Coordinate expected = new CartesianCoordinate(3, 4, 5);
        // Execute
        Coordinate actual = expected.asSphericCoordinate().asCartesianCoordinate();
        // Validate
        assertEquals(expected, actual);
    }

    @Test
    public void testCartesianDistance() {
        // Prepare
        double expected = 5.196;
        Coordinate cartesianA = new CartesianCoordinate(3, 4, 5);
        Coordinate cartesianB = new CartesianCoordinate(6, 7, 8);
        // Execute
        double actual = cartesianA.getCartesianDistance(cartesianB);
        // Validate
        assertTrue(expected - actual < Coordinate.DELTA);
    }

    @Test
    public void testSphericAngle() {
        // Prepare
        double expected = 0.447;
        CartesianCoordinate cartesianA = new CartesianCoordinate(3, 4, 5);
        CartesianCoordinate cartesianB = new CartesianCoordinate(6, 7, 8);
        // Execute
        double actual = cartesianA.getCentralAngle(cartesianB);
        // Validate
        assertTrue(expected - actual < Coordinate.DELTA);
    }
}
