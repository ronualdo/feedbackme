package raxmac.feedbackme;

import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class FeedbackControllerTest {

    @Test
    public void should_store_the_new_feedback_after_creating_it() throws URISyntaxException {
        FeedbackRepository feedbackRepository = mock(FeedbackRepository.class);
        FeedbackController controller = new FeedbackController(feedbackRepository);

        controller.provideFeedback(new HashMap<String, String>());

        verify(feedbackRepository, atLeast(1)).save(any(Feedback.class));
    }

    @Test
    public void should_set_resource_location_after_providing_new_feedback() throws URISyntaxException {
        FeedbackRepository feedbackRepository = mock(FeedbackRepository.class);

        FeedbackController controller = new FeedbackController(feedbackRepository);

        ResponseEntity<Feedback> feedbackResponseEntity = controller.provideFeedback(new HashMap<>());

        Feedback providedFeedback = feedbackResponseEntity.getBody();
        HttpHeaders headers = feedbackResponseEntity.getHeaders();

        String locationUrl = headers.getLocation().toString();
        assertThat(locationUrl, containsString("/test_user/feedbacks/"+providedFeedback.getId()));
    }


}
