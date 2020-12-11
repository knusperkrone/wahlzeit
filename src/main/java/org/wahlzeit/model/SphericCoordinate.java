package org.wahlzeit.model;

public class SphericCoordinate extends BaseCoordinate {

    private final double radius;
    private final double theta;
    private final double phi;


    public SphericCoordinate(double radius, double theta, double phi) {
        this.radius = radius;
        this.theta = theta;
        this.phi = phi;
    }

    /*
     * Business methods
     */

    @Override
    protected boolean doAssertValid() {
        return radius >= 0 && theta >= 0 && phi >= 0;
    }

    public double doGetCentralAngle(Coordinate coordinate) {
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

    /*
     * Coordinate contract
     */

    @Override
    public CartesianCoordinate doAsCartesianCoordinate() {
        double x = radius * Math.sin(theta) * Math.cos(phi);
        double y = radius * Math.sin(theta) * Math.sin(phi);
        double z = radius * Math.cos(theta);
        return new CartesianCoordinate(x, y, z);
    }

    @Override
    public SphericCoordinate doAsSphericCoordinate() {
        return this;
    }

    @Override
    public boolean doIsEquals(Coordinate other) {
        if (this == other) {
            return true;
        }

        SphericCoordinate sphericOther = other.asSphericCoordinate();
        return (sphericOther.radius - radius < DELTA) && (sphericOther.theta - theta) < DELTA && (sphericOther.phi - phi) < DELTA;
    }

    @Override
    protected int doHashCode() {
        int hash = 7;
        hash = 31 * hash + Double.hashCode(radius);
        hash = 31 * hash + Double.hashCode(theta);
        hash = 31 * hash + Double.hashCode(phi);
        return hash;
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

    /*
     * Setter
     */

    public SphericCoordinate setRadius(double newRadius) {
        SphericCoordinate sphericCoordinate = new SphericCoordinate(newRadius, theta, phi);
        if (!sphericCoordinate.doAssertValid()) {
            throw new IllegalArgumentException("Given tadius was invalid: " + radius);
        }
        return sphericCoordinate;
    }


    public SphericCoordinate setTheta(double newTheta) {
        SphericCoordinate sphericCoordinate = new SphericCoordinate(radius, newTheta, phi);
        if (!sphericCoordinate.doAssertValid()) {
            throw new IllegalArgumentException("Given theta was invalid: " + radius);
        }
        return sphericCoordinate;
    }

    public SphericCoordinate setPhi(double newPhi) {
        SphericCoordinate sphericCoordinate = new SphericCoordinate(radius, theta, newPhi);
        if (!sphericCoordinate.doAssertValid()) {
            throw new IllegalArgumentException("Given phi was invalid: " + radius);
        }
        return sphericCoordinate;
    }
}
