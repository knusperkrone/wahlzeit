package org.wahlzeit.model;

public class Screen {

    private final ScreenType screenType;
    private final String application;

    public Screen(ScreenType screenType, String application) {
        this.screenType = screenType;
        this.application = application;
    }

    public String getDeviceName() {
        return screenType.getDeviceName();
    }

    public String getApplication() {
        return application;
    }

}
