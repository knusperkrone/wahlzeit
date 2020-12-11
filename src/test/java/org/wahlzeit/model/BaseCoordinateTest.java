package org.wahlzeit.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class BaseCoordinateTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testTransform() {
        // prepare
        BaseCoordinate cartesian = new CartesianCoordinate(3, 4, 5);
        BaseCoordinate spheric = new SphericCoordinate(3, 4, 5);
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
        BaseCoordinate expected = new CartesianCoordinate(3, 4, 5);
        // Execute
        BaseCoordinate actual = expected.asSphericCoordinate().asCartesianCoordinate();
        // Validate
        assertEquals(expected, actual);
    }

    @Test
    public void testCartesianDistance() {
        // Prepare
        double expected = 5.196;
        BaseCoordinate cartesianA = new CartesianCoordinate(3, 4, 5);
        BaseCoordinate cartesianB = new CartesianCoordinate(6, 7, 8);
        // Execute
        double actual = cartesianA.getCartesianDistance(cartesianB);
        // Validate
        assertTrue(expected - actual < BaseCoordinate.DELTA);
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
        assertTrue(expected - actual < BaseCoordinate.DELTA);
    }

    @Test
    public void testAssertEqualsNull() {
        // Prepare
        exceptionRule.expect(NullPointerException.class);
        exceptionRule.expectMessage("Given coordinate was null");
        BaseCoordinate expected = new CartesianCoordinate(3, 4, 5);

        // Execute
        expected.isEquals(null);
    }

    @Test
    public void testAssertInvalidCoordinate() {
        // Prepare
        BaseCoordinate coordinate = new SphericCoordinate(-1, -1, -1);
        // Execute & Validate
        assertFalse(coordinate.doAssertValid());
    }
}
