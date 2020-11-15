package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Location extends DataObject {

    private LocationId id;
    private Coordinate coordinate;

    public Location() {
        this.id = LocationId.getNextId();
        coordinate = new Coordinate(0.0, 0.0, 0.0);
    }

    public Location(LocationId locationId) {
        this.id = locationId;
        coordinate = new Coordinate(0.0, 0.0, 0.0);
    }

    public Location(LocationId locationId, ResultSet rset) throws SQLException {
        this.id = locationId;
        readFrom(rset);
    }

    @Override
    public String getIdAsString() {
        return String.valueOf(id.asInt());
    }

    @Override
    public void readFrom(ResultSet rset) throws SQLException {
        if (id == null) {
            id = LocationId.getIdFromInt(rset.getInt("id"));
        }

        // No extra sql wrapper - as location is just a data class
        double x = rset.getDouble("x");
        double y = rset.getDouble("y");
        double z = rset.getDouble("z");
        coordinate = new Coordinate(x, y, z);
    }

    @Override
    public void writeOn(ResultSet rset) throws SQLException {
        rset.updateInt("id", id.asInt());
        rset.updateDouble("x", coordinate.getX());
        rset.updateDouble("y", coordinate.getY());
        rset.updateDouble("z", coordinate.getZ());
    }

    @Override
    public void writeId(PreparedStatement stmt, int pos) throws SQLException {

    }

    public LocationId getId() {
        return id;
    }
}
