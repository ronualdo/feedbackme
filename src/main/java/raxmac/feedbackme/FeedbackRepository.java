package raxmac.feedbackme;

import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends org.springframework.data.repository.Repository<Feedback, Long> {

    public Feedback save(Feedback feedback);

}
