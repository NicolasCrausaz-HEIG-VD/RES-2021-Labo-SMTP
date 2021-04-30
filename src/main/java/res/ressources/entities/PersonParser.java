package res.ressources.entities;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

/**
 * Reads and parse an person list from a file
 */
public class PersonParser
{

    final static String EMAILS_PATH = System.getProperty("user.dir") + "/assets/emails.utf8";

    /**
     * Read and parse email file to a list of Persons
     *
     * @return List of Persons
     */
    public static List<Person> getPersons()
    {
        LinkedList<Person> personsList = new LinkedList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(EMAILS_PATH), StandardCharsets.UTF_8)))
        {
            String line = reader.readLine();

            while (line != null)
            {
                personsList.add(new Person(line));
                line = reader.readLine();
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return personsList;
    }
}
