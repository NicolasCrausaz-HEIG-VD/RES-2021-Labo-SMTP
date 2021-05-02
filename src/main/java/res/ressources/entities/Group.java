package res.ressources.entities;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Represents a Group of at least 2 people.
 * A group of N people will be composed with one pranker and (n-1) victims.
 * @author Alec Berney
 * @author Nicolas Crausaz
 */
public class Group
{
    private final Person pranker;
    private final List<Person> victims;

    /**
     * Create a group of victims with one pranker
     * Selects a random person in the list as the pranker,
     * others will be victims
     *
     * @param persons Should at least contains 2 Person
     */
    public Group(List<Person> persons)
    {
        if (persons == null || persons.size() < 2)
            throw new RuntimeException("Cannot create a group from a list of less than 2 Person");

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

    /**
     * Get the person who has the pranker role in the group
     * @return The pranker
     */
    public Person getPranker()
    {
        return pranker;
    }

    /**
     * Get the people who have the victim role in the group
     * @return List of victims
     */
    public List<Person> getVictims()
    {
        return victims;
    }

    /**
     * Add one more  person to the victims
     * @param person The person to add to the victims list
     */
    public void addVictim(Person person)
    {
        victims.add(person);
    }
}
