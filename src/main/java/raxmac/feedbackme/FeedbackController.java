package raxmac.feedbackme;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public FeedbackController(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @RequestMapping(value = "/test_user/feedbacks", method = RequestMethod.POST)
    public ResponseEntity<Feedback> provideFeedback(@RequestBody Map<String, String> feedback) throws URISyntaxException {
        Feedback newFeedback = new Feedback(feedback.get("feedbackText"), feedback.get("author"));
        feedbackRepository.save(newFeedback);
        URI locations = createLocation(newFeedback.getId());
        return ResponseEntity.created(locations).body(newFeedback);
    }

    private URI createLocation(Long id) throws URISyntaxException {
        String locationValue = String.format("http://localhost:8080/test_user/feedbacks/%d", id);
        return new URI(locationValue);
    }
}
