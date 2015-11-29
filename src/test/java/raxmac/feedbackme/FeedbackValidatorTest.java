package raxmac.feedbackme;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FeedbackValidatorTest {

    private FeedbackValidator feedbackValidator;

    @Before
    public void initTest() {
        feedbackValidator = new FeedbackValidator();
    }

    @Test
    public void should_indicate_when_user_name_is_not_provided() {
        Feedback invalidFeedback = new Feedback(null, "feedback text", "test author");

        List<ValidationError> errorsList = feedbackValidator.validate(invalidFeedback);

        assertThat(errorsList.size(), is(1));
        assertThat(errorsList, contains(equalTo(new ValidationError("userName", "may not be null"))));
    }

    @Test
    public void should_indicate_when_feedback_text_is_not_provided() {
        Feedback invalidFeedback = new Feedback("test_user", null, "test author");

        List<ValidationError> errorsList = feedbackValidator.validate(invalidFeedback);

        assertThat(errorsList.size(), is(1));
        assertThat(errorsList, contains(equalTo(new ValidationError("feedbackText", "may not be null"))));
    }

    @Test
    public void should_indicate_when_author_is_not_provided() {
        Feedback invalidFeedback = new Feedback("test_user", "feedback text", null);

        List<ValidationError> errorsList = feedbackValidator.validate(invalidFeedback);

        assertThat(errorsList.size(), is(1));
        assertThat(errorsList, contains(equalTo(new ValidationError("author", "may not be null"))));
    }
}
