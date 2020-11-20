package org.wahlzeit.model;

public class ScreenPhotoFactory extends PhotoFactory<ScreenPhoto> {

    @Override
    public ScreenPhoto createPhoto() {
        return new ScreenPhoto();
    }

    @Override
    public ScreenPhoto createPhoto(PhotoId id, LocationId lId) {
        return new ScreenPhoto(id, lId);
    }
}
