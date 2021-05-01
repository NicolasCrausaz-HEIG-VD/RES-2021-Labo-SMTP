package res.ressources.smtp;

import org.junit.Test;
import res.ressources.config.ConfigManager;
import res.ressources.entities.Group;
import res.ressources.entities.Mail;
import res.ressources.entities.Person;

import java.util.LinkedList;

import static org.junit.Assert.assertTrue;

public class SMTPClientTest
{
    @Test
    public void shouldConnect()
    {
        SMTPClient smtpClient = new SMTPClient(ConfigManager.getInstance().getSMTPPort(),
                                                ConfigManager.getInstance().getSMTPServer(),
                                                ConfigManager.getInstance().getClientName());
        assertTrue(smtpClient.connect());
    }

    @Test
    public void testValidMail()
    {
        SMTPClient smtpClient = new SMTPClient(ConfigManager.getInstance().getSMTPPort(),
                                                ConfigManager.getInstance().getSMTPServer(),
                                                ConfigManager.getInstance().getClientName());

        LinkedList<Person> persons = new LinkedList<>();
        persons.add(new Person("nicolas.crausaz1@heig-vd.ch"));
        persons.add(new Person("alec.berney@heig-vd.ch"));
        persons.add(new Person("jon.doe@heig-vd.ch"));
        persons.add(new Person("boaty.mcboatface@heig-vd.ch"));

        Group group = new Group(persons);

        Mail mail = new Mail("test@test.com", group, "Test caractères spéciaux",
                "Ce message contient des caractères spéciaux");

        smtpClient.sendMail(mail);
    }

    @Test(expected = RuntimeException.class)
    public void testUnvalidMail()
    {
        SMTPClient smtpClient = new SMTPClient(ConfigManager.getInstance().getSMTPPort(),
                                                ConfigManager.getInstance().getSMTPServer(),
                                                ConfigManager.getInstance().getClientName());

        LinkedList<Person> persons = new LinkedList<>();
        persons.add(new Person("nicolas.crausaz1@heig-vd.ch"));
        persons.add(new Person("alec.berney@heig-vd.ch"));
        persons.add(new Person("jon.doe@heig-vd.ch"));
        persons.add(new Person("boaty.mcboatface@heig-vd.ch"));

        Group group = new Group(persons);

        Mail mail = new Mail("blabla", group, "test", "message");

        smtpClient.sendMail(mail);
    }

    @Test
    public void testMultipleValidsMails() throws InterruptedException
    {
        SMTPClient smtpClient = new SMTPClient(ConfigManager.getInstance().getSMTPPort(),
                                                ConfigManager.getInstance().getSMTPServer(),
                                                ConfigManager.getInstance().getClientName());

        LinkedList<Person> persons = new LinkedList<>();
        persons.add(new Person("nicolas.crausaz1@heig-vd.ch"));
        persons.add(new Person("alec.berney@heig-vd.ch"));
        persons.add(new Person("jon.doe@heig-vd.ch"));
        persons.add(new Person("boaty.mcboatface@heig-vd.ch"));

        Group group = new Group(persons);

        LinkedList<Mail> mails = new LinkedList<>();
        mails.add(new Mail("test@test.com", group, "test", "message"));
        mails.add(new Mail("test@test.com", group, "test", "message"));
        mails.add(new Mail("test@test.com", group, "test", "message"));
        mails.add(new Mail("test@test.com", group, "test", "message"));

        smtpClient.sendMultipleMails(mails);
    }
}
