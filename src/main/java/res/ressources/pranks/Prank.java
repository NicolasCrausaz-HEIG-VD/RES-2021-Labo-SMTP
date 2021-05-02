package res.ressources.pranks;

/**
 * Represent a prank ressource
 * @author Alec Berney
 * @author Nicolas Crausaz
 */
public class Prank
{
    private final String subject;
    private final String body;

    /**
     * Create a prank containing a title and a content.
     *
     * @param subject title of prank
     * @param body    content of pranl
     */
    public Prank(String subject, String body)
    {
        this.subject = subject;
        this.body = body;
    }

    /**
     * Get title of prank
     *
     * @return title
     */
    public String getSubject()
    {
        return subject;
    }

    /**
     * get content of prank
     *
     * @return content
     */
    public String getBody()
    {
        return body;
    }
}
