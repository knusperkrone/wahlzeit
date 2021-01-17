package org.wahlzeit.model;

import org.wahlzeit.utils.PatternInstance;

@PatternInstance(
        patternName = "Commando Pattern",
        participants = { "BaseCoordinate", "CartesianCoordinate", "SphericCoordinate" }
)
public abstract class BaseCoordinate implements Coordinate {

    /*
     * Template methods
     */

    protected abstract CartesianCoordinate doAsCartesianCoordinate();

    protected abstract SphericCoordinate doAsSphericCoordinate();

    protected abstract boolean doIsEquals(Coordinate coordinate);

    protected abstract boolean doAssertValid();

    protected abstract int doHashCode();

    /*
     * Business methods
     */

    public double getCartesianDistance(Coordinate coordinate) {
        assertValidCoordinate(coordinate);
        return asCartesianCoordinate().doGetCartesianDistance(coordinate);
    }

    public double getCentralAngle(Coordinate coordinate) {
        assertValidCoordinate(coordinate);
        return asSphericCoordinate().doGetCentralAngle(coordinate);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coordinate) {
            return isEquals((Coordinate) obj);
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

    public boolean isEquals(Coordinate coordinate) {
        assertNonNull(coordinate);
        return doIsEquals(coordinate);
    }

    /*
     * Assertions
     */

    private static void assertValidCoordinate(Coordinate coordinate) {
        assertNonNull(coordinate);
        if (coordinate instanceof BaseCoordinate) {
            if (!((BaseCoordinate) coordinate).doAssertValid()) {
                throw new IllegalStateException("Invalid Coordinate");
            }
        }
    }

    private static void assertNonNull(Coordinate obj) {
        if (obj == null) {
            throw new NullPointerException("Given coordinate was null");
        }
    }
}
