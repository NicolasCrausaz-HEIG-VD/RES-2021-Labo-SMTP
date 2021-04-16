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
        SMTPClient smtpClient = new SMTPClient(25, "localhost", "alec");

        String message = "From: test@test.com\n";
        message += "To: alec.berney@heig-vd.ch\n";
        message += "Subject: test\n";
        message += "test";

        Mail mail = new Mail("beral@sevjnet.ch", "alec.berney@heig-vd.ch", message);

        smtpClient.sendMail(mail);
    }
}
