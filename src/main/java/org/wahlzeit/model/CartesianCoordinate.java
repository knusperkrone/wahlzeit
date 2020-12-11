package org.wahlzeit.model;

public class CartesianCoordinate extends BaseCoordinate {

    private final double x;
    private final double y;
    private final double z;

    public CartesianCoordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /*
     * Business methods
     */

    protected double doGetCartesianDistance(Coordinate coordinate) {
        CartesianCoordinate cartesianOther = coordinate.asCartesianCoordinate();
        return Math.sqrt(
                Math.pow(x - cartesianOther.getX(), 2) +
                        Math.pow(y - cartesianOther.getY(), 2) +
                        Math.pow(z - cartesianOther.getZ(), 2)
        );
    }


    /*
     * Coordinate contract
     */

    @Override
    protected boolean doAssertValid() {
        return true;
    }

    @Override
    public CartesianCoordinate doAsCartesianCoordinate() {
        return this;
    }

    @Override
    public SphericCoordinate doAsSphericCoordinate() {
        double dividerSafeX = x == 0 ? EPSILON : x;
        double radius = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
        double theta = Math.acos(z / radius);
        double phi = Math.atan(y / dividerSafeX);

        return new SphericCoordinate(radius, theta, phi);
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
    protected int doHashCode() {
        int hash = 7;
        hash = 31 * hash + Double.hashCode(x);
        hash = 31 * hash + Double.hashCode(y);
        hash = 31 * hash + Double.hashCode(z);
        return hash;
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

    public CartesianCoordinate setX(double newX) {
        return new CartesianCoordinate(newX, y, z);
    }

    public CartesianCoordinate setY(double newY) {
        return new CartesianCoordinate(x, newY, z);
    }

    public CartesianCoordinate setZ(double newZ) {
        return new CartesianCoordinate(x, y, newZ);
    }

}
