package org.wahlzeit.model;

public class CartesianCoordinate extends Coordinate {

    private final double x;
    private final double y;
    private final double z;

    public CartesianCoordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /*
     * Coordinate contract
     */

    @Override
    protected boolean doAssertValid() {
        return true;
    }

    @Override
    public boolean doIsEquals(Coordinate other) {
        if (this == other) {
            return true;
        }

        CartesianCoordinate cartesianOther = other.asCartesianCoordinate();
        return (cartesianOther.x - x < DELTA) && (cartesianOther.y - y < DELTA) && (cartesianOther.z - z < DELTA);
    }

    @Override
    public CartesianCoordinate doAsCartesianCoordinate() {
        return this;
    }

    @Override
    public double doGetCartesianDistance(Coordinate coordinate) {
        CartesianCoordinate cartesianOther = coordinate.asCartesianCoordinate();
        return Math.sqrt(
                Math.pow(x - cartesianOther.getX(), 2) +
                        Math.pow(y - cartesianOther.getY(), 2) +
                        Math.pow(z - cartesianOther.getZ(), 2)
        );
    }

    @Override
    public SphericCoordinate doAsSphericCoordinate() {
        double radius = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
        double theta = Math.acos(z / radius);
        double phi = Math.atan(y / x);

        return new SphericCoordinate(radius, theta, phi);
    }

    @Override
    public double doGetCentralAngle(Coordinate coordinate) {
        return asSphericCoordinate().getCentralAngle(coordinate);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coordinate) {
            return isEquals((Coordinate) obj);
        }
        return false;
    }

    /*
     * Getter/Setter
     */

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }


}
