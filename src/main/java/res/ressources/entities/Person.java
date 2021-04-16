package res.ressources.entities;

/**
 * Represents a person that is implicated (author or victim) in a SMTP prank
 */
public class Person
{
    private final String email;

    public Person(String email)
    {
        this.email = email;
    }

    public String getEmail()
    {
        return email;
    }
}
