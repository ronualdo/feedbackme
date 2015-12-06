package raxmac.feedbackme;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends org.springframework.data.repository.Repository<Feedback, Long> {

    public Feedback save(Feedback feedback);

    List<Feedback> findByUserName(String userName);
}
