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
 * @author Alec Berney
 * @author Nicolas Crausaz
 */
public class PrankGenerator
{
    private final static int MIN_PEOPLE_IN_GROUP = 2;
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

        if (NB_PEOPLE_IN_GROUP < MIN_PEOPLE_IN_GROUP)
        {
            throw new RuntimeException("Not enough emails to create " + ConfigManager.getInstance().getNumberOfGroups() + " groups of at least 2 people.");
        }

        final int lefts = persons.size() % ConfigManager.getInstance().getNumberOfGroups();

        LinkedList<Group> groups = new LinkedList<>();

        for (int i = 0; i < persons.size() - lefts; i += NB_PEOPLE_IN_GROUP)
        {
            groups.add(new Group(persons.subList(i, i + NB_PEOPLE_IN_GROUP)));
        }

        // Merge alone persons to other groups
        for (int i = 0; i < lefts; ++i)
        {
            groups.get(i).addVictim(persons.get(persons.size() - lefts + i));
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
