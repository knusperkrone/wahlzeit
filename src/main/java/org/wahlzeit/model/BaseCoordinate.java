package org.wahlzeit.model;

public abstract class BaseCoordinate implements Coordinate {

    protected static double DELTA = 0.0001;
    protected static double EPSILON = 0.00000001;

    /*
     * Template methods
     */

    protected abstract CartesianCoordinate doAsCartesianCoordinate();

    protected abstract SphericCoordinate doAsSphericCoordinate();

    protected abstract boolean doIsEquals(BaseCoordinate coordinate);

    protected abstract boolean doAssertValid();

    protected abstract int doHashCode();

    /*
     * Business methods
     */

    public double getCartesianDistance(BaseCoordinate coordinate) {
        assertValidCoordinate(coordinate);
        return asCartesianCoordinate().doGetCartesianDistance(coordinate);
    }

    public double getCentralAngle(BaseCoordinate coordinate) {
        assertValidCoordinate(coordinate);
        return asSphericCoordinate().doGetCentralAngle(coordinate);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BaseCoordinate) {
            return isEquals((BaseCoordinate) obj);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return doHashCode();
    }

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

    public boolean isEquals(BaseCoordinate coordinate) {
        assertNonNull(coordinate);
        return doIsEquals(coordinate);
    }

    /*
     * Assertions
     */

    private static void assertValidCoordinate(BaseCoordinate coordinate) {
        assertNonNull(coordinate);
        if (!coordinate.doAssertValid()) {
            throw new IllegalStateException("Invalid Coordinate");
        }
    }

    private static void assertNonNull(BaseCoordinate obj) {
        if (obj == null) {
            throw new NullPointerException("Given coordinate was null");
        }
    }
}
