package org.wahlzeit.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ScreenManager {

    private static ScreenManager instance;

    Map<String, ScreenType> screenTypes = new ConcurrentHashMap<>(); // device, type

    public static ScreenManager getInstance() {
        if (instance == null) {
            instance = new ScreenManager();
        }
        return instance;
    }

    public Screen createScreen(String device, String application) {
        assertValidScreenDevice(device);
        ScreenType type = getScreenType(device);
        return type.createInstance(application);
    }

    public Screen createScreen() {
        ScreenType type = getScreenType(ScreenType.BASE_TYPE);
        return type.createInstance("");
    }

    private ScreenType getScreenType(String device) {
        if (screenTypes.containsKey(device)) {
            screenTypes.put(device, new ScreenType(device));
        }
        return screenTypes.get(device);
    }

    private void assertValidScreenDevice(String device) {
        if (device == null || !device.startsWith(ScreenType.BASE_TYPE)) {
            throw new AssertionError("Invalid deviceType: " + device);
        }
    }

}
