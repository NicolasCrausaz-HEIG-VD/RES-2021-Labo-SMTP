package res.ressources.pranks;

import res.ressources.config.ConfigManager;
import res.ressources.entities.Group;
import res.ressources.entities.Mail;
import res.ressources.entities.Person;
import res.ressources.entities.PersonParser;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Class that genenrate pranks and prepare them into email to be send afterwards.
 */
public class PrankGenerator
{
    private final List<Prank> pranks;

    public PrankGenerator()
    {
        List<Prank> fullPranks = PrankJSONParser.getPranks();
        pranks = new LinkedList<>();

        for (int i = 0; i < ConfigManager.getInstance().getNumberOfGroups(); i++)
        {
            int prankIndex = new Random().nextInt(fullPranks.size());
            pranks.add(fullPranks.get(prankIndex));
        }
    }

    /**
     * Prepare prank into mails ready to be send
     *
     * @return list generated mails
     */
    public List<Mail> preparePranksMails()
    {
        List<Person> persons = PersonParser.getPersons();

        final int NB_PEOPLE_IN_GROUP = persons.size() / ConfigManager.getInstance().getNumberOfGroups();

        if (NB_PEOPLE_IN_GROUP < 2)
        {
            throw new RuntimeException("Not enough emails to create " + ConfigManager.getInstance().getNumberOfGroups() + " groups of at least 2 people.");
        }

        LinkedList<Group> groups = new LinkedList<>();

        // TODO: Fix this (should not create group of 1 )
        for (int i = 0; i < persons.size(); i += NB_PEOPLE_IN_GROUP)
        {
            groups.add(new Group(persons.subList(i, i + NB_PEOPLE_IN_GROUP - 1)));
        }

        int cnt = 0;
        LinkedList<Mail> mails = new LinkedList<>();

        for (Prank p : pranks)
        {
            mails.add(new Mail(ConfigManager.getInstance().getHiddenSender(), groups.get(cnt++), p.getSubject(), p.getBody()));
        }
        return mails;
    }
}
