package org.wahlzeit.model;

import java.util.Random;

public abstract class BaseId {
    /**
     *
     */
    public static final int BUFFER_SIZE_INCREMENT = 64;

    /**
     * What a hack :-)
     */
    public static final int ID_START = getFromString("x1abz") + 1;

    /**
     *
     */
    protected static Random randomNumber = new Random(System.currentTimeMillis());

    /**
     *
     */
    protected int value = 0;
    protected String stringValue = null;


    /**
     *
     */
    public boolean equals(Object o) {
        // @FIXME

        if (!(o instanceof PhotoId)) {
            return false;
        }

        PhotoId pid = (PhotoId) o;
        return isEqual(pid);
    }

    /**
     *
     */
    public boolean isEqual(PhotoId other) {
        return other.value == value;
    }

    /**
     * @methodtype get
     */
    public int hashCode() {
        return value;
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
        return stringValue;
    }

    /**
     *
     */
    public static String getFromInt(int id) {
        StringBuffer result = new StringBuffer(10);

        id += ID_START;
        for (; id > 0; id = id / 36) {
            char letterOrDigit;
            int modulus = id % 36;
            if (modulus < 10) {
                letterOrDigit = (char) ((int) '0' + modulus);
            } else {
                letterOrDigit = (char) ((int) 'a' - 10 + modulus);
            }
            result.insert(0, letterOrDigit);

        }

        return "x" + result.toString();
    }

    /**
     *
     */
    public static int getFromString(String value) {
        int result = 0;
        for (int i = 1; i < value.length(); i++) {
            int temp = 0;
            char letterOrDigit = value.charAt(i);
            if (letterOrDigit < 'a') {
                temp = (int) letterOrDigit - '0';
            } else {
                temp = 10 + (int) letterOrDigit - 'a';
            }
            result = result * 36 + temp;
        }

        result -= ID_START;
        if (result < 0) {
            result = 0;
        }

        return result;
    }

}
