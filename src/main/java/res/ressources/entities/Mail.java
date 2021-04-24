package res.ressources.entities;

public class Mail
{
    private final String from;
    private final Group to;
    private final String subject;
    private final String message;

    public Mail(String from, Group to, String subject, String message)
    {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.message = message;
    }

    public String getFrom() {
        return from;
    }

    public Group getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }
}
