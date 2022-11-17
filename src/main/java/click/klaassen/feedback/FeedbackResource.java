package click.klaassen.feedback;

import io.quarkus.oidc.UserInfo;
import io.quarkus.security.Authenticated;
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

    @Inject FeedbackService service;
    @Inject UserInfo userInfo;

    @POST
    @NoCache
    @Authenticated
    public Feedback add(Feedback feedback) {
        log.info("Feedback form got rating: {} from user {}", feedback, new User(userInfo));
        service.publishFeedback(feedback);
        return feedback;
    }

    @GET
    public String version() {
        return version;
    }
}