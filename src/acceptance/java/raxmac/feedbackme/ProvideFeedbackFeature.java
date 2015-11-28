package raxmac.feedbackme;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(FeedbackMe.class)
@WebIntegrationTest
public class ProvideFeedbackFeature {


    static {
        new ProvideFeedbackFeature();
    }
    @Test
    public void primeiro_teste() {
        assertThat(1, is(1));
    }
}
