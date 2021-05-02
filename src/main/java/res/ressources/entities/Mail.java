package res.ressources.entities;

/**
 * Represents a simple mail
 * @author Alec Berney
 * @author Nicolas Crausaz
 */
public class Mail
{
    private final String from;
    private final Group to;
    private final String subject;
    private final String message;

    /**
     * @param from    hidden sender
     * @param to      group containing 1 sender and other recipients
     * @param subject mail's subject
     * @param message mail's content
     * @throws RuntimeException if from is not a valid mail address format
     */
    public Mail(String from, Group to, String subject, String message) throws RuntimeException
    {
        if (!Person.isAValidEmail(from)) throw new RuntimeException("Invalid mail format");

        this.from = from;
        this.to = to;
        this.subject = subject;
        this.message = message;
    }

    /**
     * @return Hidden sender
     */
    public String getFrom()
    {
        return from;
    }

    /**
     * @return Group containing 1 sender and other recipients
     */
    public Group getTo()
    {
        return to;
    }

    /**
     * @return Mail subject
     */
    public String getSubject()
    {
        return subject;
    }

    /**
     * @return Mail content
     */
    public String getMessage()
    {
        return message;
    }
}
