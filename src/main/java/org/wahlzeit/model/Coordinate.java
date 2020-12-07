package org.wahlzeit.model;

public abstract class Coordinate {

    protected static double DELTA = 0.0001;

    /*
     * Template methods
     */

    public abstract CartesianCoordinate asCartesianCoordinate();

    public abstract SphericCoordinate asSphericCoordinate();

    public abstract boolean isEquals(Coordinate coordinate);

    /*
     * Business methods
     */

    public double getCartesianDistance(Coordinate coordinate) {
        return asCartesianCoordinate().doGetCartesianDistance(coordinate);
    }

    public double getCentralAngle(Coordinate coordinate) {
        return asSphericCoordinate().doGetCentralAngle(coordinate);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coordinate) {
            return isEquals((Coordinate) obj);
        }
        return false;
    }

}
