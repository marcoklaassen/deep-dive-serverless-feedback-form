package click.klaassen.feedback;

import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/feedback")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
public class FeedbackResource {

    @Inject FeedbackService service;

    @POST
    public Feedback add(Feedback feedback) {
        log.info("Feedback form got rating: {}", feedback);
        service.publishFeedback(feedback.getRating());
        return feedback;
    }
}