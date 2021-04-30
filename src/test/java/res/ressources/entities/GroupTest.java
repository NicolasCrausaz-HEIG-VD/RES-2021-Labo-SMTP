package res.ressources.entities;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GroupTest
{
    @Test
    public void shouldContainAPranker()
    {
        LinkedList<Person> persons = new LinkedList<>();
        persons.add(new Person("nicolas.crausaz1@heig-vd.ch"));
        persons.add(new Person("alec.berney@heig-vd.ch"));
        persons.add(new Person("jon.doe@heig-vd.ch"));
        persons.add(new Person("boaty.mcboatface@heig-vd.ch"));

        Group g = new Group(persons);

        assertNotNull(g.getPranker());
    }

    @Test
    public void shouldContainTheRightNumberOfVictims()
    {
        LinkedList<Person> persons = new LinkedList<>();
        persons.add(new Person("nicolas.crausaz1@heig-vd.ch"));
        persons.add(new Person("alec.berney@heig-vd.ch"));
        persons.add(new Person("jon.doe@heig-vd.ch"));
        persons.add(new Person("boaty.mcboatface@heig-vd.ch"));

        Group g = new Group(persons);

        assertEquals(g.getVictims().size(), persons.size() - 1);
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowIfNotEnoughPersons()
    {
        LinkedList<Person> persons1 = new LinkedList<>();
        LinkedList<Person> persons2 = new LinkedList<>();
        persons2.add(new Person("nicolas.crausaz1@heig-vd.ch"));

        new Group(persons1);
        new Group(persons2);
    }
}
