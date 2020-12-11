package org.wahlzeit.model;

public interface Coordinate {

    CartesianCoordinate asCartesianCoordinate();

    double getCartesianDistance(BaseCoordinate coordinate);

    SphericCoordinate asSphericCoordinate();

    double getCentralAngle(BaseCoordinate coordinate);

    boolean isEquals(BaseCoordinate coordinate);
}
