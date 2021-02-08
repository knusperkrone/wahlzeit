package org.wahlzeit_revisited.filter;

import jakarta.annotation.Priority;
import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;
import org.wahlzeit.services.SysLog;
import org.wahlzeit_revisited.model.User;
import org.wahlzeit_revisited.model.repository.UserReadRepository;

import java.lang.reflect.Method;
import java.security.Principal;
import java.sql.SQLException;
import java.util.*;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    private static final String AUTHENTICATION_SCHEME = "Basic ";

    @Context
    private ResourceInfo resourceInfo;
    @Inject
    private UserReadRepository userReadRepository;

    /**
     * A Jakarta Filter, this is applied before each request
     * <p>
     * It checks the called resource for the @ROLES_ALLOWED annotation.
     * If this annotation is present, a credential check is performed.
     * A successful check set's the security context,
     * an unsuccessful check makes the request fail and the resource is never called
     * <p>
     * Currently only Basic HTTP authentication is supported. For more information see:
     * https://en.wikipedia.org/wiki/Basic_access_authentication
     *
     * @param requestContext the call context
     */
    @Override
    public void filter(ContainerRequestContext requestContext) {
        Method resource = resourceInfo.getResourceMethod();

        if (resource.isAnnotationPresent(DenyAll.class)) {
            requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
                    .entity("Access blocked for all users").build());
        } else if (resource.isAnnotationPresent(RolesAllowed.class)) {

            String credentials = extractCredentials(requestContext);
            if (credentials == null) {
                requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
                        .entity("Not allowed, you need to login first").build());
                return;
            }

            // split values into "username:password"
            int splitIndex = credentials.indexOf(':');
            String username = credentials.substring(0, splitIndex);
            String password = credentials.substring(splitIndex + 1);

            RolesAllowed rolesAnnotation = resource.getAnnotation(RolesAllowed.class);
            Set<String> rolesSet = new HashSet<>(Arrays.asList(rolesAnnotation.value()));
            if (rolesSet.contains("USER")) {

                Optional<User> userOpt = findUser(username, password);
                if (userOpt.isEmpty()) {
                    requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
                            .entity("Not allowed, invalid credentials").build());
                    return;
                }
                setSecurityContext(userOpt.get(), requestContext);
            }
        }
    }

    /**
     * Extracts the BASIC header value and performs the base64 decoding
     *
     * @param requestContext the current HTTP request
     * @return the encoded and ready to split credentials or null on failure
     */
    private String extractCredentials(ContainerRequestContext requestContext) {
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || authorizationHeader.length() <= AUTHENTICATION_SCHEME.length()) {
            return null;
        }

        // decode base64
        String encodedUserNamePwd = authorizationHeader.substring(AUTHENTICATION_SCHEME.length()).trim();
        String credentials = new String(Base64.getDecoder().decode(encodedUserNamePwd));

        // check valid data
        int splitIndex = credentials.indexOf(':');
        if (splitIndex == -1) {
            return null;
        }
        return credentials;
    }

    /**
     * Convenience method for wrapping up barely possible SQL errors
     *
     * @param username the username
     * @param password the password
     * @return the User that got found, or an empty Optional
     */
    private Optional<User> findUser(String username, String password) {
        try {
            return userReadRepository.findByUsernamePassword(username, password);
        } catch (SQLException sqlException) {
            SysLog.logThrowable(sqlException);
            return Optional.empty();
        }
    }

    /**
     * Sets the security context, which can be accessed via @inject in the called resource
     *
     * @param user           the user that got extracted from the headers
     * @param requestContext the call context
     */
    private void setSecurityContext(User user, ContainerRequestContext requestContext) {
        requestContext.setSecurityContext(new SecurityContext() {
            @Override
            public Principal getUserPrincipal() {
                return user;
            }

            @Override
            public boolean isUserInRole(String role) {
                return true;
            }

            @Override
            public boolean isSecure() {
                return true;
            }

            @Override
            public String getAuthenticationScheme() {
                return AUTHENTICATION_SCHEME;
            }
        });
    }

}
