package raxmac.feedbackme;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(FeedbackMe.class)
@WebIntegrationTest
public class ProvideFeedbackFeature {

    private static RestTemplate restTemplate;

    @BeforeClass
    public static void initialize() {
        restTemplate = new RestTemplate();
    }

    @Test
    public void should_be_able_to_provide_feedback_for_another_person() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("feedbackText", "my first new feedback");
        parameters.put("author", "test author");

        ResponseEntity<ProvideFeedbackResponse> creationResponse = restTemplate
                .postForEntity("http://localhost:8080/test_user/feedbacks",
                        parameters, ProvideFeedbackResponse.class);

        assertThat(creationResponse.getStatusCode(), is(HttpStatus.CREATED));
        assertThat(creationResponse.getHeaders().get("location"), notNullValue());
        assertThat("created feedback should have an id",
                creationResponse.getBody().getId(), notNullValue());
        assertThat(creationResponse.getBody().getFeedbackText(), is(parameters.get("feedbackText")));
        assertThat(creationResponse.getBody().getAuthor(), is(parameters.get("author")));
    }
}
