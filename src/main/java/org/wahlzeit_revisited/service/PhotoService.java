package org.wahlzeit_revisited.service;


import jakarta.ws.rs.core.Response;
import org.wahlzeit_revisited.dto.ImageDto;

import java.util.List;


public class PhotoService {

    public Response getImages() {
        ImageDto mockDto = new ImageDto();
        List<ImageDto> dto = List.of(mockDto, mockDto, mockDto, mockDto);
        return Response.ok(dto).build();
    }

    public Response getImage(long imageId) {
        ImageDto mockDto = new ImageDto();
        return Response.ok(mockDto).build();
    }

    public Response removeImage(long imageId) {
        return Response.ok().build();
    }

    public Response addImage(String imageB64) {
        return Response.ok().build();
    }

}
