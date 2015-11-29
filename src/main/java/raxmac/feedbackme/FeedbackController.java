package raxmac.feedbackme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/{userName}/feedbacks", method = RequestMethod.POST)
    public ResponseEntity<Feedback> provideFeedback(@PathVariable String userName, @RequestBody Map<String, String> feedback) throws URISyntaxException {
        Feedback newFeedback = new Feedback(userName, feedback.get("feedbackText"), feedback.get("author"));
        feedbackRepository.save(newFeedback);
        URI locations = createLocation(userName, newFeedback.getId());
        return ResponseEntity.created(locations).body(newFeedback);
    }

    private URI createLocation(String userName, Long id) throws URISyntaxException {
        String locationValue = String.format("http://localhost:8080/%s/feedbacks/%d", userName, id);
        return new URI(locationValue);
    }
}
