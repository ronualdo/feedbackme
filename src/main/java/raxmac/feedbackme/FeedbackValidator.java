package raxmac.feedbackme;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FeedbackValidator {

    private final Validator validator;

    public FeedbackValidator() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public List<ValidationError> validate(Feedback feedback) {
        Set<ConstraintViolation<Feedback>> constraintViolations = validator.validate(feedback);

        Stream<ConstraintViolation<Feedback>> stream = constraintViolations.stream();

        return stream.map(new Function<ConstraintViolation<Feedback>, ValidationError>() {
            @Override
            public ValidationError apply(ConstraintViolation<Feedback> feedbackConstraintViolation) {

                return new ValidationError(feedbackConstraintViolation.getPropertyPath().toString(), feedbackConstraintViolation.getMessage());
            }
        }).collect(Collectors.toList());
    }
}
