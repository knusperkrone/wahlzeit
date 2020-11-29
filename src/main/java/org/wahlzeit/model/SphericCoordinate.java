package org.wahlzeit.model;

public class SphericCoordinate implements Coordinate {

    private final double radius;
    private final double theta;
    private final double phi;


    public SphericCoordinate(double radius, double theta, double phi) {
        this.radius = radius;
        this.theta = theta;
        this.phi = phi;
    }

    /*
     * Coordinate contract
     */

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        double x = radius * Math.sin(theta) * Math.cos(phi);
        double y = radius * Math.sin(theta) * Math.sin(phi);
        double z = radius * Math.cos(theta);
        return new CartesianCoordinate(x, y, z);
    }

    @Override
    public double getCartesianDistance(Coordinate coordinate) {
        return asCartesianCoordinate().getCartesianDistance(coordinate);
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        return this;
    }

    @Override
    public double getCentralAngle(Coordinate coordinate) {
        CartesianCoordinate cartesianSelf = asCartesianCoordinate();
        CartesianCoordinate cartesianOther = coordinate.asCartesianCoordinate();

        double lat1 = cartesianSelf.getX();
        double lon1 = cartesianSelf.getY();
        double lat2 = cartesianOther.getX();
        double lon2 = cartesianOther.getY();

        // haversine
        double a = Math.pow(Math.sin((lat2 - lat1) / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin((lon2 - lon1) / 2), 2);
        double angle = 2 * Math.asin(Math.min(1, Math.sqrt(a)));

        return angle;
    }

    @Override
    public boolean isEquals(Coordinate other) {
        if (this == other) {
            return true;
        }

        SphericCoordinate sphericOther = other.asSphericCoordinate();
        return (sphericOther.radius - radius < DELTA) && (sphericOther.theta - theta) < DELTA && (sphericOther.phi - phi) < DELTA;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coordinate) {
            return isEquals((Coordinate) obj);
        }
        return false;
    }

    /*
     * Getter
     */

    public double getRadius() {
        return radius;
    }

    public double getTheta() {
        return theta;
    }

    public double getPhi() {
        return phi;
    }
}
