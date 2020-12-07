package org.wahlzeit.model;

public interface Coordinate {

    double DELTA = 0.0001;

    CartesianCoordinate asCartesianCoordinate();

    double getCartesianDistance(Coordinate coordinate);

    SphericCoordinate asSphericCoordinate();

    double getCentralAngle(Coordinate coordinate);

    boolean isEquals(Coordinate coordinate);
}
