package res.ressources.entities;

import java.util.regex.Pattern;

/**
 * Represents a person that is implicated (author or victim) in a SMTP prank
 * @author Alec Berney
 * @author Nicolas Crausaz
 */
public class Person
{
    private final String email;

    /**
     * Instanciate a person ressource from its mail adress
     * should be a valid address
     *
     * @param email mail address
     * @throws RuntimeException if address format is not valid
     */
    public Person(String email) throws RuntimeException
    {
        if(!isAValidEmail(email))
        {
            throw new RuntimeException("Invalid Email");
        }

        this.email = email;
    }

    /**
     * @return Mail address of person
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * Check if a mail address has a valid format
     * @param email mail address
     * @return true if format is valid, else false
     */
    public static boolean isAValidEmail(String email)
    {
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        return pattern.matcher(email).matches();
    }
}
