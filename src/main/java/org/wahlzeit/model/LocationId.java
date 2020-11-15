package org.wahlzeit.model;

public class LocationId extends BaseId {
    /**
     * 0 is never returned from nextValue; first value is 1
     */
    protected static int currentId = 0;

    /**
     *
     */
    public static final LocationId NULL_ID = new LocationId(0);

    /**
     *
     */
    protected static LocationId[] ids = new LocationId[BUFFER_SIZE_INCREMENT];

    /**
     * What a hack :-)
     */
    public static final int ID_START = getFromString("x1abz") + 1;

    /**
     *
     */
    public static int getCurrentIdAsInt() {
        return currentId;
    }

    /**
     *
     */
    public static synchronized void setCurrentIdFromInt(int id) {
        currentId = id;
        ids = new LocationId[currentId + BUFFER_SIZE_INCREMENT];
        ids[0] = NULL_ID;
    }

    /**
     *
     */
    public static synchronized int getNextIdAsInt() {
        currentId += 1;
        if (currentId >= ids.length) {
            LocationId[] nids = new LocationId[currentId + BUFFER_SIZE_INCREMENT];
            System.arraycopy(ids, 0, nids, 0, currentId);
            ids = nids;
        }
        return currentId;
    }

    /**
     *
     */
    public static LocationId getIdFromInt(int id) {
        if ((id < 0) || (id > currentId)) {
            return NULL_ID;
        }

        // @FIXME http://en.wikipedia.org/wiki/Double-checked_locking
        LocationId result = ids[id];
        if (result == null) {
            synchronized (ids) {
                result = ids[id];
                if (result == null) {
                    result = new LocationId(id);
                    ids[id] = result;
                }
            }
        }

        return result;
    }

    /**
     *
     */
    public static LocationId getIdFromString(String id) {
        return getIdFromInt(getFromString(id));
    }

    /**
     *
     */
    public static LocationId getNextId() {
        return getIdFromInt(getNextIdAsInt());
    }

    /**
     *
     */
    public static LocationId getRandomId() {
        int max = getCurrentIdAsInt() - 1;
        int id = randomNumber.nextInt();
        id = (id == Integer.MIN_VALUE) ? id++ : id;
        id = (Math.abs(id) % max) + 1;
        return getIdFromInt(id);
    }

    /**
     *
     */
    protected LocationId(int myValue) {
        value = myValue;
        stringValue = getFromInt(myValue);
    }

    /**
     *
     */
    public boolean isNullId() {
        return this == NULL_ID;
    }
}
