package org.wahlzeit_revisited;

import jakarta.ws.rs.core.UriBuilder;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.wahlzeit_revisited.db.repository.UserReadRepository;
import org.wahlzeit_revisited.db.repository.UserWriteRepository;
import org.wahlzeit_revisited.dto.UserCreationDto;
import org.wahlzeit_revisited.main.DatabaseMain;
import org.wahlzeit_revisited.service.PhotoService;
import org.wahlzeit_revisited.service.UserService;

import java.net.URI;


public class Wahlzeit {

    private static class ServiceInjectBinder extends AbstractBinder {
        @Override
        protected void configure() {
            bind(PhotoService.class).to(PhotoService.class);
            bind(UserService.class).to(UserService.class);
            bind(UserWriteRepository.class).to(UserWriteRepository.class);
            bind(UserReadRepository.class).to(UserReadRepository.class);
        }
    }

    public static void main(String[] args) throws Exception {
        // setup database-connection
        DatabaseMain.initDatabase();

        // setup endpoints/API
        ResourceConfig config = new ResourceConfig()
                .packages("org.wahlzeit_revisited.service")
                .packages("org.wahlzeit_revisited.resource");
        config.register(new ServiceInjectBinder());

        // start server
        URI baseUri = UriBuilder.fromUri("http://[::]/").port(8080).build();
        GrizzlyHttpServerFactory.createHttpServer(baseUri, config);
    }

}
