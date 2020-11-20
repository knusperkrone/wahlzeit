package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScreenPhotoTest {

    @Test
    public void testConstructor() {
        // Prepare
        PhotoId expectedId = PhotoId.getNextId();
        LocationId expectedLocId = LocationId.getNextId();

        // Execute
        ScreenPhoto photo = new ScreenPhoto(expectedId, expectedLocId);

        // Validate
        assertEquals(expectedId.asInt(), photo.getId().asInt());
        assertEquals(expectedLocId.asInt(), photo.getLocation().getId().asInt());
    }
}
