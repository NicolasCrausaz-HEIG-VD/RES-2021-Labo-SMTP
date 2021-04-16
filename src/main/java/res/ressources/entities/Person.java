package res.ressources.entities;

import java.util.regex.Pattern;

/**
 * Represents a person that is implicated (author or victim) in a SMTP prank
 */
public class Person
{
    private final String email;

    public Person(String email) throws RuntimeException
    {
        if(!isAValidEmail(email))
        {
            throw new RuntimeException("Invalid Email");
        }

        this.email = email;
    }

    public String getEmail()
    {
        return email;
    }

    private boolean isAValidEmail(String email)
    {
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");

        return pattern.matcher(email).matches();
    }
}
