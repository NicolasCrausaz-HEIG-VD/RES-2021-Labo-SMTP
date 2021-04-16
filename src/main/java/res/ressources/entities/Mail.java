package res.ressources.entities;

public class Mail
{
    private String addressEmailFrom;
    private String addressEmailTo;
    private String message;

    public Mail(String addressEmailFrom, String addressEmailTo, String message)
    {
        this.addressEmailFrom = addressEmailFrom;
        this.addressEmailTo = addressEmailTo;
        this.message = message;
    }

    public String getAddressEmailFrom() {
        return addressEmailFrom;
    }

    public String getAddressEmailTo() {
        return addressEmailTo;
    }

    public String getMessage() {
        return message;
    }
}
