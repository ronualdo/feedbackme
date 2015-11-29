package raxmac.feedbackme;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties("true")
@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    private String feedbackText;
    @NotNull
    private String author;
    @NotNull
    private String userName;

    public Feedback(String userName, String feedbackText, String author) {
        this.userName = userName;
        this.feedbackText = feedbackText;
        this.author = author;
    }

    //needed because of hibernate
    private Feedback() {}

    public long getId() { return id; }

    public String getFeedbackText() {
        return feedbackText;
    }

    public String getAuthor() {
        return author;
    }
}
