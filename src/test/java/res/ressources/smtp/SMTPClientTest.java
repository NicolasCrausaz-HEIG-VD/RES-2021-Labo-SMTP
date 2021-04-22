package res.ressources.smtp;

import org.junit.Test;
import res.ressources.entities.Group;
import res.ressources.entities.Mail;
import res.ressources.entities.Person;

import static org.junit.Assert.assertTrue;

public class SMTPClientTest
{
    @Test
    public void shouldConnect()
    {
        SMTPClient smtpClient = new SMTPClient(25, "localhost", "alec");

        assertTrue(smtpClient.connect());
    }

    @Test
    public void testValidMail()
    {
        SMTPClient smtpClient = new SMTPClient(25, "localhost", "RobotApp");

        Person[] persons = {
                new Person("nicolas.crausaz1@heig-vd.ch"),
                new Person("alec.berney@heig-vd.ch"),
                new Person("jon.doe@heig-vd.ch"),
                new Person("boaty.mcboatface@heig-vd.ch")
        };

        Group group = new Group(persons);

        Mail mail = new Mail("test@test.com", group, "àààééà?§", "message");

        smtpClient.sendMail(mail);
    }
}
