package raxmac.feedbackme;

public class ValidationError {
    private final String message;
    private final String field;

    public ValidationError(String field, String message) {
        this.field = field;
        this.message = message;
    }

    @Override
    public boolean equals(Object object) {
        boolean result = false;

        if (object instanceof ValidationError) {
            ValidationError that = (ValidationError) object;
            result = this.field.equals(that.field) &&
                    this.message.equals(that.message);
        }

        return result;
    }

    @Override
    public String toString() {
        return String.format("[field]: %s [validation]: %s", this.field, this.message);
    }
}
