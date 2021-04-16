package res.ressources.pranks;

import com.sun.tools.classfile.RuntimeTypeAnnotations_attribute;
import res.ressources.config.ConfigManager;
import res.ressources.entities.Group;
import res.ressources.entities.Mail;
import res.ressources.entities.Person;
import res.ressources.entities.PersonParser;

import java.util.LinkedList;
import java.util.Random;

public class PrankGenerator
{
    private final LinkedList<Prank> pranks;
    private final String hiddenSender;

    public PrankGenerator(String hiddenSender, int number)
    {
        this.hiddenSender = hiddenSender;
        LinkedList<Prank> fullPranks = PrankJSONParser.getPranks();
        pranks = new LinkedList<>();

        for (int i = 0; i < number; i++)
        {
            int prankIndex = new Random().nextInt(fullPranks.size());
            pranks.add(fullPranks.get(prankIndex));
        }
    }

    public LinkedList<Prank> getPranks()
    {
        return new LinkedList<>(pranks);
    }

    public LinkedList<Mail> preparePranksMails()
    {
        LinkedList<Person> persons = PersonParser.getPersons();
        LinkedList<Mail> mails = new LinkedList<>();
        final int NB_PEOPLE_IN_GROUP = persons.size() / ConfigManager.getInstance().getNumberOfGroups();

        if (NB_PEOPLE_IN_GROUP < 2)
        {
            throw new RuntimeException("Not enough emails to create " + ConfigManager.getInstance().getNumberOfGroups() + "groups of at least 2 people.");
        }

        // Faire des ranges
        //LinkedList<Group> mails = new LinkedList<>();

        // TODO: Create groups of number people, add them to list
/*
        for (int i = 0; i < persons.size(); i++)
        {
            if (i % NB_PEOPLE_IN_GROUP == 0)
            {

            }

        }
*/

        for (Prank p : pranks)
        {
            // Group gr = new Group(); // TODO: Create a mail parser
            // mails.add(new Mail(hiddenSender, new Group(new Person[]{new Person("test")}), p.getSubject(), p.getBody()));
        }
        return null;
    }
}
