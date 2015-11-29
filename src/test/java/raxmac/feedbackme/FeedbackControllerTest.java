package raxmac.feedbackme;

import org.junit.Test;
import org.mockito.verification.VerificationMode;

import java.net.URISyntaxException;
import java.util.HashMap;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;

public class FeedbackControllerTest {

    @Test
    public void should_store_the_new_feedback_after_creating_it() throws URISyntaxException {
        FeedbackRepository feedbackRepository = mock(FeedbackRepository.class);
        FeedbackController controller = new FeedbackController(feedbackRepository);

        controller.provideFeedback(new HashMap<String, String>());

        verify(feedbackRepository, atLeast(1)).add(any(Feedback.class));
    }


}
