package res.ressources.entities;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PersonTest
{
    @Test
    public void shouldHaveTheRightEmail()
    {
        final String EMAIL = "jon.doe@test.ch";

        assertEquals(new Person(EMAIL).getEmail(), EMAIL);
    }
}
