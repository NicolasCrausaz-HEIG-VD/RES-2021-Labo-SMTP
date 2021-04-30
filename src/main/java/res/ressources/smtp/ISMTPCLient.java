package res.ressources.smtp;

import res.ressources.entities.Mail;

import java.util.List;

public interface ISMTPCLient
{
    void sendMail(Mail mail);

    void sendMultipleMails(List<Mail> mails) throws InterruptedException;
}
