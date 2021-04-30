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
    final static int MIN_PERSON = 2;

    /**
     * Read and parse email file to a list of Persons
     * Ensure that there is at least 2 persons in the file
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

        if (personsList.size() < MIN_PERSON)
            throw new RuntimeException("There should be at least " + MIN_PERSON + " people in the email file.");

        return personsList;
    }
}
