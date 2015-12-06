package raxmac.feedbackme;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(FeedbackMe.class)
@WebIntegrationTest
public class RetrieveFeebackFeature {

    private RestTemplate restTemplate;

    @Before
    public void setUp() {
        restTemplate = new RestTemplate();
    }
    @Test
    public void should_be_able_to_retrieve_feedbacks_of_specific_user() {
        Map<String, String> feedback1 = new HashMap<String, String>(){{
            put("feedbackText", "feedback1");
            put("author", "author 1");
        }};

        Map<String, String> feedback2 = new HashMap<String, String>(){{
            put("feedbackText", "feedback2");
            put("author", "author 2");
        }};

        givenJoaoHasFeedbacks(feedback1, feedback2);

        ResponseEntity feedbacksRetrieved = restTemplate.getForEntity("http://localhost:8080/joao/feedbacks", List.class);
        List<Map<String, String>> feedbacks = (List<Map<String, String>>) feedbacksRetrieved.getBody();

        assertThat(feedbacksRetrieved.getStatusCode(), is(HttpStatus.OK));
        assertThat(feedbacks.size(), is(2));
        assertThat(feedbacks, hasFeedbackWithTextAndAuthor("feedback1", "author 1"));
        assertThat(feedbacks, hasFeedbackWithTextAndAuthor("feedback1", "author 1"));

    }

    private Matcher<? super List<Map<String, String>>> hasFeedbackWithTextAndAuthor(String feedbackText, String author) {
        return new TypeSafeMatcher<List<Map<String, String>>>() {
            @Override
            protected boolean matchesSafely(List<Map<String, String>> item) {
                return item.stream().anyMatch(new Predicate<Map<String, String>>() {
                    @Override
                    public boolean test(Map<String, String> provideFeedbackResponse) {
                        return provideFeedbackResponse.get("feedbackText").equals(feedbackText) &&
                                provideFeedbackResponse.get("author").equals(author);
                    }
                });
            }

            @Override
            public void describeTo(Description description) {

            }
        };
    }

    private void givenJoaoHasFeedbacks(Map<String, String>... feedbacks) {
        for (Map<String, String> feedback : feedbacks) {
            ResponseEntity<ProvideFeedbackResponse> creationResponse = restTemplate
                    .postForEntity("http://localhost:8080/joao/feedbacks",
                            feedback, ProvideFeedbackResponse.class);

            if (creationResponse.getStatusCode() != HttpStatus.CREATED) {
                throw new RuntimeException("impossible to create feedbacks");
            }
        }
    }
}
