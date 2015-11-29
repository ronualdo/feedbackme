package raxmac.feedbackme;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class FeedbackControllerTest {

    private final static String TEST_USER_NAME = "test_user";
    private FeedbackController feedbackController;
    private FeedbackRepository feedbackRepository;

    @Before
    public void setup() {
        feedbackRepository = mock(FeedbackRepository.class);
        feedbackController = new FeedbackController(feedbackRepository);
    }

    @Test
    public void should_store_the_new_feedback_after_creating_it() throws URISyntaxException {

        feedbackController.provideFeedback(TEST_USER_NAME, validRequest());

        verify(feedbackRepository, atLeast(1)).save(any(Feedback.class));
    }

    @Test
    public void should_set_resource_location_after_providing_new_feedback() throws URISyntaxException {

        ResponseEntity<Feedback> feedbackResponseEntity = feedbackController.provideFeedback(TEST_USER_NAME,
                validRequest());

        Feedback providedFeedback = feedbackResponseEntity.getBody();
        HttpHeaders headers = feedbackResponseEntity.getHeaders();

        String locationUrl = headers.getLocation().toString();
        assertThat(locationUrl, containsString("/test_user/feedbacks/"+providedFeedback.getId()));
    }

    private HashMap<String, String> validRequest() {
        return new HashMap<String, String>(){{
            put("author", "author");
            put("feedbackText", "a new feedback");
        }};
    }

    @Test
    public void should_validate_feedback() throws URISyntaxException {
        ResponseEntity responseEntity = feedbackController.provideFeedback(TEST_USER_NAME, new HashMap<>());

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.BAD_REQUEST));
        assertThat(((List<ValidationError>) responseEntity.getBody()).size(), is(2));
    }


}
