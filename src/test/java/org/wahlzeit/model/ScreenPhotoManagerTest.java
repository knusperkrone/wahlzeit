package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ScreenPhotoManagerTest {

    private PhotoManager<?> manager;

    @Before
    public void initPhotoManger() {
        manager = PhotoManager.getInstance();
    }

    @Test
    public void testPhotoManager() {
        assertTrue(manager instanceof ScreenPhotoManager);
    }

    @Test
    public void testCreatePhoto() {
        File mockFile = new File("./");
        try {
            ScreenPhoto screenPhoto = (ScreenPhoto) manager.createPhoto(mockFile);
        } catch (ClassCastException e) {
            fail(e.getMessage());
        } catch (Exception ignored) {

        }
    }
}
