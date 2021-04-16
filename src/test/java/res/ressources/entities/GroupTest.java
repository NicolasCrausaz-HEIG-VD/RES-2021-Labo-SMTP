package res.ressources.entities;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GroupTest
{
    @Test
    public void shouldContainAPranker()
    {
        Person[] persons = {
                new Person("nicolas.crausaz1@heig-vd.ch"),
                new Person("alec.berney@heig-vd.ch"),
                new Person("jon.doe@heig-vd.ch"),
                new Person("boaty.mcboatface@heig-vd.ch")
        };

        Group g = new Group(persons);

        assertNotNull(g.getPranker());
    }

    @Test
    public void shouldContainTheRightNumberOfVictims()
    {
        Person[] persons = {
                new Person("nicolas.crausaz1@heig-vd.ch"),
                new Person("alec.berney@heig-vd.ch"),
                new Person("jon.doe@heig-vd.ch"),
                new Person("boaty.mcboatface@heig-vd.ch")
        };

        Group g = new Group(persons);

        assertEquals(g.getVictims().size(), persons.length - 1);
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowIfNotEnoughPersons()
    {
        Person[] persons1 = {};
        Person[] persons2 = {new Person("nicolas.crausaz1@heig-vd.ch")};

        new Group(persons1);
        new Group(persons2);
    }
}
