package res.ressources.entities;

import res.ressources.config.ConfigManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class PersonParser
{
    public static LinkedList<Person> getPersons()
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
