package res.ressources.pranks;

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

    public LinkedList<Mail> preparePranksMails()
    {
        LinkedList<Person> persons = PersonParser.getPersons();
        LinkedList<Mail> mails = new LinkedList<>();
        final int NB_PEOPLE_IN_GROUP = persons.size() / ConfigManager.getInstance().getNumberOfGroups();

        if (NB_PEOPLE_IN_GROUP < 2)
        {
            throw new RuntimeException("Not enough emails to create " + ConfigManager.getInstance().getNumberOfGroups() + "groups of at least 2 people.");
        }

        LinkedList<Group> groups = new LinkedList<>();

        for (int i = 0; i < persons.size(); i += NB_PEOPLE_IN_GROUP)
        {
            groups.add(new Group(persons.subList(i, i + NB_PEOPLE_IN_GROUP - 1)));
        }

        int cnt = 0;
        for (Prank p : pranks)
        {
            mails.add(new Mail(hiddenSender, groups.get(cnt++), p.getSubject(), p.getBody()));
        }
        return mails;
    }
}
