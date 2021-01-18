package org.wahlzeit.model;

import org.wahlzeit.utils.PatternInstance;

@PatternInstance(
        patternName = "Compositum",
        participants = { "Coordinate", "CartesianCoordinate", "SphericCoordinate" }
)
public interface Coordinate {

    double DELTA = 0.0001;
    double EPSILON = 0.00000001;

    CartesianCoordinate asCartesianCoordinate();

    double getCartesianDistance(Coordinate coordinate);

    SphericCoordinate asSphericCoordinate();

    double getCentralAngle(Coordinate coordinate);

    boolean isEquals(Coordinate coordinate);
}
