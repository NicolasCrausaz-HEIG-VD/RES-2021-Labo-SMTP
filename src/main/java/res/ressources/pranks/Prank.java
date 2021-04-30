package res.ressources.pranks;

public class Prank
{
    private final Mail
    private final String subject;
    private final String body;

    public Prank(String subject, String body)
    {
        this.subject = subject;
        this.body = body;
    }

    public String getSubject()
    {
        return subject;
    }

    public String getBody()
    {
        return body;
    }
}
