package ca.fangpei.cs.feelsbook;

public class SubscriptionTooLongException extends Throwable {


    public SubscriptionTooLongException() {
        super("The message is too long! Please keep your tweets within 100 characters.");
    }
}

