package res.ressources.entities;

import java.util.LinkedList;
import java.util.Random;

public class Group
{
    private final Person pranker;
    private final LinkedList<Person> victims;

    public Group(Person[] persons)
    {
        if (persons == null) throw new RuntimeException("Cannot create a group from an empty list of Persons");

        // Select a random pranker within a person list,
        // others will be victims
        victims = new LinkedList<>();
        int prankerId = new Random().nextInt(persons.length);
        pranker = persons[prankerId];

        for (int i = 0; i < persons.length; i++)
        {
            if (i != prankerId)
            {
                victims.add(persons[i]);
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
