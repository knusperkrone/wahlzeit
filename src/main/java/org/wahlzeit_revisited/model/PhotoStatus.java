/*
 * Copyright (c) 2006-2009 by Dirk Riehle, http://dirkriehle.com
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit_revisited.model;

/**
 * The PhotoStatus of a Photo captures its state in the system.
 * A photo may be visible or invisible, it may have been flagged, and it may have been deleted.
 * These states are not mutually exclusive, hence the bitset simulation in this class.
 */
public enum PhotoStatus {

    /**
     *
     */

    VISIBLE(0), // no bit set
    INVISIBLE(1), // only invisible bit set
    FLAGGED(2), // flagged without or with invisible bit set
    MODERATED(3),
    DELETED(4);

    /**
     *
     */
    private static final int INVISIBLE_BIT = 0;
    private static final int FLAGGED_BIT = 1;
    private static final int MODERATED_BIT = 2;
    private static final int DELETED_BIT = 3;

    /**
     * All possible states of PhotoStatus
     */
    private static PhotoStatus[] allValues = {
            VISIBLE, INVISIBLE, FLAGGED, MODERATED, DELETED,
    };

    /**
     *
     */
    public static PhotoStatus getFromInt(int myValue) throws IllegalArgumentException {
        if ((myValue >= 0) && (myValue <= 15)) {
            return allValues[myValue];
        }

        throw new IllegalArgumentException("invalid PhotoStatus int: " + myValue);
    }

    /**
     *
     */
    private static String[] valueNames = {
            "visible", "invisible", "flagged", "flagged",
            "moderated", "moderated", "moderated", "moderated",
            "deleted", "deleted", "deleted", "deleted",
            "deleted", "deleted", "deleted", "deleted"
    };

    /**
     *
     */
    public static PhotoStatus getFromString(String myStatus) throws IllegalArgumentException {
        for (PhotoStatus status : PhotoStatus.values()) {
            if (valueNames[status.asInt()].equals(myStatus)) {
                return status;
            }
        }

        throw new IllegalArgumentException("invalid PhotoStatus string: " + myStatus);
    }

    /**
     *
     */
    private int value = 0;

    /**
     *
     */
    private PhotoStatus(int myValue) {
        value = myValue;
    }

    /**
     *
     */
    public int asInt() {
        return value;
    }

    /**
     *
     */
    public String asString() {
        return valueNames[value];
    }

    /**
     *
     */
    public PhotoStatus[] getAllValues() {
        return allValues;
    }

    /**
     *
     */
    public String getTypeName() {
        return "PhotoStatus";
    }

    /**
     *
     */
    public boolean isDisplayable() {
        return !isInvisible() && !isFlagged() && !isModerated() && !isDeleted();
    }

    /**
     *
     */
    public boolean isInvisible() {
        return (value & (1 << INVISIBLE_BIT)) != 0;
    }

    /**
     *
     */
    public PhotoStatus asInvisible(boolean yes) {
        return yes ? flag(INVISIBLE_BIT) : unflag(INVISIBLE_BIT);
    }

    /**
     *
     */
    public boolean isFlagged() {
        return (value & (1 << FLAGGED_BIT)) != 0;
    }

    /**
     *
     */
    public PhotoStatus asFlagged(boolean yes) {
        return yes ? flag(FLAGGED_BIT) : unflag(FLAGGED_BIT);
    }

    /**
     *
     */
    public boolean isModerated() {
        return (value & (1 << MODERATED_BIT)) != 0;
    }

    /**
     *
     */
    public PhotoStatus asModerated(boolean yes) {
        return yes ? flag(MODERATED_BIT) : unflag(MODERATED_BIT);
    }

    /**
     *
     */
    public boolean isDeleted() {
        return (value & (1 << DELETED_BIT)) != 0;
    }

    /**
     *
     */
    public PhotoStatus asDeleted(boolean yes) {
        return yes ? flag(DELETED_BIT) : unflag(DELETED_BIT);
    }

    /**
     *
     */
    protected PhotoStatus flag(int statusBit) {
        return allValues[value | (1 << statusBit)];
    }

    /**
     *
     */
    protected PhotoStatus unflag(int statusBit) {
        return allValues[value & (-1 - (1 << statusBit))];
    }

}
