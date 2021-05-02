package res.ressources.smtp;

import res.ressources.entities.Mail;

import java.util.List;

/**
 * Necessities for SMTPClient
 * @author Alec Berney
 * @author Nicolas Crausaz
 */
public interface ISMTPCLient
{
    /**
     * Send a mail
     * @param mail
     */
    void sendMail(Mail mail);

    /**
     * Send a list of mails
     * @param mails list of mail
     * @throws InterruptedException
     */
    void sendMultipleMails(List<Mail> mails) throws InterruptedException;
}
