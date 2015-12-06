package raxmac.feedbackme;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@JsonIgnoreProperties("true")
@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank
    @Size(max=255)
    private String feedbackText;
    @NotBlank
    @Size(max=255)
    private String author;
    @NotBlank
    @Size(max=255)
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
