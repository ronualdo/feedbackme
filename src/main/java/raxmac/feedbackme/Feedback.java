package raxmac.feedbackme;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties("true")
public class Feedback {
    private final long id;
    private final String feedbackText;
    private final String author;

    public Feedback(String feedbackText, String author) {
        this.id = 1l;
        this.feedbackText = feedbackText;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public String getFeedbackText() {
        return feedbackText;
    }

    public String getAuthor() {
        return author;
    }
}
