package res.ressources.entities;

public class Mail
{
    private String addressEmailFrom;
    private String addressEmailTo;

    private String subject;
    private String message;

    public Mail(String addressEmailFrom, String addressEmailTo, String subject, String message)
    {
        this.addressEmailFrom = addressEmailFrom;
        this.addressEmailTo = addressEmailTo;
        this.subject = subject;
        this.message = message;
    }

    public String getAddressEmailFrom() {
        return addressEmailFrom;
    }

    public String getAddressEmailTo() {
        return addressEmailTo;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }
}
