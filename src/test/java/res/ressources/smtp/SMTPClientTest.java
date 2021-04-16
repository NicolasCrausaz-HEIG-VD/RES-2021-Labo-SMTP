package res.ressources.smtp;

import org.junit.Test;
import res.ressources.entities.Mail;

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

        Mail mail = new Mail("test@test.com", "test2@test2.test2", "test", "message");

        smtpClient.sendMail(mail);
    }
}
