package org.wahlzeit.model;


public class ScreenPhotoManager extends PhotoManager<ScreenPhoto> {

    @Override
    PhotoFactory<ScreenPhoto> getPhotoFactory() {
        return ScreenPhotoFactory.getInstance();
    }
}
