package org.wahlzeit.model;

public class ScreenType {

    public static final String BASE_TYPE = "DISPLAY_";

    private final String device;

    public ScreenType(String deviceName) {
        this.device = deviceName;
    }

    public Screen createInstance(String application) {
        return new Screen(this, application);
    }

    public String getDeviceName() {
        return device; // Device name
    }

    public boolean isSubtype() {
        return device.length() != BASE_TYPE.length();
    }

}
