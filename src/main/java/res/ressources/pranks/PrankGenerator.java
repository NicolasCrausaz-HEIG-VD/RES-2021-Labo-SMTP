package res.ressources.pranks;

import res.ressources.entities.Group;
import res.ressources.entities.Mail;
import res.ressources.entities.Person;

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
        LinkedList<Mail> mails = new LinkedList<>();
        for (Prank p : pranks)
        {
            // Group gr = new Group(); // TODO: Create a mail parser
            // mails.add(new Mail(hiddenSender, new Group(new Person[]{new Person("test")}), p.getSubject(), p.getBody()));
        }
    }
}
