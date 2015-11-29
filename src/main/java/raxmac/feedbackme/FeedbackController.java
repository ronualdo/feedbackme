package raxmac.feedbackme;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

@RestController
public class FeedbackController {

    private final FeedbackRepository feedbackRepository;

    public FeedbackController(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @RequestMapping(value = "/test_user/feedbacks", method = RequestMethod.POST)
    public ResponseEntity<Feedback> provideFeedback(@RequestBody Map<String, String> feedback) throws URISyntaxException {
        URI locations = createLocation();
        Feedback newFeedback = new Feedback(feedback.get("feedbackText"), feedback.get("author"));

        feedbackRepository.add(newFeedback);


        return ResponseEntity.created(locations).body(newFeedback);
    }

    private URI createLocation() throws URISyntaxException {
        return new URI("http://localhost:8080/test_user/feedbacks/1");
    }
}
