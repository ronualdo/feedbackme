package raxmac.feedbackme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class FeedbackController {

    private final FeedbackRepository feedbackRepository;
    private final FeedbackValidator feedbackValidator;

    @Autowired
    public FeedbackController(FeedbackRepository feedbackRepository) {
        this.feedbackValidator = new FeedbackValidator();
        this.feedbackRepository = feedbackRepository;
    }

    @RequestMapping(value = "/{userName}/feedbacks", method = RequestMethod.POST)
    public ResponseEntity provideFeedback(@PathVariable String userName,
                                          @RequestBody Map<String, String> feedback) throws URISyntaxException {

        Feedback newFeedback = new Feedback(userName, feedback.get("feedbackText"), feedback.get("author"));
        List<ValidationError> errors = feedbackValidator.validate(newFeedback);

        if (errors.isEmpty()) {
            feedbackRepository.save(newFeedback);
            URI locations = createLocation(userName, newFeedback.getId());
            return ResponseEntity.created(locations).body(newFeedback);
        } else {
            return ResponseEntity.badRequest().body(errors);
        }
    }

    private URI createLocation(String userName, Long id) throws URISyntaxException {
        String locationValue = String.format("http://localhost:8080/%s/feedbacks/%d", userName, id);
        return new URI(locationValue);
    }
}
