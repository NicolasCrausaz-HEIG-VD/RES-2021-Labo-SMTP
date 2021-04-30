package res.ressources.entities;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Represents a Group of at least 2 people.
 * A group of N people will be composed with one pranker and (n-1) victims.
 */
public class Group
{
    private final Person pranker;
    private final LinkedList<Person> victims;

    /**
     * Create a group of victims with one pranker
     *
     * @param persons Should at least contains 2 Person
     */
    public Group(List<Person> persons)
    {
        if (persons == null || persons.size() < 2)
            throw new RuntimeException("Cannot create a group from a list of less than 2 Person");

        // Select a random pranker within a person list,
        // others will be victims
        victims = new LinkedList<>();
        int prankerId = new Random().nextInt(persons.size());
        pranker = persons.get(prankerId);

        for (int i = 0; i < persons.size(); i++)
        {
            if (i != prankerId)
            {
                victims.add(persons.get(i));
            }
        }
    }

    public Person getPranker()
    {
        return pranker;
    }

    public LinkedList<Person> getVictims()
    {
        return victims;
    }
}
