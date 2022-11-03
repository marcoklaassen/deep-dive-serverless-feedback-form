package click.klaassen.feedback;

import io.quarkus.security.Authenticated;
import io.quarkus.security.identity.SecurityIdentity;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.resteasy.annotations.cache.NoCache;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/feedback")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
public class FeedbackResource {

    @ConfigProperty(name = "feedback.version")
    String version;

    @Inject SecurityIdentity securityIdentity;
    @Inject FeedbackService service;

    @POST
    @NoCache
    @Authenticated
    public Feedback add(Feedback feedback) {
        log.info("Feedback form got rating: {} from user {}", feedback, new User(securityIdentity));
        service.publishFeedback(feedback.getRating());
        return feedback;
    }

    @GET
    public String version() {
        return version;
    }
}