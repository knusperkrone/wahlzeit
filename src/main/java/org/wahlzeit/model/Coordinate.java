package org.wahlzeit.model;

public abstract class Coordinate {

    protected static double DELTA = 0.0001;

    public CartesianCoordinate asCartesianCoordinate() {
        CartesianCoordinate transformed = doAsCartesianCoordinate();
        assertValidCoordinate(transformed);
        return transformed;
    }

    public SphericCoordinate asSphericCoordinate() {
        SphericCoordinate transformed = doAsSphericCoordinate();
        assertValidCoordinate(transformed);
        return transformed;
    }

    public double getCartesianDistance(Coordinate coordinate) {
        assertValidCoordinate(coordinate);
        return doGetCartesianDistance(coordinate);
    }

    public double getCentralAngle(Coordinate coordinate) {
        assertValidCoordinate(coordinate);
        return doGetCentralAngle(coordinate);
    }

    boolean isEquals(Coordinate coordinate) {
        assertNonNull(coordinate);
        return doIsEquals(coordinate);
    }

    /*
     * Template methode
     */

    protected abstract CartesianCoordinate doAsCartesianCoordinate();

    protected abstract SphericCoordinate doAsSphericCoordinate();

    protected abstract double doGetCartesianDistance(Coordinate coordinate);

    protected abstract double doGetCentralAngle(Coordinate coordinate);

    protected abstract boolean doIsEquals(Coordinate coordinate);

    protected abstract boolean doAssertValid();

    /*
     * Assertions
     */

    private static void assertValidCoordinate(Coordinate coordinate) {
        assertNonNull(coordinate);
        if (!coordinate.doAssertValid()) {
            throw new IllegalStateException("Invalid Coordinate");
        }
    }

    private static void assertNonNull(Coordinate obj) {
        if (obj == null) {
            throw new NullPointerException("Given coordinate was null");
        }
    }
}
