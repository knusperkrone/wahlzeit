package org.wahlzeit_revisited.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.wahlzeit_revisited.service.PhotoService;


@Path("api/photo")
@Produces(MediaType.APPLICATION_JSON)
public class PhotoResource extends AbstractResource {

    @Inject
    PhotoService service;

    @GET
    public Response getImages() {
        return service.getImages();
    }

    @GET
    @Path("/{id}")
    public Response getImage(@PathParam("id") Long imageId) {
        return service.getImage(imageId);
    }

    @DELETE
    @Path("/{id}")
    public Response removeImage(@PathParam("id") Long imageId) {
        return service.removeImage(imageId);
    }

    @POST
    public Response addImage(String imageB64) {
        return service.addImage(imageB64);
    }

}
