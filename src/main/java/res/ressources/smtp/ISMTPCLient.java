package res.ressources.smtp;

import res.ressources.entities.Mail;

public interface ISMTPCLient
{
    void sendMail(Mail mail);
}
