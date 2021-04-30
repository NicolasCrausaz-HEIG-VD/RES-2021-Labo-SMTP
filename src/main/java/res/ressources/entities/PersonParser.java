package res.ressources.entities;

import res.ressources.config.ConfigManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Reads and parse an person list from a file
 */
public class PersonParser
{
    /**
     * Read and parse email file to a list of Persons
     * @return List of Persons
     */
    public static List<Person> getPersons()
    {
        LinkedList<Person> personsList = new LinkedList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + ConfigManager.getInstance().getEmailFile())))
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
