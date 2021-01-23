package org.wahlzeit.model;

public class ScreenPhoto extends Photo {

    private final Screen screen;

    public ScreenPhoto() {
        super();
        screen = ScreenManager.getInstance().createScreen();
    }

    public ScreenPhoto(PhotoId myId, LocationId locationId) {
        super(myId, locationId);
        screen = ScreenManager.getInstance().createScreen("Android", "Unknown");
    }

    public ScreenPhoto(PhotoId myId, LocationId locationId, String device) {
        super(myId, locationId);
        screen = ScreenManager.getInstance().createScreen(device, "Unknown");
    }

    public ScreenPhoto(PhotoId myId, LocationId locationId, String device, String application) {
        super(myId, locationId);
        screen = ScreenManager.getInstance().createScreen(device, application);
    }

    public String getDevice() {
        return screen.getDeviceName();
    }

    public String getApplication() {
        return screen.getApplication();
    }
}
